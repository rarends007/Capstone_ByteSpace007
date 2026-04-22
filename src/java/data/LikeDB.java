/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Super.User;
import static data.UserDB.getUserID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.naming.NamingException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author se757706
 */
public class LikeDB {

    public static boolean insertLike(int postID, int commentID, int likingUserID) throws SQLException, NamingException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean success = false;

        //todo, make a function to insert Admins later, in the JSP there needs, getting the role from the User user obj
        //to be a hidden field that passes MEMBER or ADMIN and then it is passed to user in the PublicController -> 
        //Register Switch differentiated by an if() in the switch and then calls either insertAdmin, or insertMember
        //only the admin portal will have a hidden input role assigned to "ADMIN" when passed to the PublicController
        String query = """
                       INSERT INTO like
                       (post_ID, comment_ID, liking_user_ID)
                       VALUES
                       (?, ?, ?);
                       """;
        ps = connection.prepareStatement(query);
        ps.setInt(1, postID);
        ps.setInt(2, commentID);
        ps.setInt(3, likingUserID);

        result = ps.executeUpdate();

        if (result != -1) {
            success = true;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return success;
    }
    
    public static boolean deleteLike(int likeID) throws SQLException, NamingException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean success = false;

        //todo, make a function to insert Admins later, in the JSP there needs, getting the role from the User user obj
        //to be a hidden field that passes MEMBER or ADMIN and then it is passed to user in the PublicController -> 
        //Register Switch differentiated by an if() in the switch and then calls either insertAdmin, or insertMember
        //only the admin portal will have a hidden input role assigned to "ADMIN" when passed to the PublicController
        String query = """
                       REMOVE FROM like
                       WHERE like_ID = ?
                       """;
        ps = connection.prepareStatement(query);
        ps.setInt(1, likeID);

        result = ps.executeUpdate();

        if (result != -1) {
            success = true;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return success;
    }
    
    public static LinkedHashMap<Integer, Integer> getPostLikes(int postID) throws SQLException, NamingException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        ResultSet rs = null;
        LinkedHashMap<Integer, Integer> postLikes = new LinkedHashMap<>(); //postID and num of likes
        int likes = 0;

        //todo, make a function to insert Admins later, in the JSP there needs, getting the role from the User user obj
        //to be a hidden field that passes MEMBER or ADMIN and then it is passed to user in the PublicController -> 
        //Register Switch differentiated by an if() in the switch and then calls either insertAdmin, or insertMember
        //only the admin portal will have a hidden input role assigned to "ADMIN" when passed to the PublicController
        String query = """
                       SELECT * 
                       FROM `like` 
                       WHERE post_ID = ?
                       """;
        ps = connection.prepareStatement(query);
        ps.setInt(1, postID);

        rs = ps.executeQuery();

        while (rs.next()) {
            likes++;
        }
        postLikes.put(postID, likes);
        
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return postLikes;
    }
    
    public static LinkedHashMap<Integer, Integer> getCommentLikes(int commentID) throws SQLException, NamingException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        ResultSet rs = null;
        LinkedHashMap<Integer, Integer> commentLikes = new LinkedHashMap<>(); //postID and num of likes
        int likes = 0;

        //todo, make a function to insert Admins later, in the JSP there needs, getting the role from the User user obj
        //to be a hidden field that passes MEMBER or ADMIN and then it is passed to user in the PublicController -> 
        //Register Switch differentiated by an if() in the switch and then calls either insertAdmin, or insertMember
        //only the admin portal will have a hidden input role assigned to "ADMIN" when passed to the PublicController
        String query = """
                       SELECT * 
                       FROM `like` 
                       WHERE comment_ID = ?
                       """;
        ps = connection.prepareStatement(query);
        ps.setInt(1, commentID);

        rs = ps.executeQuery();

        while (rs.next()) {
            likes++;
            commentLikes.put(commentID, likes);
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return commentLikes;
    }
}
