/*
 * Hensikten med klassen er etablere kontakt med databasen
 */
package classes;


import java.io.PrintWriter;
import java.sql.*;
import java.util.PrimitiveIterator;
import javax.ejb.Init;
import javax.naming.*;
import javax.sql.DataSource;


/**
 *
 * @author hallgeir
 */
public class DbTool {
    Connection con;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;


    /*
        Vil etablere kontakt med databasen og returner et Connection objekt.
    */
    public Connection loggInn(PrintWriter out) {
        try {
            // Step 1: Allocate a database 'Connection' object
            Context cont = new InitialContext();
            DataSource ds = (DataSource) cont.lookup("java:comp/env/jdbc/localhost");

            con = ds.getConnection();
            return con;

        } catch (SQLException ex) {
            out.println("Not connected to database " + ex);
        } catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
        return null;
    }  // end loggInn

    public Connection logInn() {
        try {
            Context cont = new InitialContext();
            DataSource ds = (DataSource) cont.lookup("java:comp/env/jdbc/localhost");

            con = ds.getConnection();
            return con;
        } catch (SQLException ex) {
            System.out.println("Not connected to database " + ex);
        } catch (NamingException nex) {
            System.out.println("Not correct naming " + nex);
        }
        return null;

    }
}