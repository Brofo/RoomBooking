package classes;


import java.io.PrintWriter;
import java.sql.*;

/**
 * @author Kristian
 * @version 0.1
 */
public class AlterOrder {
    private PrintWriter out;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public AlterOrder(){

    }
    //Tar inn det gamle navnet og det nye navnet som senere blir videresendt til SQL Stringen
    public void changeName(PrintWriter out, Connection conn, String oldname , String newname){

        //Under ser du sql stringen som oppdaterer det gamle navnet med det nye navnet, skriver så ut navnet
        //Funksjonen funker ikke med mindre man skrive hele navnet likt som det er
        final String sql_name = "UPDATE RoombookingDB.Customer set cus_name = ? where cus_name = ?;";

        try{
            PreparedStatement Statement = conn.prepareStatement(sql_name);
            Statement.setString(1,newname);
            Statement.setString(2,oldname);
            Statement.executeUpdate();

            out.println("Endret navn fra " + oldname + " til " + newname);

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne navn" + ex);
        }
    }

}
