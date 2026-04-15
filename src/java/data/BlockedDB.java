/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Super.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.sql.ResultSet;

/**
 *
 * @author se757706
 */
public class BlockedDB {

    public static boolean blockUser(int userID, int blockedUserID) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean success = false;

        String query = """
                        INSERT INTO user_blocked 
                        (user_id, user_blocked_id) 
                        VALUES 
                        (?, ?);
                      """;

        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);
        ps.setInt(2, blockedUserID);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("successfully blocked user");
            success = true;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return success;
    }
    
    public static boolean unblockUser(int userID, int blockedUserID) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean success = false;

        String query = """
                        REMOVE FROM user_blocked 
                        WHERE user_id = ? 
                        AND user_blocked_id = ?
                      """;

        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);
        ps.setInt(2, blockedUserID);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            success = true;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return success;
    }
    
    public static LinkedHashMap<Integer, String> getBlockedUsers(int userID) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedHashMap<Integer, String> users = new LinkedHashMap<Integer, String>();

        String query = """
                       SELECT user.username, user_blocked.user_id, user_blocked.user_blocked_id 
                       FROM user JOIN user_blocked 
                       ON user.user_id = user_blocked.user_blocked_id 
                       WHERE user_blocked.user_id = ?
                       """;

        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);

        rs = ps.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                Integer blockedUserID = rs.getInt("user_blocked_id");
                String blockedUsername = rs.getString("username");

                User user = new User();
                user.setUserID(blockedUserID);
                user.setUsername(blockedUsername);
                users.put(blockedUserID, blockedUsername);
            }
        }
        
        DBUtil.closeResultSet(rs);

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return users;
    }
}
