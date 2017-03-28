package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHandler {

    public Connection openConnection() {
        String connectionURL = "jdbc:mysql://localhost:3306/304Project";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            System.out.println("Connected to DB");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void closeConnection(Connection connection) {
        try {
            connection.close();
            System.out.println("Connection closed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();
        connHandler.closeConnection(connection);
    }


}
