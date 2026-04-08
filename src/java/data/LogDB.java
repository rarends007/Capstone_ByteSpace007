/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 *
 * @author raren
 */
public class LogDB {

    public static boolean deleteLogsForUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean logDeleted = false;

        String query = """
                       DELETE FROM log
                       WHERE logged_user_id = ?
                       """;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("LogDB -> deleteLogsForUser() -> Delete executed -> rows effected -> " + result);
            logDeleted = true;

        } catch (SQLException ex) {
            System.out.println("LogDB -> deleteLogsForUser() failed-> \nException -> " + ex + "\n");
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }

        return logDeleted;
    }

    public static void createLoginLog(int userID, int actionID, String logText, LocalDateTime loggedDate) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        String query
                = """
                INSERT INTO log
                (logged_user_id, logged_action_id, log_text, logged_date)
                values
                (?, ?, ?, ?)
                """;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, actionID);
            ps.setString(3, logText);
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(loggedDate));

            result = ps.executeUpdate();
            System.out.println("LogDB -> createLoginLog() -> Creation Executed -> rows effected -> " + result);
        } catch (SQLException ex) {
            System.out.println("LogDB -> createLoginLog() failed-> \nException -> " + ex + "\n");
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }
    }

    public static HashMap<Integer, Log> getAllLoginLogs() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;
        ResultSet rs = null;
        HashMap<Integer, Log> logHashMap = new HashMap<>();

        String query
                = """
                SELECT *
                FROM log
                WHERE logged_action_id = 1
                """;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int logID = rs.getInt("log_id");
                    int logUserID = rs.getInt("logged_user_id");
                    int logActionID = rs.getInt("logged_action_id");
                    String logText = rs.getString("log_text");
                    LocalDateTime loggedDateTime = rs.getObject("logged_date", LocalDateTime.class);

                    Log log = new Log(logID, logUserID, logActionID, logText, loggedDateTime);

                    logHashMap.put(logID, log);
                }
            }

        } catch (SQLException ex) {
            System.out.println("LogDB -> getAllLogs() failed-> \nException -> " + ex + "\n");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }
        return logHashMap;
    }

}
