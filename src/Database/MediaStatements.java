package Database;

import Results.Results;
import Query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MediaStatements {

    public List<Results> findMedia(Query query) {
        List<Results> ret = new ArrayList<>();

        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();

        // User entered a keyword to search on
        if (!query.getSearch().isEmpty()) {
            // Enumerate all cases for selected categories
            // All 4 categories
            // Projection on media title
             if (query.getMediaTitle()) {
                    if (query.getMax()) {
                        String sql = "SELECT title, mid FROM (" +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Book' FROM Creator c, Media m, Book b WHERE c.cid = m.cid AND b.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Comic' FROM Creator c, Media m, Comic com WHERE c.cid = m.cid AND com.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Movie' FROM Creator c, Media m, Movie mov WHERE c.cid = m.cid AND mov.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'TV Show' FROM Creator c, Media m, TVShow tv WHERE c.cid = m.cid AND tv.mid = m.mid AND c.name LIKE ?" +
                                ")   as TEMP GROUP BY avg_rating DESC LIMIT 1";


                        try {
                            PreparedStatement statement = connection.prepareStatement(sql);
                            String keyword = "%" + query.getSearch() + "%";
                            statement.setString(1, keyword);
                            statement.setString(2, keyword);
                            statement.setString(3, keyword);
                            statement.setString(4, keyword);
                            ResultSet rs = statement.executeQuery();

                            while (rs.next()) {
                                Results results = new Results("empty", rs.getString(1), 0, "empty", rs.getInt(2));
                                ret.add(results);

                            }
                            System.out.println(ret.get(0).getMediaTitle());
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            connHandler.closeConnection(connection);
                        }
                    }
                    if (query.getMin()) {
                        String sql = "SELECT title, mid FROM (" +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Book' FROM Creator c, Media m, Book b WHERE c.cid = m.cid AND b.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Comic' FROM Creator c, Media m, Comic com WHERE c.cid = m.cid AND com.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Movie' FROM Creator c, Media m, Movie mov WHERE c.cid = m.cid AND mov.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'TV Show' FROM Creator c, Media m, TVShow tv WHERE c.cid = m.cid AND tv.mid = m.mid AND c.name LIKE ?" +
                                ")   as TEMP GROUP BY avg_rating ASC LIMIT 1";
                        try {
                            PreparedStatement statement = connection.prepareStatement(sql);
                            String keyword = "%" + query.getSearch() + "%";
                            statement.setString(1, keyword);
                            statement.setString(2, keyword);
                            statement.setString(3, keyword);
                            statement.setString(4, keyword);
                            ResultSet rs = statement.executeQuery();

                            while (rs.next()) {
                                  Results results = new Results("empty", rs.getString(1), 0, "empty", rs.getInt(2));
                                  ret.add(results);
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            connHandler.closeConnection(connection);
                        }
                    }
                    if(!query.getMax() && !query.getMin()) {
                        String sql = "SELECT title, mid FROM (" +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Book' FROM Creator c, Media m, Book b WHERE c.cid = m.cid AND b.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Comic' FROM Creator c, Media m, Comic com WHERE c.cid = m.cid AND com.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Movie' FROM Creator c, Media m, Movie mov WHERE c.cid = m.cid AND mov.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'TV Show' FROM Creator c, Media m, TVShow tv WHERE c.cid = m.cid AND tv.mid = m.mid AND c.name LIKE ?" +
                                ")   as TEMP GROUP BY avg_rating";
                        try {
                            PreparedStatement statement = connection.prepareStatement(sql);
                            String keyword = "%" + query.getSearch() + "%";
                            statement.setString(1, keyword);
                            statement.setString(2, keyword);
                            statement.setString(3, keyword);
                            statement.setString(4, keyword);
                            ResultSet rs = statement.executeQuery();
                            while (rs.next()) {
                                Results results = new Results("empty", rs.getString(1), 0, "empty", rs.getInt(2));
                                ret.add(results);
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            connHandler.closeConnection(connection);
                        }
                    }
                    }
                    // No projection, get all the results
                    else {
                    if (query.getMax()) {
                        String sql = "SELECT * FROM (" +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Book' FROM Creator c, Media m, Book b WHERE c.cid = m.cid AND b.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Comic' FROM Creator c, Media m, Comic com WHERE c.cid = m.cid AND com.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Movie' FROM Creator c, Media m, Movie mov WHERE c.cid = m.cid AND mov.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'TV Show' FROM Creator c, Media m, TVShow tv WHERE c.cid = m.cid AND tv.mid = m.mid AND c.name LIKE ?" +
                                ") as TEMP GROUP BY avg_rating DESC LIMIT 1";
                        try {
                            PreparedStatement statement = connection.prepareStatement(sql);
                            String keyword = "%" + query.getSearch() + "%";
                            statement.setString(1, keyword);
                            statement.setString(2, keyword);
                            statement.setString(3, keyword);
                            statement.setString(4, keyword);

                            ResultSet rs = statement.executeQuery();

                            while (rs.next()) {
                                Results results = new Results(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getString(5), rs.getInt(4));
                                    ret.add(results);
                                }
                                System.out.println(ret.get(0).getMediaTitle());

                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            connHandler.closeConnection(connection);
                        }
                    }
                    if (query.getMin()) {
                        String sql = "SELECT * FROM (" +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Book' FROM Creator c, Media m, Book b WHERE c.cid = m.cid AND b.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Comic' FROM Creator c, Media m, Comic com WHERE c.cid = m.cid AND com.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Movie' FROM Creator c, Media m, Movie mov WHERE c.cid = m.cid AND mov.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'TV Show' FROM Creator c, Media m, TVShow tv WHERE c.cid = m.cid AND tv.mid = m.mid AND c.name LIKE ?" +
                                ")   as TEMP GROUP BY avg_rating ASC LIMIT 1";
                        try {
                            PreparedStatement statement = connection.prepareStatement(sql);
                            String keyword = "%" + query.getSearch() + "%";
                            statement.setString(1, keyword);
                            statement.setString(2, keyword);
                            statement.setString(3, keyword);
                            statement.setString(4, keyword);
                            ResultSet rs = statement.executeQuery();
                            while (rs.next()) {
                                Results results = new Results(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getString(5), rs.getInt(4));
                                ret.add(results);
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            connHandler.closeConnection(connection);
                        }
                    }
                    if (!query.getMax() && !query.getMin()) {
                        String sql = "SELECT * FROM (" +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Book' FROM Creator c, Media m, Book b WHERE c.cid = m.cid AND b.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Comic' FROM Creator c, Media m, Comic com WHERE c.cid = m.cid AND com.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'Movie' FROM Creator c, Media m, Movie mov WHERE c.cid = m.cid AND mov.mid = m.mid AND c.name LIKE ?" +
                                " UNION " +
                                "SELECT DISTINCT c.name, m.title, m.avg_rating, m.mid, 'TV Show' FROM Creator c, Media m, TVShow tv WHERE c.cid = m.cid AND tv.mid = m.mid AND c.name LIKE ?" +
                                ")   as TEMP GROUP BY avg_rating";
                        try {
                            PreparedStatement statement = connection.prepareStatement(sql);
                            String keyword = "%" + query.getSearch() + "%";
                            statement.setString(1, keyword);
                            statement.setString(2, keyword);
                            statement.setString(3, keyword);
                            statement.setString(4, keyword);
                            ResultSet rs = statement.executeQuery();
                            while (rs.next()) {
                                Results results = new Results(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getString(5), rs.getInt(4));
                                ret.add(results);
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            connHandler.closeConnection(connection);
                        }
                    }
                    }
                }

            // Filter all results based on query
            List<Results> filteredRet = new ArrayList<>();
            HashMap<String, Boolean> bools = new HashMap<>();
            if (query.getBook()) { bools.put("Book", true); }
            if (query.getComic()) { bools.put("Comic", true); }
            if (query.getMovie()) { bools.put("Movie", true); }
            if (query.getTv()) { bools.put("TV Show", true); }

            for (int i = 0; i < ret.size(); i++) {
                if (bools.containsKey(ret.get(i).getCategory()) || ret.get(i).getCategory().equals("empty")) {
                    filteredRet.add(ret.get(i));
                }
            }

            for (int i = 0; i < filteredRet.size(); i++) {
                System.out.println(filteredRet.get(i).getCategory());
                System.out.println(filteredRet.get(i).getCreator());
                System.out.println(filteredRet.get(i).getMediaTitle());
                System.out.println(filteredRet.get(i).getRating());
            }
            return filteredRet;
        }





    public int findTitle(String title) {
        String sql = "SELECT mid, title FROM Media WHERE title = ?";
        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("mid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connHandler.closeConnection(connection);
        }
        return -1;
    }

    public int findCreator(String name) {
        String sql = "SELECT cid, name FROM Creator WHERE name = ?";
        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("cid");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            connHandler.closeConnection(connection);
        }
        return -1;
    }

    public static void main(String []args) {
        Query query = new Query("Joss", false, false, true, true, true, false, false);
        MediaStatements mediaStatements = new MediaStatements();
        mediaStatements.findMedia(query);
    }

}

