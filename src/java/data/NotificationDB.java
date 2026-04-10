/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

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
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return notificationsDeleted;
    }

    public HashMap<Integer, Notification> getAllUnviewedNotificationsByUserID(int UserID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        HashMap notificationsHashMap = new HashMap();

        //TODO: finish function
        String query = """
                       """;

        return notificationsHashMap;

    }

    public boolean insertNotificationForUserByUserID(int UserID) { //call anytime a message is sent or a user follows another user(for the user followed)
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean notificationInserted = false;

        String query = """
                     
                     """;

        return notificationInserted;
    }

    public boolean setNotificationViewedByNotificationID(int NotificationID) { //call when user clicks notification icon
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        int result = -1;
        
        boolean notificationViewedSetTrue = false;
        
        String query = """
                     
                     """;
        return notificationViewedSetTrue;
        
        
    }

}
