package classes;

import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

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
        String[] searchValueArray = {name, email, phone};
        HashMap<String, String> searchResults = new HashMap();
        for(int i = 0; i < columnArray.length; i++){
            cus_id = getSingleRecord("cus_id", "Customer", columnArray[i], searchValueArray[i]);
            if(cus_id != null){
                searchResults.put(searchValueArray[i], cus_id);
            }
        }
        out.println(searchResults.toString());
        return searchResults;
    }
    /**
     * getSingleRecord will take parameters to create a SQL-statement that retrieves any single record of the
     * RoombookingDB-schema.
     * @param whatToSelect is the name of the record (column name) wanted.
     * @param tableToSearch is the name of the table the attribute is in.
     * @param whereCondition is the condition that the record should be matched against.
     * @param whereParameter the exact value of the condition used for the matching.
     * @return a String that contains the value of the record found.
     */
    public String getSingleRecord(String whatToSelect, String tableToSearch, String whereCondition, String whereParameter) {
        try {
            String stmt =   "SELECT " + whatToSelect +
                            " FROM RoombookingDB." + tableToSearch +
                            " WHERE " + whereCondition + " = ?";

            PreparedStatement pst = con.prepareStatement(stmt);
            pst.setString(1, whereParameter);
            ResultSet searchResultSet = pst.executeQuery();

            if(searchResultSet.next()){
                String searchResult = searchResultSet.getString(1);
                return searchResult;
            }
        }
        catch (SQLException e){
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
