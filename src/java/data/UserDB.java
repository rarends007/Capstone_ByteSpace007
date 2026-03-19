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
    
     private void copyPasteMeToEasyAddNewDBFunction()
             throws SQLException{ //remember to catch the exception in the calling code, 
         //OR you can just catch it in this func you are defining
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; //remove if not USING a SELECT statement -> returning a resultset
        
        String query = """
                       MySQL Here ?;
                       """;
        
        ps = connection.prepareStatement(query);
        //do stuff
        //ps.setObject(1, obj));
        
        /*
         ###if you update, insert, delete
                int result = ps.executeUpdate(); returns a int for num rows affected
        
                return result; if null it failed. -> place where it needs to go \/
        
         ###if you SELECT
                rs.executeQuery(); //put here
                if(rs != null){
                    while(rs.next()) { //greater than one RECORD/ROW returned from SELECT
                        Integer userid = rs.getInt("userid");
                    }
               }
               if(rs != null){
                    exactly one RECORD/ROW -> follow this format
                    Integer userid = rs.getInt("userid");
                }
                return user(s) or whatever value you are returning below \/
        */
        
        rs = ps.executeQuery(); //remove if not using SELECT and thus returning a resultset, place in proper spot above /\
        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset
        
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        //put return statement here
    }
     
    public static boolean insertUserMember(User user, ArrayList errors){
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
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getMiddlename());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getCredential());
            
            result = ps.executeUpdate();
            System.out.println("New User added, not the role yet though.");
            
            if(result != -1){
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
            
        }catch(SQLException ex){
            System.out.println("\nUserDB -> insertUserMember() -> Need to update Validation, Malformed User -> \nExcetion -> " + ex) ;
            if("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry".equals(ex.toString().trim().substring(0, 66))){
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
     public static int getUserID(String username){
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
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery(); 
            
            if(rs != null){
               rs.next();
               userID = rs.getInt("user_id");
             }
        }catch(SQLException ex){
            System.out.println("error retrieving userID -> UserDB -> getUserID()");
        }
     
        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset
        
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
       return userID;
    }
     
     /**
      * 
      * @return HashMap<Integer, User> userHashMap -> returns a hash map of all users in the database 
      */
     public static HashMap<Integer, User> getAllUsers(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; 
        
        HashMap<Integer, User> userHashMap = new HashMap<>();
        String query = """
                       SELECT *
                       FROM user;
                       """;
        
        try{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            if(rs != null){
               while(rs.next()){
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
        }catch(SQLException ex){
            System.out.println("error retrieving userID -> UserDB -> getAllUser()");
        }
     
        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset
        
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
       return userHashMap;
    }
    
}
