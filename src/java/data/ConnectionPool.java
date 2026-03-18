package data;

/**
 *
 * @author raren
 * controls the connection pool
 */

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool() {
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/bytespaceDB"); //The end of the string of the datasource is bytespaceDB and *MUST* be bytespaceDB, or what ever is defined in the context.xml resource name, we are sticking with scc for now. pg 405
            System.out.println("DataSource lookup successful: "  + dataSource);
        }catch(NamingException ex) {
            System.out.println("\n\nError in Connection pool NamingException:    " + ex + "\n\n");
        }
    }
    

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        
        return pool;
    }
    
    
   public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
