import Database.ViewLaterStatements;
import Results.Results;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gerritgerritsen on 2017-03-31.
 */
@WebServlet(name = "ViewLater")
public class ViewLater extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        Pattern uidPattern = Pattern.compile("uid\":(\\d),");
        Pattern midPattern = Pattern.compile("mid\":(\\d+)");

        Matcher uidPatternMatch = uidPattern.matcher(data);
        Matcher midPatternMatch = midPattern.matcher(data);

        if (uidPatternMatch.find() && midPatternMatch.find()) {
            int uid = Integer.parseInt(uidPatternMatch.group(1));
            int mid = Integer.parseInt(midPatternMatch.group(1));
            ViewLaterStatements vs = new ViewLaterStatements();
            vs.addToViewLater(uid, mid);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String req = request.getQueryString();
        int uid;
        Pattern requesterPattern = Pattern.compile("requester=(\\d+)");
        Matcher requesterPatternMatch = requesterPattern.matcher(req);

        if (requesterPatternMatch.find()) {
            uid = Integer.parseInt(requesterPatternMatch.group(1));
            List<Results> resultsList = new ArrayList<Results>();
            ViewLaterStatements vs = new ViewLaterStatements();
            resultsList = vs.retrieveViewLater(uid);
            response.getWriter().write("[");
            for (int i = 0; i < resultsList.size(); i++) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonObj = mapper.writeValueAsString(resultsList.get(i));
                response.getWriter().write(jsonObj);
                // A check so that a comma isn't added for the last object, making the response writer contain valid JSON
                if (i != resultsList.size() - 1) {
                    response.getWriter().write(", ");
                }
            }
            response.getWriter().write("]");
            response.getWriter();
        }




    }
}