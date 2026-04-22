/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Comment;
import business.bytespace.Image;
import business.bytespace.Super.Post;
import business.bytespace.Super.User;
import static data.PostDB.getPostComments;
import static data.PostDB.getPostImages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public static int removeFollow(int userID, int followingID)
            throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = """
                       DELETE FROM user_followers 
                       WHERE user_id = ? 
                       AND following_id = ?
                       """;

        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);
        ps.setInt(2, followingID);

        int rows = ps.executeUpdate();

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return rows;
    }

    public static int addFollow(int userID, int followingID)
            throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = """
                       INSERT INTO user_followers 
                       (user_id, following_id)
                       VALUES (?, ?)
                       """;

        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);
        ps.setInt(2, followingID);

        int rows = ps.executeUpdate();

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return rows;
    }

    /**
     * Deletes all follow entries for the provider user id.
     * @param userID int
     * @return boolean
     */
    public static boolean deleteAllFollowingByUserID(int userID) {
        //TODO: 
        //1. write the logic to delete all of the followers for a user to be deleted
        // 2. Call that function in the UserDB function that is called when an admin wants to delete a user from the database on the user to be deleted.
        // There will be a database error when an admin attempts to do so until i do.
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        boolean userFollowerEntriesDeleted = false;
        int result = -1;
        
        String query = """
                       DELETE
                       FROM user_followers
                       WHERE user_id = ?;
                       """;
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            result = ps.executeUpdate();
            System.out.println("Rows deleted: " + result);
            
            userFollowerEntriesDeleted = true;
        }catch(SQLException ex){
            System.err.println("FollowersDB -> .deleteAllFollowingByUserID() -> \n\tSQLException: " + ex );
        }
        return userFollowerEntriesDeleted;
    }
    
    /**
     * Deletes all of the entries from the user_follower table where the provided userID is being followed by another member
     * @param userID
     * @return 
     */
    public static boolean deleteAllFollowedByUserID(int userID) {
        //TODO: 
        //1. write the logic to delete all of the followers for a user to be deleted
        // 2. Call that function in the UserDB function that is called when an admin wants to delete a user from the database on the user to be deleted.
        // There will be a database error when an admin attempts to do so until i do.
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        boolean userFollowerEntriesDeleted = false;
        int result = -1;
        
        String query = """
                       DELETE
                       FROM user_followers
                       WHERE following_id = ?;
                       """;
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            result = ps.executeUpdate();
            System.out.println("Rows deleted: " + result);
            
            userFollowerEntriesDeleted = true;
        }catch(SQLException ex){
            System.err.println("FollowersDB -> .deleteAllFollowingByUserID() -> \n\tSQLException: " + ex );
        }
        return userFollowerEntriesDeleted;
    }
}
