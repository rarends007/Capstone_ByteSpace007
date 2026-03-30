/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp; //needed to make the timestamp work from LocalDateTime to the mysql DATETIME datatype, and insert it using JDBC
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;

/**
 *
 * @author raren
 */
public class MessageDB {

    /**
     * Deletes all messages for a user form the database. if message deleted,
     * returns true.
     *
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

    /**
     *
     * @param userID
     * @return
     */
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
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return receiverMessagesSetToBlankForUser;
    }

    /**
     * This function inserts one message into the database. Returns true if
     * successful, false if not.
     *
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
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return messageInserted;

    }

    /**
     * Returns all of the received messages for the current logged in user
     * ordered by the time the message was received in descending order, most
     * recent messages first.
     *
     * @param userID int
     * @return Hashmap<Integer, Message>
     */
    public static HashMap<Integer, Message> retrieveAllMessagesForUser(Integer userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<Integer, Message> messagesForUser = new HashMap();

        String query = """ 
                               SELECT *
                               FROM message
                               WHERE reciever_id = ?
                               ORDER BY timestamp DESC;
                       """;        //retrieves all messages for a single user that they recieved

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                int messageID = rs.getInt("message_id");
                String messgeText = rs.getString("message_text");
                int senderID = rs.getInt("sender_id");
                int recieverID = rs.getInt("reciever_id");
                Timestamp time = rs.getTimestamp("timestamp");

                Message message = new Message(messageID, messgeText, senderID, recieverID, time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()); //https://stackoverflow.com/questions/23263490/how-to-convert-java-sql-timestamp-to-localdate-java8-java-time
                messagesForUser.put(messageID, message);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Issue in .retrieveAllMessagesForUser -> \nSQLException -> Error thrown: " + ex);
        } catch (Exception ex) {
            System.err.println("Issue in .retrieveAllMessagesForUser -> \nGeneral Exception -> Error thrown: " + ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return messagesForUser;

    }
    
    /**
     * Gets the message context given the message ID.
     * @param messageID
     * @return Message or Null
     */
    public static Message getMessageByID(int messageID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Message message = null;
        
        String query = """
                        SELECT *
                        FROM message
                        WHERE message_id = ?;
                       """;
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, messageID);
            rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    message = new Message();
                    message.setMessageID(messageID);
                    message.setMessageText(rs.getString("message_text"));
                    message.setSenderID(rs.getInt("sender_id"));
                    message.setRecieverID(rs.getInt("reciever_id"));
                    message.setTimeStamp(rs.getTimestamp("timestamp").toLocalDateTime());
                }
            }
        }catch (SQLException ex){
            System.err.println("getMessageByID() -> \nSQLException: " + ex);
        }catch (Exception ex){
              System.err.println("getMessageByID() -> \nException: " + ex);
        }finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        return message;
    }

}
