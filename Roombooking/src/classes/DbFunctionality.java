/*
 * Hensikten med klassen er å samle all funksjonalitet mot
 * databasen. Hente fra tabell, lagre......
 */
package classes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hallgeir
 */
public class DbFunctionality {

 /*

        Skal inserte et nytt navn i tabellen webadresse i databasen. Navnet som
        settes inn er hentet fra formen.
    */
    public void newName(String newName, PrintWriter out, Connection conn) {
        PreparedStatement insertName;

        try {
            String ins ="insert into  RoombookingDB.Customer(cus_name)  values (?)";

            insertName = conn.prepareStatement(ins);
            insertName.setString(1,newName);           // Gir verdi til sql
            insertName.executeUpdate();
            out.println("Nytt navn lagret i DB: " + newName);
        } // end try
        catch (SQLException ex) {
            out.println("Ikke fått opprettet nytt navn " +ex);
        }
    }

    public void newDato(String newDato, PrintWriter out, Connection conn) {
        PreparedStatement insertDato;

        try {
            String ins ="insert into  RoombookingDB.Orders(order_checkindate)  values (?)";
            insertDato = conn.prepareStatement(ins);
            insertDato.setString(1,newDato);           // Gir verdi til sql
            insertDato.executeUpdate();
            out.println("Ny Dato lagret i DB: " + newDato);
        } // end try
        catch (SQLException ex) {
            out.println("Ikke fått opprettet nytt navn " +ex);
        }
    }
    
    /*
        Will print links as links. To do this we will use 
        out.format. See https://dzone.com/articles/java-string-format-examples 
        New here: use of %s in Strings
    */

    public void printName(PrintWriter out, Connection conn)
    {
        PreparedStatement getName;

        try {
            getName = conn.prepareStatement("select * from RoombookingDB.Customer order by ?");
            getName.setString(1,"navn");

            ResultSet rset = getName.executeQuery();

            // Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            out.println("The records selected are:" +"<br>");
            int rowCount = 0;
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                String navn = rset.getString("cus_name");


                out.println("Navn: " + navn +"<br>");


                out.println("<br>");
                ++rowCount;
            }  // end while
            out.println("Total number of records = " + rowCount +"<br>");

        } // end try
        catch (SQLException ex) {
            out.println("Ikke hentet fra DB " +ex);
        }
    }
}