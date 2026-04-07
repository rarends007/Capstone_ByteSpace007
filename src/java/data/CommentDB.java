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
public class CommentDB {
    
     public static boolean deleteAllCommentsForUser(int userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean commentDeleted = false; 
        
        String query = """
                       DELETE FROM comment
                       WHERE commenting_user_id = ?;
                       """;
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("CommentsDB -> deleteAllCommentsForUser() -> Delete executed -> rows effected -> " + result);
            commentDeleted = true;

        }catch(SQLException ex){
            System.out.println("CommentsDB -> deleteAllCommentsForUser() failed-> \nExcetion -> " + ex +"\n") ;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return commentDeleted;
    } 
   
   /**
    * Inserts a comment on a post.
    * @param userID int
    * @param postID int
    * @param commentText String
    * @return boolean, true if successful
    */
   public static boolean insertComment(int userID, int postID, String commentText){
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement ps = null;
       
       boolean commentAdded = false;
        
       String query = """
                        INSERT INTO comment
                        (commenting_user_id, post_id, comment_text)
                        VALUES
                        (?, ?, ?); -- int, int string
                      """;
       
       try{
           ps = connection.prepareStatement(query);
           ps.setInt(1, userID);
           ps.setInt(2, postID);
           ps.setString(3, commentText);
           
           int rowsAffected = ps.executeUpdate();
           System.out.println("comments inserted -> " + rowsAffected);
           commentAdded = true;
       }catch (SQLException ex){
           System.err.println("commnetDB -> insertCommnet() -> \nSQLException ->  " + ex);
       }catch (Exception ex){
            System.err.println("commnetDB -> insertCommnet() -> \nGeneral Exception ->  " + ex);
       }finally{
           DBUtil.closePreparedStatement(ps);
           pool.freeConnection(connection);
       } 
       return commentAdded;
   }
   
    public static boolean deleteCommentByID(int commentID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean commentDeleted = false; 
        
        String query = """
                       DELETE FROM comment
                       WHERE comment_id = ?;
                       """;
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, commentID);

            result = ps.executeUpdate();
            System.out.println("CommentsDB -> deleteCommentByID() -> Delete executed -> rows effected -> " + result);
            commentDeleted = true;

        }catch(SQLException ex){
            System.err.println("CommentsDB -> deleteAllCommentsForUser() failed-> \nExcetion -> " + ex +"\n") ;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return commentDeleted;
    } 
     
     
     
}
