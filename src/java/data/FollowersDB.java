/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Super.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

/**
 *
 * @author Scout Ernst <se757706@southeast.edu>
 */
public class FollowersDB {

    //retrieves list of users that this user is following
    public static LinkedHashMap<Integer, String> getFollowing(int userID)
            throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedHashMap<Integer, String> following = new LinkedHashMap();

        String query = """
                       SELECT user.username, user_followers.user_id, user_followers.following_id 
                       FROM user JOIN user_followers 
                       ON user.user_id = user_followers.following_id 
                       WHERE user_followers.user_id = ?
                       """;

        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);

        rs = ps.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                Integer followingUserID = rs.getInt("following_id");
                String followingUsername = rs.getString("username");

                User user = new User();
                user.setUserID(followingUserID);
                user.setUsername(followingUsername);
                following.put(followingUserID, followingUsername);
            }
        }

        DBUtil.closeResultSet(rs);

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return following;
    }
    
    //retrieve list of users that are following this user
    public static LinkedHashMap<Integer, String> getFollowers(int userID)
            throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedHashMap<Integer, String> followers = new LinkedHashMap();

        String query = """
                       SELECT user.username, user_followers.user_id, user_followers.following_id 
                       FROM user JOIN user_followers 
                       ON user.user_id = user_followers.user_id
                       WHERE user_followers.following_id = ?
                       """;

        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);

        rs = ps.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                Integer followerID = rs.getInt("user_id");
                String followerUsername = rs.getString("username");

                User user = new User();
                user.setUserID(followerID);
                user.setUsername(followerUsername);
                followers.put(followerID, followerUsername);
            }
        }

        DBUtil.closeResultSet(rs);

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return followers;
    }
}
