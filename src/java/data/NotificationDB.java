/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import business.bytespace.Notification;

/**
 *
 * @author raren
 */
public class NotificationDB {

    public static boolean deleteNotificationsForUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean notificationsDeleted = false;

        String query = """
                       DELETE FROM notification
                       WHERE notified_user_id = ?;
                       """;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("NotificationsDB -> deleteNotificationsForUser() -> Delete executed -> rows effected -> " + result);
            notificationsDeleted = true;

        } catch (SQLException ex) {
            System.out.println("NotificationsDB -> deleteNotificationsForUser() failed-> \nExcetion -> " + ex + "\n");
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return notificationsDeleted;
    }

    /**
     * Insert a notification for a user based on user ID.
     *
     * @param UserID
     * @param text
     * @return (boolean) true if successful
     */
    public static boolean insertNotificationForUserByUserID(int UserID, String text) { //call anytime a message is sent or a user follows another user(for the user followed)
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean notificationInserted = false;

        String query = """
                       INSERT INTO notification
                       (notification_text, notified_user_id)
                       VALUES
                       (?, ?);
                     """;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, text);
            ps.setInt(2, UserID);
            result = ps.executeUpdate();
            System.out.println("insertNotificationForUserByUserID() -> noficication inserted : " + result);
            notificationInserted = true;
        } catch (SQLException ex) {
            System.err.println(".insertNotificationForUserByUserID() -> \n\tSQL Error: " + ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return notificationInserted;
    }

    /**
     * Sets all notifications to viewed based on the user ID.
     *
     * @param userID
     * @return (boolean) true if successful
     */
    public static boolean setNotificationViewedByUserID(int userID) { //call when user clicks notification icon
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean notificationViewedSetTrue = false;

        String query = """
                     UPDATE notification
                     SET is_viewed = true
                     WHERE notified_user_id = ?;
                     """;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();

            if (result != -1 && result != 0) {
                notificationViewedSetTrue = true;
            }
        } catch (SQLException ex) {
            System.err.println(".setNotificationViewedByUserID() -> \n\tSQLException: " + ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return notificationViewedSetTrue;

    }

    /**
     * Fetches a HashMap of notifications by type  <Integer, Notification> from
     * the database.
     *
     * @param userID
     * @return HashMap<Integer, Notification>
     */
    public static HashMap<Integer, Notification> getAllNotificationsForUserByUserID(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        boolean notificationFetched = false;
        HashMap<Integer, Notification> notificationsMap = new HashMap();

        ResultSet rs = null;

        String query = """
                       SELECT notification_id, notification_text, is_viewed, notified_user_id
                       FROM notification
                       WHERE notified_user_id = ?;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            rs = ps.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();

                int notificationID = rs.getInt("notification_id");

                notification.setNotificationID(rs.getInt(notificationID));
                notification.setNotificationInfo(rs.getString("notification_text"));
                notification.setIsViewed(rs.getBoolean("is_viewed"));
                notification.setNotifiedUserID(rs.getInt("user_id"));

                notificationsMap.put(notificationID, notification);

            }
        } catch (SQLException ex) {
            System.err.println(".getAllNotificationsForUserByUserID() -> \n\tSQLException: " + ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return notificationsMap;
    }
    
    /**
     * fetches all Viewed or Unviewed notifications for a user by user ID. Supply Param isViewed with
     * false -> unviewed
     * true -> viewed
     * @param UserID
     * @param isViewed
     * @return HashMap<Integer, Notification>
     */
    public HashMap<Integer, Notification> getAllViewedORUnviewedNotificationsByUserID(int userID, boolean isViewed) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        HashMap<Integer, Notification> notificationsMap = new HashMap();

        ResultSet rs = null;

        String query = """
                       SELECT notification_id, notification_text, is_viewed, notified_user_id
                       FROM notification
                       WHERE notified_user_id = ? AND is_viewed = ?;;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setBoolean(2, isViewed);

            rs = ps.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();

                int notificationID = rs.getInt("notification_id");

                notification.setNotificationID(rs.getInt(notificationID));
                notification.setNotificationInfo(rs.getString("notification_text"));
                notification.setIsViewed(rs.getBoolean("is_viewed"));
                notification.setNotifiedUserID(rs.getInt("user_id"));

                notificationsMap.put(notificationID, notification);

            }
        } catch (SQLException ex) {
            System.err.println(".getAllNotificationsForUserByUserID() -> \n\tSQLException: " + ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return notificationsMap;
    }

}
