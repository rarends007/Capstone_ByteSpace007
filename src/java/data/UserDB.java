/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Super.User;
import data.ConnectionPool;
import java.sql.*;
import data.DBUtil;
import java.util.ArrayList;
import java.util.HashMap;

import business.bytespace.Super.User;

/**
 *
 * @author raren
 */
public class UserDB {

    /**
     * Accepts a user object that is used to process all user fields into the
     * database as appropriate and insert the new user.
     *
     * @param user
     * @param errors
     * @return
     */
    public static boolean insertUser(User user, ArrayList errors) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean userRegistered = false;

        //todo, make a function to insert Admins later, in the JSP there needs, getting the role from the User user obj
        //to be a hidden field that passes MEMBER or ADMIN and then it is passed to user in the PublicController -> 
        //Register Switch differentiated by an if() in the switch and then calls either insertAdmin, or insertMember
        //only the admin portal will have a hidden input role assigned to "ADMIN" when passed to the PublicController
        String query = """
                       INSERT INTO user
                       (username, firstname, middlename, lastname, credential)
                       VALUES
                       (?, ?, ?, ?, ?);
                       """;
        
        
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); //https://stackoverflow.com/questions/4224228/preparedstatement-with-statement-return-generated-keys
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getMiddlename());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getCredential());

            result = ps.executeUpdate();
            System.out.println("New User added, not the role yet though.");
            
            //ResultSet rs = 
            
            if (result != -1) {
                query = """
                               INSERT INTO role
                               (user_id, rolename)
                               VALUES
                               (?, ?);
                               """;
                ps = connection.prepareStatement(query);
                ps.setInt(1, getUserID(user.getUsername()));
                ps.setString(2, user.getRole());
            }
            System.out.println("New User added, and now User role added too.");
            userRegistered = true;

        } catch (SQLException ex) {
            System.out.println("\nUserDB -> insertUserMember() -> Need to update Validation, Malformed User -> \nExcetion -> " + ex);
            //if("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry".equals(ex.toString().trim().substring(0, 66))){ obsolete
            if (ex.toString().contains("Duplicate entry")) {
                System.out.println("UserDB -> insertUserMember -> This username already exists.");
                errors.add("Username already exists, choose another one.");
            }

        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return userRegistered;
    }

    /**
     *
     * @param username
     * @return userID int
     */
    public static int getUserID(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int userID = -1;

        String query = """
                       SELECT user_id
                       FROM user
                       WHERE username = ?;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs != null) {
                rs.next();
                userID = rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            System.out.println("error retrieving userID -> UserDB -> getUserID()");
        }

        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return userID;
    }
    
    /**
     * Gets the username for the specific user given the userID
     * @param userID
     * @return String username
     */
     public static String getUsername(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String username = "";

        String query = """
                       SELECT username
                       FROM user
                       WHERE user_id = ?;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            if (rs != null) {
                rs.next();
                username = rs.getString("username");
            }
        } catch (SQLException ex) {
            System.out.println("error retrieving username -> UserDB -> getUsername()");
        }finally{
             DBUtil.closeResultSet(rs);

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return username;
    }
    
    /**
     * gets a user object based on the username.
     * @param usernamePassed
     * @return User
     */
    public static User getUser(String usernamePassed) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        usernamePassed = usernamePassed.trim();
        User user = null;

        String query = """
                       SELECT *
                       FROM user AS u JOIN user_role AS ur
                       	ON u.user_id = ur.User_id
                       WHERE u.username = ?;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usernamePassed);
            
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String credential = rs.getString("credential");
                    String role = rs.getString("rolename");

                    user = new User(userID, username, firstname, middlename, lastname, credential, role);

                }

            }
        } catch (SQLException ex) {
            System.out.println("error retrieving userID -> UserDB -> getAllUser() -> " + ex);
        }

        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return user;
    }
    
    /**
     * gets a user object based on the userID.
     * @param passedInUserID (int)
     * @return User
     */
    public static User getUser(int passedInUserID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        User user = null;

        String query = """
                       SELECT *
                       FROM user AS u JOIN user_role AS ur
                       	ON u.user_id = ur.User_id
                       WHERE u.user_id = ?;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, passedInUserID);
            
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String credential = rs.getString("credential");
                    String role = rs.getString("rolename");

                    user = new User(userID, username, firstname, middlename, lastname, credential, role);

                }

            }
        } catch (SQLException ex) {
            System.out.println("error retrieving userID -> UserDB -> getAllUser() -> " + ex);
        }

        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return user;
    }

    /**
     *
     * @return HashMap<Integer, User> userHashMap -> returns a hash map of all
     * users in the database
     */
    public static HashMap<Integer, User> getAllUsers() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<Integer, User> userHashMap = new HashMap<>();
        String query = """
                       SELECT *
                       FROM user;
                       """;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String credential = rs.getString("credential");

                    User user = new User(userID, username, firstname, middlename, lastname, credential);

                    userHashMap.put(userID, user);
                }

            }
        } catch (SQLException ex) {
            System.out.println("error retrieving userID -> UserDB -> getAllUser()");
        }

        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return userHashMap;
    }
    
    public static HashMap<Integer, User> getSuggestedUsers(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<Integer, User> userHashMap = new HashMap<>();
        String query = """
                       SELECT *
                       FROM user 
                       WHERE user_id NOT IN (SELECT following_id FROM user_followers WHERE user_id = ?) AND user_id != ?
                       LIMIT 5;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, userID);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String credential = rs.getString("credential");

                    User user = new User(userId, username, firstname, middlename, lastname, credential);

                    userHashMap.put(userId, user);
                }

            }
        } catch (SQLException ex) {
            System.out.println("error retrieving userID -> UserDB -> getAllUser()");
        }

        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return userHashMap;
    }

    /**
     *
     * @param userID int
     * @return boolean indicating if deleted -> true if deleted, false if not
     */
    public static boolean deleteUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean userDeleted = false;

        String query = """
                       DELETE FROM user
                       WHERE user_id = ?;
                       """;
        try {
            ImageDB.deleteAllImagesForUser(userID);
            CommentDB.deleteAllCommentsForUser(userID);
            PostDB.deleteAllPostsForUser(userID); //if a post is deleted I set the DB to delete all associated fk on cascade -> i.e. comments, becasue without a post comments are not needed, however, I left in the comment delete because I don't feel like removing it.
            LogDB.deleteLogsForUser(userID);
            MessageDB.deleteMessagesForUser(userID);
            MessageDB.setAllRecieverMessageUserNamesBlankForUser(userID);
            NotificationDB.deleteNotificationsForUser(userID);
            ProfileDB.deleteUserProfile(userID);
            ReportDB.deleteAllReportsForUser(userID); //must execute in order so all fk are deleted then then finally the parent user record
            FollowersDB.deleteAllFollowingByUserID(userID);
            FollowersDB.deleteAllFollowedByUserID(userID);
            
            deleteRole(userID);

            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("UserDB -> deleteUser() -> Delete executed -> rows effected -> " + result);
            userDeleted = true;

        } catch (SQLException ex) {
            System.out.println("\nUserDB -> deleteUser() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return userDeleted;
    }

    public static boolean deleteRole(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean userDeleted = false;

        String query = """
                       DELETE FROM user_role
                       WHERE user_id = ?;
                       """;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("UserDB -> deleteUser() -> Delete executed -> rows effected -> " + result);
            userDeleted = true;

        } catch (SQLException ex) {
            System.out.println("\nUserDB -> deleteUser() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return userDeleted;
    }

}
