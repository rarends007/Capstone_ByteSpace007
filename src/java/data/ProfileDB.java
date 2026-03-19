/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author raren
 */
public class ProfileDB {
    
     public static boolean deleteAnyProfilesForUser(int userID){
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
            System.out.println("ProfileDB -> deleteAnyProfilesForUser() -> Delete executed -> rows effected -> " + result);
            profileDeleted = true;

        }catch(SQLException ex){
            System.out.println("\nProfileDB -> deleteAnyProfilesForUser() failed-> \nExcetion -> " + ex +"\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return profileDeleted;
    } 
}
