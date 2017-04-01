import Database.MediaStatements;
import Results.Results;
import Query.Query;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by gerritgerritsen on 2017-03-28.
 */
@WebServlet(name = "SearchMedia")
public class SearchMedia extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String req = request.getQueryString();
        //Variables to hold incoming request parameters
        List<Results> resultsList = new ArrayList<Results>();

        String search;
        Boolean book;
        Boolean comic;
        Boolean movie;
        Boolean tv;
        Boolean mediaTitle;
        Boolean max;
        Boolean min;


        // Regex patterns to extract parameter values
        Pattern searchPattern = Pattern.compile("search=(.+?(?=&))");
        Pattern bookPattern = Pattern.compile("book=(.+?(?=&))");
        Pattern comicPattern = Pattern.compile("comic=(.+?(?=&))");
        Pattern moviePattern = Pattern.compile("movie=(.+?(?=&))");
        Pattern tvPattern = Pattern.compile("tv=(.+?(?=&))");
        Pattern mediaTitlePattern = Pattern.compile("mediaTitle=(.+?(?=&))");
        Pattern maxPattern = Pattern.compile("max=(.+?(?=&))");
        Pattern minPattern = Pattern.compile("min=(.+)");


        // Matchers
        Matcher searchPatternMatch = searchPattern.matcher(req);
        Matcher bookPatternMatch = bookPattern.matcher(req);
        Matcher comicPatternMatch = comicPattern.matcher(req);
        Matcher moviePatternMatch = moviePattern.matcher(req);
        Matcher tvPatternMatch = tvPattern.matcher(req);
        Matcher mediaTitlePatternMatch = mediaTitlePattern.matcher(req);
        Matcher maxPatternMatch = maxPattern.matcher(req);
        Matcher minPatternMatch = minPattern.matcher(req);


        // Here's where we decide what to do with the request we received

        // Either there's a specific text search or not. Here's the first case
        MediaStatements mediaStatements = new MediaStatements();
        if (searchPatternMatch.find()) {
            search = searchPatternMatch.group(1);
            if (bookPatternMatch.find()
                    && comicPatternMatch.find()
                    && moviePatternMatch.find()
                    && tvPatternMatch.find()
                    && mediaTitlePatternMatch.find()
                    && maxPatternMatch.find()
                    && minPatternMatch.find())
                    {
                // get the values of these booleans from the request
                book        = Boolean.parseBoolean(bookPatternMatch.group(1));
                comic       = Boolean.parseBoolean(comicPatternMatch.group(1));
                movie       = Boolean.parseBoolean(moviePatternMatch.group(1));
                tv          = Boolean.parseBoolean(tvPatternMatch.group(1));
                mediaTitle  = Boolean.parseBoolean(mediaTitlePatternMatch.group(1));
                max         = Boolean.parseBoolean(maxPatternMatch.group(1));
                min         = Boolean.parseBoolean(minPatternMatch.group(1));


                Query query = new Query(search, book, comic, movie, tv, mediaTitle, max, min);
                resultsList = mediaStatements.findMedia(query);
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
}
