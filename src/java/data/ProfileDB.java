/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Super.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;



/**
 *
 * @author raren
 */
public class ProfileDB {
    
    /**
     * Creates the users profile by inserting it into the database. Call ONLY right after the user and their role is submitted to the database on registration. Used in Utility.handleRegistration()
     * @param user
     * @return (boolean)
     */
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
         * This updates the profile photo and returns true if update is successul  and system messages 
         * @param userID
         * @param profilePhotoPath
         * @return (boolean)
         */
         public static boolean  updateProfilePhoto(int userID, String profilePhotoPath ){  //inserts a new profile for the user on registration, only called once for each user.
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;

            boolean profilePhotoUpdated = false;

            //LocalDateTime datetime = new LocalDateTime.now();



            int result = -1;

            String query = """
                           UPDATE profile
                           SET profile_photo_path = ?
                           WHERE user_id_owned_by = ?;
                           """;

            try{
                ps = connection.prepareStatement(query);
                ps.setString(1,profilePhotoPath);
                ps.setInt(2, userID);

                result = ps.executeUpdate();
                System.out.println("ProfileDB -> updateProfilePhoto() -> update executed -> rows effected -> " + result);
                profilePhotoUpdated = true;

            }catch(SQLException ex){
                System.out.println("\nProfileDB -> updateProfilePhoto() failed-> \nExcetion -> " + ex +"\n");
            }

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

             return profilePhotoUpdated;
     }
         
     public static String getProfilePhotoPath(int  userID){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            String profilePhotoPath = "";
            
            String query = """
                           SELECT profile_photo_path
                           FROM profile
                           WHERE user_id_owned_by = ?;
                           """;
            try{
                ps = connection.prepareStatement(query);
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                if(rs.next()){
                    profilePhotoPath = rs.getString("profile_photo_path");
                    return profilePhotoPath;
                }else{
                    return "";
                }
                
            }catch(SQLException ex){
                System.err.println(ex);
                return "";
            }finally{
                DBUtil.closeResultSet(rs); 
                DBUtil.closePreparedStatement(ps); 
                pool.freeConnection(connection);

            }
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
     
   
     
     
     
     
     
     
        public static boolean setUserStatus(int userID, String userStatus){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;

            boolean userStatusUpdated = false;



            int result = -1;

            String query = """
                           UPDATE profile
                           SET profile_status = ?
                           WHERE user_id_owned_by = ?;
                           """;

            try{
                ps = connection.prepareStatement(query);
                ps.setString(1,userStatus);
                ps.setInt(2, userID);

                result = ps.executeUpdate();
                System.out.println("ProfileDB -> setUserStatus() -> update executed -> rows effected -> " + result);
                userStatusUpdated = true;

            }catch(SQLException ex){
                System.out.println("\nProfileDB -> setUserStatus() failed-> \nException -> " + ex +"\n");
            }

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

            return userStatusUpdated;
    } 
     
    public static String getUserStatus(int userID){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            String userStatus = "";
            
            String query = """
                           SELECT profile_status
                           FROM profile
                           WHERE user_id_owned_by = ?;
                           """;
            try{
                ps = connection.prepareStatement(query);
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                
                if (rs.next()){
                userStatus = rs.getString("profile_status");
                }
                return userStatus;
                
                
            }catch(SQLException ex){
                System.err.println(ex);
                return "";
            }finally{
                DBUtil.closeResultSet(rs); 
                DBUtil.closePreparedStatement(ps); 
                pool.freeConnection(connection);

            }        
    }
}
