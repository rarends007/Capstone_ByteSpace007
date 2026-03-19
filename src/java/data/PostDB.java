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
public class PostDB {
    public static boolean deleteAllPostsForUser(int userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean postsDeleted = false; 
        
        String query = """
                       DELETE FROM post
                       WHERE user_id = ?;
                       """;
      
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("PostDB -> deleteAllPostsForUser() -> Delete executed -> rows effected -> " + result);
            postsDeleted = true;

        }catch(SQLException ex){
            System.out.println("\nPostDB -> deleteAllPostsForUser() failed-> \nExcetion -> " + ex +"\n") ;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return postsDeleted;
    } 
}
