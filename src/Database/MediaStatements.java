package Database;

import Results.Results;
import Query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MediaStatements {

    public List<Results> findMedia(Query query) {
        List<Results> ret = new ArrayList<>();

        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();

        // Enumerate all cases for selected categories
        if (query.getBook() && query.getComic() && query.getMovie() && query.getTv()) {
            // Projection on Creator
            if(query.getCreator()) {
                if (query.getMax()) {
                    String sql = "";
                }
                if (query.getMin()) {
                    String sql = "";
                }
                if (query.getAvgMax()) {
                    String sql = "";
                }
                if (query.getAvgMin()) {
                    String sql = "";
                }
                else {
                    String sql = "";
                }
            }
            // Projection on media title
            if(query.getMediaTitle()) {
                if (query.getMax()) {
                    String sql = "";
                }
                if (query.getMin()) {
                    String sql = "";
                }
                if (query.getAvgMax()) {
                    String sql = "";
                }
                if (query.getAvgMin()) {
                    String sql = "";
                }
                else {
                    String sql = "";
                }
            }
            // No projection on creator or media title, so just check if max, min, avgMax, or avgMin were selected:
            if (query.getMax()) {
                String sql = "";
            }
            if (query.getMin()) {
                String sql = "";
            }
            if (query.getAvgMax()) {
                String sql = "";
            }
            if (query.getAvgMin()) {
                String sql = "";
            }
            else {
                String sql = "";
            }
        }

        

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
        }
        catch (Exception e) {
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

}