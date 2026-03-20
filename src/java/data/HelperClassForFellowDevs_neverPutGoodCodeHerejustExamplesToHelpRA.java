/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author raren
 */
public class HelperClassForFellowDevs_neverPutGoodCodeHerejustExamplesToHelpRA {
    
  private void copyPasteMeToEasyAddNewDBFunction()
             throws SQLException{ //remember to catch the exception in the calling code, 
         //OR you can just catch it in this func you are defining
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; //remove if not USING a SELECT statement -> returning a resultset
        
        String query = """
                       MySQL Here ?;
                       """;
        
        ps = connection.prepareStatement(query);
        //do stuff
        //ps.setObject(1, obj));
        
        /*
         ###if you update, insert, delete
                int result = ps.executeUpdate(); returns a int for num rows affected
        
                return result; if null it failed. -> place where it needs to go \/
        
         ###if you SELECT
                rs.executeQuery(); //put here
                if(rs != null){
                    while(rs.next()) { //greater than one RECORD/ROW returned from SELECT
                        Integer userid = rs.getInt("userid");
                    }
               }
               if(rs != null){
                    exactly one RECORD/ROW -> follow this format
                    Integer userid = rs.getInt("userid");
                }
                return user(s) or whatever value you are returning below \/
        */
        
        rs = ps.executeQuery(); //remove if not using SELECT and thus returning a resultset, place in proper spot above /\
        DBUtil.closeResultSet(rs); //remove if not using SELECT and thus returning a resultset
        
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
        
        //put return statement here
    }
}
