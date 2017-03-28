import Database.MediaStatements;
import Results.Results;

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
        Boolean creator;
        Boolean mediaTitle;
        Boolean max;
        Boolean min;
        Boolean avgMax;
        Boolean avgMin;

        // Regex patterns to extract parameter values
        Pattern searchPattern = Pattern.compile("search=(.+?(?=&))");
        Pattern bookPattern = Pattern.compile("book=(.+?(?=&))");
        Pattern comicPattern = Pattern.compile("comic=(.+?(?=&))");
        Pattern moviePattern = Pattern.compile("movie=(.+?(?=&))");
        Pattern tvPattern = Pattern.compile("tv=(.+?(?=&))");
        Pattern creatorPattern = Pattern.compile("creator=(.+?(?=&))");
        Pattern mediaTitlePattern = Pattern.compile("mediaTitle=(.+?(?=&))");
        Pattern maxPattern = Pattern.compile("max=(.+?(?=&))");
        Pattern minPattern = Pattern.compile("min=(.+?(?=&))");
        Pattern avgMaxPattern = Pattern.compile("avgMax=(.+?(?=&))");
        Pattern avgMinPattern = Pattern.compile("avgMin=(.+?(?=&))");

        // Matchers
        Matcher searchPatternMatch = searchPattern.matcher(req);
        Matcher bookPatternMatch = bookPattern.matcher(req);
        Matcher comicPatternMatch = comicPattern.matcher(req);
        Matcher moviePatternMatch = moviePattern.matcher(req);
        Matcher tvPatternMatch = tvPattern.matcher(req);
        Matcher creatorPatternMatch = creatorPattern.matcher(req);
        Matcher mediaTitlePatternMatch = mediaTitlePattern.matcher(req);
        Matcher maxPatternMatch = maxPattern.matcher(req);
        Matcher minPatternMatch = minPattern.matcher(req);
        Matcher avgMaxPatternMatch = avgMaxPattern.matcher(req);
        Matcher avgMinPatternMatch = avgMinPattern.matcher(req);

        // Here's where we decide what to do with the request we received

        // Either there's a specific text search or not. Here's the first case
        MediaStatements mediaStatements = new MediaStatements();
        if (searchPatternMatch.find()) {
            search = searchPatternMatch.group(1);
            if (bookPatternMatch.find() && comicPatternMatch.find() && moviePatternMatch.find() && tvPatternMatch.find()) {
                // get the values of these booleans from the request
                book  = Boolean.parseBoolean(bookPatternMatch.group(1));
                comic = Boolean.parseBoolean(comicPatternMatch.group(1));
                movie = Boolean.parseBoolean(moviePatternMatch.group(1));
                tv    = Boolean.parseBoolean(tvPatternMatch.group(1));

                if (book && comic && movie && tv) {
                    if (creatorPatternMatch.find() && mediaTitlePatternMatch.find()) {
                        // get the values of these booleans from the request
                        creator = Boolean.parseBoolean(creatorPatternMatch.group(1));
                        mediaTitle = Boolean.parseBoolean(mediaTitlePatternMatch.group(1));
                        if (creator) {
                            if (maxPatternMatch.find() && minPatternMatch.find() && avgMaxPatternMatch.find() && avgMinPatternMatch.find()) {
                                max = Boolean.parseBoolean(maxPatternMatch.group(1));
                                min = Boolean.parseBoolean(minPatternMatch.group(1));
                                avgMax = Boolean.parseBoolean(avgMaxPatternMatch.group(1));
                                avgMin = Boolean.parseBoolean(avgMinPatternMatch.group(1));

                                if (max) {
                                    // TODO: write findAllCategoriesByCreatorWithKeyword in the MediaStatements class
                                    // resultsList = mediaStatements.findAllCategoriesByCreatorWithKeyword(search, "max");
                                }
                                if (min) {
                                    // resultsList = mediaStatements.findAllCategoriesByCreatorWithKeyword(search, "min");
                                }
                                if (avgMax) {
                                    // resultsList = mediaStatements.findAllCategoriesByCreatorWithKeyword(search, "avgMax");
                                }
                                if (avgMin) {
                                    // resultsList = mediaStatements.findAllCategoriesByCreatorWithKeyword(search, "avgMin");
                                }

                                else {
                                    // resultsList = mediaStatements.findAllCategoriesByCreatorWithKeyword(search, "none");
                                }

                            }

                        }
                        if (mediaTitle) {
                            if (maxPatternMatch.find() && minPatternMatch.find() && avgMaxPatternMatch.find() && avgMinPatternMatch.find()) {
                                max = Boolean.parseBoolean(maxPatternMatch.group(1));
                                min = Boolean.parseBoolean(minPatternMatch.group(1));
                                avgMax = Boolean.parseBoolean(avgMaxPatternMatch.group(1));
                                avgMin = Boolean.parseBoolean(avgMinPatternMatch.group(1));

                                if (max) {
                                    // TODO: write findAllCategoriesByCreatorWithKeyword in the MediaStatements class
                                    // resultsList = mediaStatements.findAllCategoriesByTitleWithKeyword(search, "max");
                                }
                                if (min) {
                                    // resultsList = mediaStatements.findAllCategoriesByTitleWithKeyword(search, "min");
                                }
                                if (avgMax) {
                                    // resultsList = mediaStatements.findAllCategoriesByTitleWithKeyword(search, "avgMax");
                                }
                                if (avgMin) {
                                    // resultsList = mediaStatements.findAllCategoriesByTitleWithKeyword(search, "avgMin");
                                }

                                else {
                                    // resultsList = mediaStatements.findAllCategoriesByTitleWithKeyword(search, "none");
                                }

                            }
                        }
                        else {
                            // TODO: write findAllCategoriesWithKeyword in the MediaStatements class
                            // resultsList = mediaStatements.findAllCategoriesWithKeyword(search);
                        }
                    }

                // TODO: we will need to enumerate each possible combination of book & movie, or book & TV show, etc, like above.
                    // this will be a beefy file but it's doing all the heavy lifting for search
                }

            }
        }

        // No specific text, so just query based on what boxes have been checked
        if (bookPatternMatch.find()) {
            // TODO: replicate above once complete, just without a search keyword
            System.out.println(bookPatternMatch.group(1));
        }

    }
}
