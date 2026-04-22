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
import java.sql.Statement;

import data.CommentDB;
import data.ImageDB;

/**
 *
 * @author raren
 */
public class PostDB {

    public static boolean deleteAllPostsForUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean postsDeleted = false;

        String query = """
                       DELETE FROM post
                       WHERE user_id = ?;
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("PostDB -> deleteAllPostsForUser() -> Delete executed -> rows effected -> " + result);
            postsDeleted = true;

        } catch (SQLException ex) {
            System.out.println("\nPostDB -> deleteAllPostsForUser() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return postsDeleted;
    }

    /**
     * Deletes all comments for a post and then the post made for the logged in
     * user based on postID. Returns true if successful, false if not.
     *
     * @param postID
     * @return (boolean)
     */
    public static boolean deletePostByPostID(int postID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean postDeleted = false;

        String query = """
                       DELETE FROM post
                       WHERE post_id = ?;
                       """;

        try {
            if (CommentDB.deleteAllCommentsByPostID(postID)) {
                ImageDB.deleteAllImagesByPostID(postID);
                ps = connection.prepareStatement(query);
                ps.setInt(1, postID);
                result = ps.executeUpdate();

                System.out.println("PostDB -> .deletePostByPostID() -> num post deleted: " + result);
                postDeleted = true;
            }
        } catch (SQLException ex) {
            System.err.println("PostDB -> .deletePostByPostID() -> \nSQLExceptoin: " + ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return postDeleted;

    }

    public static HashMap<Integer, Image> getPostImages(int postID)
            throws SQLException {
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
            throws SQLException {
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
            throws SQLException {
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

    public static int makePost(int userID, String imageURL, String postText) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int postId = -1;

        String query = """
                       INSERT INTO post (user_id, image_file_path, post_text)
                       VALUES(?, ?, ?);
                       """;

        try {
            // getGeneratedKeys() Throws an error below
            // Excetion -> java.sql.SQLException: Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate() or Connection.prepareStatement().
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userID);
            ps.setString(2, imageURL);
            ps.setString(3, postText);

            int result = ps.executeUpdate();

            if (result > 0) {
                // look up intternet how to get generated by insert id
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    postId = rs.getInt(1);
                }
                System.out.println("PostDB -> makePost() -> post inserted");
            }

        } catch (SQLException ex) {
            System.out.println("\nPostDB -> makePost() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return postId;
    }

    public static boolean createPostImage(String imageURL, int postID, int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;
        boolean success = false;

        String query = """
                       INSERT INTO image (image_path, post_id, user_id)
                       VALUES(?, ?, ?);
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, imageURL);
            ps.setInt(2, postID);
            ps.setInt(3, userID);

            result = ps.executeUpdate();
            System.out.println("PostDB -> createPostImage() -> post image inserted");
            success = true;

        } catch (SQLException ex) {
            System.out.println("\nPostDB -> createPostImage() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return success;
    }

    public static boolean uploadImage(String imageURL, int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;
        boolean success = false;

        String query = """
                       INSERT INTO image (image_path, user_id)
                       VALUES(?, ?);
                       """;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, imageURL);
            ps.setInt(2, userID);

            result = ps.executeUpdate();
            System.out.println("PostDB -> uploadImage() -> post image inserted");
            success = true;

        } catch (SQLException ex) {
            System.out.println("\nPostDB -> uploadImage() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return success;
    }

}
