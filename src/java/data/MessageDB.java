/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Timestamp; //needed to make the timestamp work from LocalDateTime to the mysql DATETIME datatype, and insert it using JDBC

/**
 *
 * @author raren
 */
public class MessageDB {

    /**
     * Deletes all messages for a user form the database. if message deleted, returns true.
     * @param userID int
     * @return boolean
     */
    public static boolean deleteMessagesForUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        int result = -1;

        boolean userDeleted = false;

        String query = """
                       DELETE FROM message
                       WHERE sender_id = ?;
                       """;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("MessageDB -> deleteMessagesForUser() -> Delete executed -> rows effected -> " + result);
            userDeleted = true;

        } catch (SQLException ex) {
            System.out.println("MessageDB -> deleteMessagesForUser() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return userDeleted;
    }

    public static boolean setAllRecieverMessageUserNamesBlankForUser(int userID) {
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
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("MessageDB -> setAllRecieverMessageUserNamesBlankForUser() -> Delete executed -> rows effected -> " + result);
            receiverMessagesSetToBlankForUser = true;

        } catch (SQLException ex) {
            System.out.println("MessageDB -> setAllRecieverMessageUserNamesBlankForUser() failed-> \nExcetion -> " + ex + "\n");
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);

        return receiverMessagesSetToBlankForUser;
    }

    /**
     * This function inserts one message into the database. Returns true if successful, false if not.
     * @param message Message object
     * @return boolean
     */
    public static boolean insertMessage(Message message) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        boolean messageInserted = false;

        String query = """
                       INSERT INTO message
                       (sender_id, reciever_id, message_text, timestamp)
                       VALUES
                       (?, ?, ?, ?);
                       """;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, message.getSenderID());
            ps.setInt(2, message.getRecieverID());
            ps.setString(3, message.getMessageText());
            ps.setTimestamp(4, Timestamp.valueOf(message.getTimeStamp())); //https://docs.oracle.com/en/java/javase/17/docs/api/java.sql/java/sql/Timestamp.html
            
            ps.executeUpdate();
                    
            System.out.println("MessageDB -> .insertMessage() -> message inserted");
            messageInserted = true;
        } catch (SQLException ex) {
            System.err.println("MessageDB -> .insertMessage() ->\n * Failed to insert message into the database\n *Reason -> " + ex);
        }

        return messageInserted;

    }

}
