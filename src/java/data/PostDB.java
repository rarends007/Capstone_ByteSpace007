/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Comment;
import business.bytespace.Image;
import business.bytespace.Super.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
    
    public static HashMap<Integer, Image> getPostImages(int postID)
            throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Integer, Image> images = new HashMap<Integer, Image>();
        
        String query = """
                       SELECT *
                       FROM image
                       WHERE post_id = ?;
                       """;
        
        ps = connection.prepareStatement(query);
        ps.setInt(1, postID);
        rs = ps.executeQuery();
        
        while (rs.next()) {
                int imageID = rs.getInt("image_id");
                String imagePath = rs.getString("image_path");

                Image image = new Image(imageID, imagePath);

                images.put(imageID, image);
            }
        
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return images;
    }
    
    public static HashMap<Integer, Comment> getPostComments(int postID)
            throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
        
        String query = """
                       SELECT c.comment_id, c.commenting_user_id, c.post_id, c.comment_text, u.username 
                       FROM comment c JOIN user u 
                       ON c.commenting_user_id = u.user_id 
                       WHERE c.post_id = ?;
                       """;
        
        ps = connection.prepareStatement(query);
        ps.setInt(1, postID);
        rs = ps.executeQuery();
        
        while (rs.next()) {
                int commentID = rs.getInt("comment_id");
                int commentingUserID = rs.getInt("commenting_user_id");
                int commentPostID = rs.getInt("post_id");
                String commentText = rs.getString("comment_text");
                String commentingUsername = rs.getString("username");

                Comment comment = new Comment(commentID, commentingUserID, commentPostID, commentText, commentingUsername);

                comments.put(commentID, comment);
            }
        
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return comments;
    }
    
    public static HashMap<Integer, Post> getUserPosts(int userID)
             throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Integer, Post> posts = new HashMap<Integer, Post>();
        
        String query = """
                       SELECT *
                       FROM post
                       WHERE user_id = ?;
                       """;
        
        ps = connection.prepareStatement(query);
        ps.setInt(1, userID);
        rs = ps.executeQuery();
        
        while (rs.next()) {
                int postID = rs.getInt("post_id");
                String postText = rs.getString("post_text");
                HashMap<Integer, Image> images = getPostImages(postID);
                HashMap<Integer, Comment> comments = getPostComments(postID);

                Post post = new Post(postID, postText, images, comments);

                posts.put(postID, post);

            }
        
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return posts;
    }
    
    public static boolean makePost(int userID, String imageURL, String postText) {        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        boolean success = false;
        
                String query = """
                       INSERT INTO post (user_id, image_file_path, post_text)
                       VALUES(?, ?, ?);
                       """;
      
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setString(2, imageURL);
            ps.setString(3, postText);

            result = ps.executeUpdate();
            System.out.println("PostDB -> makePost() -> post inserted");
            success = true;

        }catch(SQLException ex){
            System.out.println("\nPostDB -> makePost() failed-> \nExcetion -> " + ex +"\n") ;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return success;
    }
}
