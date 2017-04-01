import Database.ViewLaterStatements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gerritgerritsen on 2017-04-01.
 */
@WebServlet(name = "RemoveViewLater")
public class RemoveViewLater extends HttpServlet {


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
            vs.deleteFromViewLater(uid, mid);
        }
    }
}
