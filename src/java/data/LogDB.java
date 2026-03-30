/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.bytespace.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author raren
 */
public class LogDB {
     
    public static boolean deleteLogsForUser(int userID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        int result = -1;
        
        boolean logDeleted = false; 
        
        String query = """
                       DELETE FROM log
                       WHERE logged_user_id = ?;
                       """;
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);

            result = ps.executeUpdate();
            System.out.println("LogDB -> deleteLogsForUser() -> Delete executed -> rows effected -> " + result);
            logDeleted = true;

        }catch(SQLException ex){
            System.out.println("LogDB -> deleteLogsForUser() failed-> \nExcetion -> " + ex +"\n") ;
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        return logDeleted;
    } 
     
     
    public static void createLoginLog(int userID, int actionID, String logText) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
         
        int result = -1;
        ResultSet rs = null;
        
        String query =
                """
                INSERT INTO log
                (logged_user_id, logged_action_id, log_text)
                values
                (?, ?, ?)
                """;
              
        try 
        {      
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, actionID);
            ps.setString(3, logText);
                        
            result = ps.executeUpdate();
            System.out.println("LogDB -> createLoginLog() -> Creation Executed -> rows effected -> " + result);
        } 
        catch (SQLException ex) 
        {
            System.out.println("LogDB -> createLoginLog() failed-> \nExcetion -> " + ex +"\n") ;
        }
    }
    
    
    public static void getAllLogs () {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
         
        int result = -1;
        ResultSet rs = null;
        HashMap<Integer,Log> logHashMap = new HashMap<>();
        
        String query =
                """
                SELECT *
                FROM log
                """;        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int logID = rs.getInt("log_id");
                    int logUserID = rs.getInt("logged_user_id");
                    int logActionID = rs.getInt("logged_action_id");                                     
                    String logText = rs.getString("log_text");

                    Log log = new Log(logID, logUserID, logActionID, logText);

                    logHashMap.put(logID, user);
                }
            }} 
            catch (SQLException ex) 
            {
            System.out.println("LogDB -> createLoginLog() failed-> \nExcetion -> " + ex +"\n") ;
            }
    
    }
    
    
    
    
    
    
    
    
    
     
}
