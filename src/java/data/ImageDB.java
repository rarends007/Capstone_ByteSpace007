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
public class ImageDB {
     public static boolean deleteAllImagesForUser(int userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean imagesDeleted = false; 
        
        String query = """
                       DELETE FROM image
                       WHERE user_id = ?;
                       """;
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("ImageDB -> deleteAllImagesForUser() -> Delete executed -> rows effected -> " + result);
            imagesDeleted = true;

        }catch(SQLException ex){
            System.out.println("ImageDB -> deleteAllImagesForUser() failed-> \nExcetion -> " + ex +"\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return imagesDeleted;
    } 
}
