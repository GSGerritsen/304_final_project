package Database;

import Stats.Stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatStatements {

    public List<Stats> generateMax() {

        String sql = "SELECT c.cid, c.name, MAX(groups.average) " +
                "FROM creator c, (SELECT cid, AVG(avg_rating) as average " +
                "                FROM media " +
                "                GROUP BY cid) as groups " +
                "where groups.cid = c.cid";

        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();

        List<Stats> ret = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Stats stats = new Stats(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                ret.add(stats);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connHandler.closeConnection(connection);
        }

        return ret;

    }

    public List<Stats> generateMin() {

        String sql = "SELECT c.cid, c.name, MIN(groups.average) " +
                "FROM creator c, (SELECT cid, AVG(avg_rating) as average " +
                "                FROM media " +
                "                GROUP BY cid) as groups " +
                "where groups.cid = c.cid";

        ConnectionHandler connHandler = new ConnectionHandler();
        Connection connection = connHandler.openConnection();

        List<Stats> ret = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Stats stats = new Stats(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                ret.add(stats);
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
