package classes;


import javax.persistence.criteria.Order;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristian
 * @version 0.8
 */
public class AlterOrder {
    private PrintWriter out;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;


    public AlterOrder(){

    }
    //Tar inn det gamle navnet og det nye navnet samt kundeid som senere blir videresendt til SQL Stringen
    public void changeName(PrintWriter out, Connection conn, String firstname , String lastname, String customerID){

        //Under ser du sql stringen som oppdaterer det gamle navnet med det nye navnet, skriver så ut navnet tar også inn kunde id slik at man endrer på riktig navn, ettersom at den vil endre på alle som har registrert seg under samme navn.
        try{
            String sql_name;
                out.println(firstname+lastname);
            if (firstname != "" && lastname == ""){

                sql_name = "UPDATE RoombookingDB.customer set cus_firstname = ? where cus_id = ?;";
                PreparedStatement Statement = conn.prepareStatement(sql_name);
                Statement.setString(1,firstname);
                Statement.setString(2, customerID);
                Statement.executeUpdate();
                out.println("<p> Endret navn  til " + firstname +"</p>");
                out.println(firstname+lastname);
            } else if(firstname == "" && lastname != ""){

                sql_name = "UPDATE RoombookingDB.customer set cus_lastname = ? where cus_id = ?;";
                PreparedStatement Statement = conn.prepareStatement(sql_name);
                Statement.setString(1,lastname);
                Statement.setString(2, customerID);
                Statement.executeUpdate();
                out.println("<p> Endret navn  til " + lastname +"</p>");

            } else if(firstname != "" && lastname !=""){

                sql_name = "UPDATE RoombookingDB.customer set cus_firstname = ? , cus_lastname = ? where cus_id = ?;";
                PreparedStatement Statement = conn.prepareStatement(sql_name);
                Statement.setString(1,firstname);
                Statement.setString(2,lastname);
                Statement.setString(3, customerID);
                Statement.executeUpdate();
                out.println("<p> Endret navn  til " + firstname + " " + lastname +"</p>");
                out.println(firstname+lastname);
            } else{
                out.println("<p>Du må skrive inn navnet du vil endre i det tilhørende feltet</p>");
                out.println(firstname+lastname);
            }



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
    public void changeRoom(PrintWriter out, Connection conn,String orderID, String RomID){
        final String sql_name = "UPDATE RoombookingDB.orders set room_id = ? where order_id = ?;";

        try{
            out.println("<p> endret rom </p>");
            PreparedStatement Statement = conn.prepareStatement(sql_name);

            Statement.setString(1,RomID);
            Statement.setString(2,orderID);

            Statement.executeUpdate();

            out.println("<p> På bestilling " + orderID+"</p>");

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne telefon error: " + ex);
        }




    }
    public void changeDate(PrintWriter out, Connection conn, String orderID, String type, String data) throws ParseException {
        out.println("<title> Congrats you cancelled culture on the "+ data + "</title>");
        java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        String sql_name = null;

        if (type == "checkin"){
            sql_name = "UPDATE RoombookingDB.orders set order_checkindate = ? where order_id = ?";

        } else if(type =="checkout"){
            sql_name = "UPDATE RoombookingDB.orders set order_checkoutdate = ? where order_id = ?";
        }

        try{

            PreparedStatement Statement = conn.prepareStatement(sql_name);
            Statement.setDate(1,new java.sql.Date(date1.getTime()));
            Statement.setString(2,orderID);
            Statement.executeUpdate();

            out.println("<p> Endret dato til " + date1  + " i ordre: " + orderID + "</p>");

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne navn error: " + ex);
        }
    }

    public void cancelOrder(PrintWriter out, Connection conn, String orderID, String mail, String phone){
        final String sql = "DELETE roombookingdb.orders FROM roombookingdb.orders inner join roombookingdb.customer on orders.cus_id = customer.cus_id where order_id = ? and (customer.cus_email = ?  or customer.cus_phone = ?);";

        if (mail == "" && phone == ""){
            out.println("<p>Vi må nesten ha en email eller et telefonnummer for avbestilling, gå tilbake og prøv igjen :)"+"</p>");
        }
        else{
        try{
            PreparedStatement Statement = conn.prepareStatement(sql);
            Statement.setString(1, orderID);
            Statement.setString(2,mail);
            Statement.setString(3,phone);
            Statement.executeUpdate();

            out.println("<p> Kansellert " + orderID  + " <br> Ha en fin dag! </p>");

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne navn error: " + ex);
        }
      }
    }

    }


