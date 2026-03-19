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
public class MessageDB {
     public static boolean deleteMessagesForUser(int userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean userDeleted = false; 
        
        String query = """
                       DELETE FROM message
                       WHERE sender_id = ?;
                       """;
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("MessageDB -> deleteMessagesForUser() -> Delete executed -> rows effected -> " + result);
            userDeleted = true;

        }catch(SQLException ex){
            System.out.println("MessageDB -> deleteMessagesForUser() failed-> \nExcetion -> " + ex +"\n") ;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return userDeleted;
    } 
     
   public static boolean setAllRecieverMessageUserNamesBlankForUser(int userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean receiverMessagesSetToBlankForUser = false; 
        
        String query = """
                       UPDATE message
                       SET reciever_id = null
                       WHERE reciever_id = ?;
                       """;
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("MessageDB -> setAllRecieverMessageUserNamesBlankForUser() -> Delete executed -> rows effected -> " + result);
            receiverMessagesSetToBlankForUser = true;

        }catch(SQLException ex){
            System.out.println("MessageDB -> setAllRecieverMessageUserNamesBlankForUser() failed-> \nExcetion -> " + ex +"\n") ;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return receiverMessagesSetToBlankForUser;
    }   
}
