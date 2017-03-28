package Database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserStatements {

    public int findUserId(String username) {
        String sql = "SELECT uid FROM User WHERE username = ?";
        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("uid");
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
