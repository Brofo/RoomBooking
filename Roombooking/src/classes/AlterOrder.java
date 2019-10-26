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
    private ResultSet rs;


    public AlterOrder(){

    }
    //Tar inn det gamle navnet og det nye navnet samt kundeid som senere blir videresendt til SQL Stringen
    public void changeName(PrintWriter out, Connection conn, String firstname , String lastname, String customerID) throws SQLException {

        //Under ser du sql stringen som oppdaterer det gamle navnet med det nye navnet, skriver så ut navnet tar også inn kunde id slik at man endrer på riktig navn, ettersom at den vil endre på alle som har registrert seg under samme navn.
        PreparedStatement Stat = null;
        try {
            String sql_name;
            if (firstname != "" && lastname == "") {

                sql_name = "UPDATE RoombookingDB.customer set cus_firstname = ? where cus_id = ?;";
                Stat = conn.prepareStatement(sql_name);
                Stat.setString(1, firstname);
                Stat.setString(2, customerID);
                Stat.executeUpdate();
                out.println("<p> Endret navn  til " + firstname + "</p>");


            } else if (firstname == "" && lastname != "") {

                sql_name = "UPDATE RoombookingDB.customer set cus_lastname = ? where cus_id = ?;";
                Stat = conn.prepareStatement(sql_name);
                Stat.setString(1, lastname);
                Stat.setString(2, customerID);
                Stat.executeUpdate();
                out.println("<p> Endret navn  til " + lastname + "</p>");

            } else if (firstname != "" && lastname != "") {

                sql_name = "UPDATE RoombookingDB.customer set cus_firstname = ? , cus_lastname = ? where cus_id = ?;";
                Stat = conn.prepareStatement(sql_name);
                Stat.setString(1, firstname);
                Stat.setString(2, lastname);
                Stat.setString(3, customerID);
                Stat.executeUpdate();
                out.println("<p> Endret navn  til " + firstname + " " + lastname + "</p>");

            } else {
                out.println("<p>Du må skrive inn navnet du vil endre i det tilhørende feltet</p>");
            }


        } catch (SQLException ex) {
            out.println("Kunne ikke finne navn error: " + ex);
        } finally {
            if (Stat != null) {
                Stat.close();
            }
            if(out != null){
                out.close();}
            if (conn != null){
                conn.close();
            }
        }

    }
    public void changeEmail(PrintWriter out, Connection conn, String mail, String cID) throws SQLException {
        //Under ser du sql stringen som oppdaterer den gamle e-mailen med den nye e-mailen, skriver så ut endringene som er blitt gjort

        final String sql_name = "UPDATE RoombookingDB.Customer set cus_email = ? where cus_id= ?;";
        PreparedStatement Stat = null;
        try{
            Stat = conn.prepareStatement(sql_name);
            Stat.setString(1,mail);
            Stat.setString(2,cID);
            Stat.executeUpdate();

            out.println("Endret E-mail  til " + mail);

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne E-mail error: " + ex);
        }
        finally {
            if (Stat != null) {
                Stat.close();
            }
            if(out != null){
                out.close();}
            if (conn != null){
                conn.close();
            }
        }



    }
    public void changePhone(PrintWriter out, Connection conn, String phone, String cID) throws SQLException {
        //Under ser du sql stringen som oppdaterer det gamle nummeret med det nye nummeret, skriver så ut endring som ble gjort på navnet
        final String sql_name = "UPDATE RoombookingDB.Customer set cus_phone = ? where cus_id= ?;";
        PreparedStatement Stat = null;
        try{
            int phoneint = Integer.parseInt(phone);
             Stat = conn.prepareStatement(sql_name);

            Stat.setInt(1,phoneint);
            Stat.setString(2,cID);

            Stat.executeUpdate();

            out.println("Endret E-mail fra  til " + phone);

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne telefon error: " + ex);
        } finally {
            if (Stat != null) {
                Stat.close();
            }
            if(out != null){
                out.close();}
            if (conn != null){
                conn.close();
            }
        }


    }
    public void changeRoom(PrintWriter out, Connection conn,String orderID, String RomID) throws SQLException {
        final String sql_name = "UPDATE RoombookingDB.orders set room_id = ? where order_id = ?;";
        PreparedStatement Stat = null;
        try{
            out.println("<p> endret rom </p>");
            Stat = conn.prepareStatement(sql_name);

            Stat.setString(1,RomID);
            Stat.setString(2,orderID);

            Stat.executeUpdate();

            out.println("<p> På bestilling " + orderID+"</p>");

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne rom error: " + ex);
        } finally {
            if (Stat != null) {
                Stat.close();
            }
            if(out != null){
                out.close();}
            if (conn != null){
                conn.close();
            }
        }




    }
    public void changeDate(PrintWriter out, Connection conn, String orderID, String type, String data) throws ParseException, SQLException {
        out.println("<title> Congrats you cancelled culture on the "+ data + "</title>");
        java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        String sql_name = null;

        if (type == "checkin"){
            sql_name = "UPDATE RoombookingDB.orders set order_checkindate = ? where order_id = ?";

        } else if(type =="checkout"){
            sql_name = "UPDATE RoombookingDB.orders set order_checkoutdate = ? where order_id = ?";
        }
        PreparedStatement Stat = null;
        try{

            Stat = conn.prepareStatement(sql_name);
            Stat.setDate(1,new java.sql.Date(date1.getTime()));
            Stat.setString(2,orderID);
            Stat.executeUpdate();

            out.println("<p> Endret dato til " + date1  + " i ordre: " + orderID + "</p>");

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne navn error: " + ex);
        } finally {
            if (Stat != null) {
                Stat.close();
            }
            if(out != null){
                out.close();}
            if (conn != null){
                conn.close();
            }
        }
    }
    public void cancelOrder(PrintWriter out, Connection conn, String orderID, String cID) throws SQLException {
        final String sql = "DELETE FROM roombookingdb.orders where order_id = ? and cus_id = ?;";
        PreparedStatement Stat = null;
        try{
            Stat = conn.prepareStatement(sql);
            Stat.setString(1, orderID);
            Stat.setString(2,cID);

            Stat.executeUpdate();

            out.println("<p> Kansellert " + orderID  + " <br> Ha en fin dag! </p>");

        }
        catch (SQLException ex) {
            out.println("Kunne ikke finne navn error: " + ex);
        }
        finally {
            if (Stat != null) {
                Stat.close();
            }
            if(out != null){
                out.close();}
            if (conn != null){
                conn.close();
            }
        }
      }
    }




