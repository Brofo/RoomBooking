package classes;

import javax.xml.transform.Result;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        con = new DbTool().logIn(out);

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

    /**
     * getId will take arguments to decide which table and column to search
     * with the value caller wants to search for.
     * @param tableToSearch the name of the table to search.
     * @param columnToSearch the name of the column to search.
     * @param columnValue the value to to search the column for.
     * @return the primary key (id) of the searched value or null if not found.
     */
    public String getId(String tableToSearch, String columnToSearch, String columnValue) {
        try {
            String stmt = "SELECT cus_id FROM RoombookingDB." + tableToSearch + " WHERE " + columnToSearch + " = ?";
            PreparedStatement pst = con.prepareStatement(stmt);
            pst.setString(1, columnValue);
            ResultSet searchResultSet = pst.executeQuery();

            if(searchResultSet.next()){
                String searchResult = searchResultSet.getString(1);
                return searchResult;
            }
        } catch (SQLException e){
            out.println("Exeption in getCusId: " + e);
        }
        return null;
    }

    /**
     * checkForCustomer checks if there's a customer registered by either the name, email or phone submitted
     * and maps the result by customer id and the data connected with it in a hashmap.
     * @param name
     * @param email
     * @param phone
     * @return a hashmap with the search results.
     */
    public HashMap<String, String> checkForCustomer(String name, String email, String phone) {
        String cus_id;
        String[] columnArray = {"cus_name", "cus_email", "cus_phone"};
        String[] searchArray = {name, email, phone};
        HashMap<String, String> searchResults = new HashMap();
        for(int i = 0; i < columnArray.length; i++){
            cus_id = getId("Customer", columnArray[i], searchArray[i]);
            if(cus_id != null){
                searchResults.put(cus_id, searchArray[i]);
            }
        }
        return searchResults;
    }

    public String getField(String wantedField, String tableToSearch, String columnToSearch, String columnValue) {
        try {
            String stmt =   "SELECT " + wantedField +
                            " FROM RoombookingDB." + tableToSearch +
                            " WHERE " + columnToSearch + " = ?";
            PreparedStatement pst = con.prepareStatement(stmt);
            pst.setString(1, columnValue);
            ResultSet searchResultSet = pst.executeQuery();

            if(searchResultSet.next()){
                String searchResult = searchResultSet.getString(1);
                out.println(searchResult);
                return searchResult;
            }
        } catch (SQLException e){
            out.println("Exeption in getField: " + e);
        }
        return null;

    }

    public void createBooking(String name, String email, String phone, String checkIn, String checkOut) {
        try {
            if(checkForCustomer(name, email, phone).isEmpty()) {

            }


            PreparedStatement pst = con.prepareStatement("INSERT INTO RoombookingDB.Orders VALUES ('?', '?', '?', '?', '?'");
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setString(4, checkIn);
            pst.setString(5, checkOut);



            int orderCount = pst.executeUpdate();
            out.println(orderCount + "order/s created.");

        } catch (SQLException e) {
            out.println("Error" + e);
        }

    }


}
