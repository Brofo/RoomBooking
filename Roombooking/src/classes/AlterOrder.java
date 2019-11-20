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
 * @version 0.9
 * This class is for making changes to all the users information.
 * Its used in the CancelpageUser Servlet, and provides the logic to the servlet.
 *
 **/
public class AlterOrder {
    private PrintWriter out;
    private Connection conn;
    private ResultSet rs;


    public AlterOrder(){

    }

    /**
     * This class takes in a printwriter,connection ,firstname, secondname and a customerID. Then it will execute an
     * sql string which will update the name of the user with the customerID provided.
     * @param out Printwriterout
     * @param conn Connection
     * @param firstname Firstname for user
     * @param lastname Lastname for user
     * @param customerID CustomerID for user
     */

    public void changeName(PrintWriter out, Connection conn, String firstname , String lastname, String customerID) throws SQLException {

        //Under ser du sql stringen som oppdaterer det gamle navnet med det nye navnet, skriver så ut navnet tar også inn kunde id slik at man endrer på riktig navn, ettersom at den vil endre på alle som har registrert seg under samme navn.
        PreparedStatement Stat = null;
        try {
            String sql_name;

            /*
            * If the users firstname is not empty but last name is it will only change firstname
            */
            if (firstname != "" && lastname == "") {

                sql_name = "UPDATE RoombookingDB.customer set cus_firstname = ? where cus_id = ?;";
                Stat = conn.prepareStatement(sql_name);
                Stat.setString(1, firstname);
                Stat.setString(2, customerID);
                Stat.executeUpdate();
                out.println("<p> Changed firstname to " + firstname + "</p>");
                out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.cancelServlets.CancelPage\">");




            }
            /*
            * If the users firstname is empty and lastname is not it will only change the lastname
            */
            else if (firstname == "" && lastname != "") {

                sql_name = "UPDATE RoombookingDB.customer set cus_lastname = ? where cus_id = ?;";
                Stat = conn.prepareStatement(sql_name);
                Stat.setString(1, lastname);
                Stat.setString(2, customerID);
                Stat.executeUpdate();
                out.println("<p> Changed lastname to " + lastname + "</p>");
                out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.cancelServlets.CancelPage\">");

            }
            /*
            * If both names are filled in it will change both
            */
            else if (firstname != "" && lastname != "") {

                sql_name = "UPDATE RoombookingDB.customer set cus_firstname = ? , cus_lastname = ? where cus_id = ?;";
                Stat = conn.prepareStatement(sql_name);
                Stat.setString(1, firstname);
                Stat.setString(2, lastname);
                Stat.setString(3, customerID);
                Stat.executeUpdate();
                out.println("<p> Changed fullname to " + firstname + " " + lastname + "</p>");
                out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.cancelServlets.CancelPage\">");

            }
            /*
             Else it will give out an error message and redirect back to manage acount page
            */
            else {
                out.println("<p>You have to write atleast one name into the field</p>");
                out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.cancelServlets.CancelPage\">");
            }


        } catch (SQLException ex) {
            out.println("Couldnt find name error: " + ex);
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

    /**
     * This class takes in a printwriter,connection ,New password, Old password and a customerID. Then it will execute an
     * sql string which will update the password of the user with the customerID provided and then it will force the user to logout.
     * @param out Printwriterout
     * @param conn Connection
     * @param newpass New password for the user
     * @param oldpass Old password for the user
     * @param cID CustomerID for user
     */
    public void changePassword(PrintWriter out, Connection conn, String newpass,String oldpass, String cID) throws SQLException {
        PreparedStatement Stat = null;
        try {
            final String sql_name = "UPDATE RoombookingDB.Customer set cus_password = ? where cus_id= ? and cus_password =?;";
            Stat = conn.prepareStatement(sql_name);
            Stat.setString(1,newpass);
            Stat.setString(2,cID);
            Stat.setString(3,oldpass);
            Stat.executeUpdate();

            out.println("<p>You will be redirected to the login page, where you will have to login with the new password</p>");
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url=servlets.userServlets.LogOutServlet\">");
        }  catch (SQLException e) {
            e.printStackTrace();
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

    /**
     * This class takes in a printwriter,connection ,mail,  a customerID. Then it will execute an
     * sql string which will update the email of the user with the customerID provided and then it will force the user to logout
     * @param out Printwriterout
     * @param conn Connection
     * @param mail mail for user
     * @param cID CustomerID for user
     */
    public void changeEmail(PrintWriter out, Connection conn, String mail, String cID) throws SQLException {
        final String sql_name = "UPDATE RoombookingDB.Customer set cus_email = ? where cus_id= ?;";
        PreparedStatement Stat = null;
        try{
            Stat = conn.prepareStatement(sql_name);
            Stat.setString(1,mail);
            Stat.setString(2,cID);
            Stat.executeUpdate();

            out.println("<p> Changed email to " + mail + "</p>");
            out.print("<p> You will now be logged out </p>");
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url=servlets.userServlets.LogOutServlet\">");

        }
        catch (SQLException ex) {
            out.println("Couldnt find email error: " + ex);
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

    /**
     * This class takes in a printwriter,connection ,phone , and a customerID. Then it will execute an
     * sql string which will update the phone of the user with the customerID provided.
     * @param out Printwriterout
     * @param conn Connection
     * @param phone phone for user
     * @param cID CustomerID for user
     */
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

            out.println("<p>Changed phone to " + phone);
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.cancelServlets.CancelPage\">");

        }
        catch (SQLException ex) {
            out.println("Couldnt find phone error: " + ex);
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

    /**
     * This class takes in a printwriter,connection , OrderId  and a Roomid it will execute an
     * sql string which will update the Roomtype of the user with the OrderId provided.
     * @param out Printwriterout
     * @param conn Connection
     * @param orderID orderId for user
     * @param RomID RoomID for user
     */
    public void changeRoom(PrintWriter out, Connection conn,String orderID, String RomID) throws SQLException {
        final String sql_name = "UPDATE RoombookingDB.orders set room_id = ? where order_id = ?;";
        PreparedStatement Stat = null;
        try{
            out.println("<p> Changed room </p>");
            Stat = conn.prepareStatement(sql_name);
            Stat.setString(1,RomID);
            Stat.setString(2,orderID);
            Stat.executeUpdate();

            out.println("<p> On order " + orderID+"</p>");
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.cancelServlets.CancelPage\">");

        }
        catch (SQLException ex) {
            out.println("Couldnt find room error: " + ex);
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

    /**
     * This class takes in a printwriter,connection , OrderId ,a type(checkin or checkout) and the date it will execute an
     * sql string which will update the the date on the set type(chekcin or checkout) of the user with the OrderId provided.
     * @param out PrintWriterOut
     * @param conn Connection
     * @param orderID orderId for user
     * @param type which of type(Checkin Or Checkout) the function will use
     * @param data date for user
     */
    public void changeDate(PrintWriter out, Connection conn, String orderID, String type, String data) throws ParseException, SQLException {
        out.println("<title> Congrats you cancelled culture on the "+ data + "</title>");
        java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        String sql_name = null;
        /*
        * If the type is set to be checkin it will set sqlstring to update the date on checkin
        */
        if (type == "checkin"){
            sql_name = "UPDATE RoombookingDB.orders set order_checkindate = ? where order_id = ?";

        }
        /*
         * If the type is set to be checkout it will set sqlstring to update on checkout
         */
        else if(type =="checkout"){
            sql_name = "UPDATE RoombookingDB.orders set order_checkoutdate = ? where order_id = ?";
        } else{
            out.println("<p>Changing of the date failed, sending you back to manage account. Try again later</p>");
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.profileServlets.ProfileServlet\">");
        }

        PreparedStatement Stat = null;
        try{

            Stat = conn.prepareStatement(sql_name);
            Stat.setDate(1,new java.sql.Date(date1.getTime()));
            Stat.setString(2,orderID);
            Stat.executeUpdate();

            out.println("<p> Changed date to " + date1  + " in order: " + orderID + "</p>");
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.profileServlets.ProfileServlet\">");

        }
        catch (SQLException ex) {
            out.println("Couldnt change date error: " + ex);
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

    /**
     * This class takes in a printwriter,connection , OrderId  and a CustomerID it will execute an
     * sql string which will delete the OrderID of the user with the CustomerID provided.
     * @param out PrintWriterOut
     * @param conn Connection
     * @param orderID orderId for user
     * @param cID The users customerID
     */
    public void cancelOrder(PrintWriter out, Connection conn, String orderID, String cID) throws SQLException {
        final String sql = "DELETE FROM roombookingdb.orders where order_id = ? and cus_id = ?;";
        PreparedStatement Stat = null;
        try{

            Stat = conn.prepareStatement(sql);
            Stat.setString(1, orderID);
            Stat.setString(2,cID);

            Stat.executeUpdate();

            out.println("<p> Cancelled  " + orderID  + " <br> Have a nice day! </p>");
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url= servlets.profileServlets.ProfileServlet\">");

        }
        catch (SQLException ex) {
            out.println("Couldnt Cancel error: " + ex);
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




