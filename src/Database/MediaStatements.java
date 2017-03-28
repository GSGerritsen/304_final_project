package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MediaStatements {

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
