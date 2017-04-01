package Database;

import Results.Results;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewLaterStatements {

    public void addToViewLater(int uid, int mid) {
        String sql = "INSERT INTO ViewLater (uid, mid) VALUES (?, ?)";

        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, uid);
            statement.setInt(2, mid);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connHandler.closeConnection(connection);
        }
    }

    public void deleteFromViewLater(int uid, int mid) {
        String sql = "DELETE FROM ViewLater WHERE uid = ? AND mid = ?";

        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, uid);
            statement.setInt(2, mid);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connHandler.closeConnection(connection);
        }
    }



    public List<Results> retrieveViewLater(int uid) {
        List<Results> ret = new ArrayList<>();
        String sql = "SELECT * FROM (" +
                "SELECT DISTINCT c.name, m.title, m.mid, m.avg_rating, 'Book' FROM Creator c, Media m, Book b, RegularUser u, ViewLater v WHERE u.uid = ? AND c.cid = m.cid AND b.mid = m.mid AND u.uid = v.uid AND v.mid = m.mid" +
                " UNION " +
                " SELECT DISTINCT c.name, m.title, m.mid, m.avg_rating, 'Comic' FROM Creator c, Media m, Comic com, RegularUser u, ViewLater v WHERE u.uid = ? AND c.cid = m.cid AND com.mid = m.mid AND u.uid = v.uid AND v.mid = m.mid" +
                " UNION " +
                " SELECT DISTINCT c.name, m.title, m.mid, m.avg_rating, 'Movie' FROM Creator c, Media m, Movie mov, RegularUser u, ViewLater v WHERE u.uid = ? AND c.cid = m.cid AND mov.mid = m.mid AND u.uid = v.uid AND v.mid = m.mid" +
                " UNION " +
                " SELECT DISTINCT c.name, m.title, m.mid, m.avg_rating, 'TV Show' FROM Creator c, Media m, TVShow tv, RegularUser u, ViewLater v  WHERE u.uid = ? AND c.cid = m.cid AND tv.mid = m.mid AND u.uid = v.uid AND v.mid = m.mid" +
                ")   as TEMP GROUP BY avg_rating";

        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, uid);
            statement.setInt(2, uid);
            statement.setInt(3, uid);
            statement.setInt(4, uid);
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

        return ret;

    }

}
