/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Super.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;



/**
 *
 * @author raren
 */
public class ProfileDB {
    
     public static boolean createUserProfile(User user){  //inserts a new profile for the user on registration, only called once for each user.
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
         
        boolean profileAdded = false;
        
        //LocalDateTime datetime = new LocalDateTime.now();
        
        
        
        int result = -1;
        
        String query = """
                       INSERT INTO profile
                       (profile_name, user_id_owned_by, profile_status)
                       VALUES
                       (?, ?, ?);
                       """;
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getUserID());
            ps.setString(3, "profile created " + "Add A Status");

            result = ps.executeUpdate();
            System.out.println("ProfileDB -> createUserProfile() -> Insert executed -> rows effected -> " + result);
            profileAdded = true;

        }catch(SQLException ex){
            System.out.println("\nProfileDB -> createUserProfile() failed-> \nExcetion -> " + ex +"\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
         
         return profileAdded;
     }
     
     
     /**
      * deletes the profile a  user owns, that is created when they register
      * @param userID
      * @return 
      */
     public static boolean deleteUserProfile(int userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean profileDeleted = false; 
        
        String query = """
                       DELETE FROM profile
                       WHERE user_id_owned_by  = ?;
                       """;
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("ProfileDB -> deleteUserProfile() -> Delete executed -> rows effected -> " + result);
            profileDeleted = true;

        }catch(SQLException ex){
            System.out.println("\nProfileDB -> deleteUserProfile() failed-> \nExcetion -> " + ex +"\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return profileDeleted;
    } 
}
