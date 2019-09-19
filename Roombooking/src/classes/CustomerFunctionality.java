package classes;

import java.io.PrintWriter;
import java.sql.*;

/**
 * CustomerFunctionality provides the functionality related to the Customer entity.
 * @author Karl Martin Lund
 * @version 1.0
 * @since
 */

public class CustomerFunctionality {
    private PrintWriter out;
    private Connection con;
    private Statement st;
    private ResultSet rs;



    public CustomerFunctionality(PrintWriter out) {
        this.out = out;
        con = new DbTool().loggInn(out);

        try {
            st = con.createStatement();
        }
        catch (SQLException sqlEx){
            out.println("Connection.createStatement failed: " + sqlEx);
        }
        try {
            rs = st.executeQuery("SELECT * FROM RoombookingDB.Customer");
        }
        catch (SQLException sqlEx){
            out.println("Filling resultset failed: " + sqlEx);
        }
    }

    /**
     * printCustomers() prints a list of all the customers in the database.
     */
    public void printCustomers() {
        try {
            while(rs.next()) {
                out.println("ID: " + getId() + "\nName: " + getName() + "\nPhone: " + getPhone() + "\nEmail: " + getEmail() + "\n");
            }
        }
        catch (SQLException sqlEx) {
            out.println("rs.next() failed: " + sqlEx);
        }
    }

    /**
     * @return the customers ID.
     */
    private String getId(){
        try {
            String id = rs.getString("cus_id");
            return id;
        }
        catch (SQLException sqlEx) {
            out.println("getId failed: " + sqlEx);
        }
        return null;
    }
    /**
     * @return the customers name.
     */
    private String getName(){
        try {
            String name = rs.getString("cus_name");
            return name;
        }
        catch (SQLException sqlEx) {
            out.println("getName failed: " + sqlEx);
        }
        return null;
    }

    /**
     * @return the customers phone number.
     */
    private String getPhone() {
        try {
            String phone = rs.getString("cus_phone");
            return phone;
        }
        catch (SQLException sqlEx) {
            out.println("getName failed: " + sqlEx);
        }
        return null;
    }

    /**
     * @return the customers email.
     */
    private String getEmail() {
        try {
            String email = rs.getString("cus_email");
            return email;
        }
        catch (SQLException sqlEx) {
            out.println("getEmail failed: " + sqlEx);
        }
        return null;
    }


}
