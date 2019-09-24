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
    //Tar inn det gamle navnet og det nye navnet samt kundeid som senere blir videresendt til SQL Stringen
    public void changeName(PrintWriter out, Connection conn, String oldname , String newname, String customerID){

        //Under ser du sql stringen som oppdaterer det gamle navnet med det nye navnet, skriver så ut navnet tar også inn kunde id slik at man endrer på riktig navn, ettersom at den vil endre på alle som har registrert seg under samme navn.
        final String sql_name = "UPDATE RoombookingDB.Customer set cus_name = ? where cus_name = ? AND cus_id = ?;";

        try{
            PreparedStatement Statement = conn.prepareStatement(sql_name);
            Statement.setString(1,newname);
            Statement.setString(2,oldname);
            Statement.setString(3, customerID);
            Statement.executeUpdate();

            out.println("Endret navn fra " + oldname + " til " + newname);

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne navn error: " + ex);
        }
    }
    public void changeEmail(PrintWriter out, Connection conn, String oldMail, String newMail){
        //Under ser du sql stringen som oppdaterer den gamle e-mailen med den nye e-mailen, skriver så ut endringene som er blitt gjort

        final String sql_name = "UPDATE RoombookingDB.Customer set cus_email = ? where cus_email= ?;";

        try{
            PreparedStatement Statement = conn.prepareStatement(sql_name);
            Statement.setString(1,newMail);
            Statement.setString(2,oldMail);
            Statement.executeUpdate();

            out.println("Endret E-mail fra " + oldMail + " til " + newMail);

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne E-mail error: " + ex);
        }


    }

    public void changePhone(PrintWriter out, Connection conn, int oldPhone, int newPhone){
        //Under ser du sql stringen som oppdaterer det gamle nummeret med det nye nummeret, skriver så ut endring som ble gjort på navnet
        final String sql_name = "UPDATE RoombookingDB.Customer set cus_phone = ? where cus_phone= ?;";

        try{
            PreparedStatement Statement = conn.prepareStatement(sql_name);

            Statement.setInt(1,newPhone);
            Statement.setInt(2,oldPhone);

            Statement.executeUpdate();

            out.println("Endret E-mail fra " + oldPhone + " til " + newPhone);

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne telefon error: " + ex);
        }


    }
}
