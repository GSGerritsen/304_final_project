import Database.StatStatements;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Stats.Stats;

@WebServlet(name = "Stats")
public class StatsServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Stats> resultsList = new ArrayList<Stats>();
        StatStatements ss = new StatStatements();
        resultsList = ss.generateMax();
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
