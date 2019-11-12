package classes;

import javax.persistence.SqlResultSetMapping;
import javax.xml.transform.Result;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * DbLib provides the functionality related to the Customer entity.
 * @author Karl Martin Lund
 * @version 1.0
 * @since
 */

public class DbLib {
    private PrintWriter out;
    private Connection con;




    public DbLib(PrintWriter out) {
        this.out = out;
    }
    /**
     * checkForCustomer checks if there's a customer registered by either the name, email or phone submitted
     * and maps the result by customer id and the data connected with it in a hashmap.
     * @param name
     * @param email
     * @param phone
     * @return a hashmap with the search results.
     */
    public HashMap<String, String> checkForCustomer(String name, String email, String phone) throws SQLException {
        String cus_id;
        String[] columnArray = {"cus_name", "cus_email", "cus_phone"};
        String[] searchValueArray = {name, email, phone};
        HashMap<String, String> searchResults = new HashMap();
        for(int i = 0; i < columnArray.length; i++){
            cus_id = getField("cus_id", "Customer", columnArray[i], searchValueArray[i]);
            if(cus_id != null){
                searchResults.put(searchValueArray[i], cus_id);
            }
        }
        return searchResults;
    }
    /**
     * getField will take parameters to create a SQL-statement that retrieves any single record of the
     * RoombookingDB-schema.
     * @param whatToSelect is the name of the record (column name) wanted.
     * @param tableToSearch is the name of the table the attribute is in.
     * @param whereCondition is the condition that the record should be matched against.
     * @param whereParameter the exact value of the condition used for the matching.
     * @return a String that contains the value of the record found.
     */
    public String getField(String whatToSelect, String tableToSearch, String whereCondition, String whereParameter) throws SQLException {
        con = new DbTool().logIn(out);
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
        }finally {
            if (out != null) {
            }
            if (con != null){
                con.close();
            }


        }
        return null;
    }

    /**
     * inputRecordInCustomer will create a new record of a customer in table Customer.
     * PS: Sindre har laget en form for denne i Register.java, men jeg trengte cus_id i kalleren (for å kunne
     * registrere kunde før ordre), så enten så må generateId skje i kalleren (createBooking) eller så må
     * registerCustomer returnere cus_id'en. Ville ikke endre Sindre sin klasse så jeg lagde min egen her. Dvs. at
     * jeg per nå kaller generateId i createBooking.
     * @param cus_id
     * @param name
     * @param phone
     * @param email
     * @param password
     * @param bonuspoints
     */
    public void inputRecordInCustomer(String cus_id, String name, String phone, String email, String password, String bonuspoints) throws SQLException {
        con = new DbTool().logIn(out);
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO RoombookingDB.Customer VALUES (?, ?, ?, ?, ?, ?)");
            pst.setString(1, cus_id);
            pst.setString(2, name);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, password);
            pst.setString(6, bonuspoints);

            int customerCount = pst.executeUpdate();
            if(customerCount == 1) {
                out.println(customerCount + " customer registered.");
            }
            else {
                out.println(customerCount + " customer registered.");
            }
        }
        catch (SQLException e) {
            out.println("Exeption in inputRecordInCustomer: " + e);
        } finally {
            if (out != null) {
               out.close();
            }
            if (con != null){
                con.close();
            }

        }
    }
    /**
     * inputRecordInOrders will put in a full record in table Orders. This is essentially the last part of creating
     * an order this will stay private as it needs only be accessed from createOrder().
     * @param room_id the id of the room that is ordered
     * @param cus_id the id of the customer that is ordering.
     * @param checkInDate the check in-date of the order.
     * @param checkOutDate the check out-date of the order.
     * @param customerPreferences any special preferences the customer has submitted.
     * @return the order_id of the order recorded.
     */
    public String inputRecordInOrders(String room_id, String cus_id, String checkInDate, String checkOutDate, String customerPreferences, String paymentType) throws SQLException {
        con = new DbTool().logIn(out);
        try {
            java.util.Date checkInDatePR = new SimpleDateFormat("yyyy-MM-dd").parse(checkInDate);
            java.util.Date checkOutDatePR = new SimpleDateFormat("yyyy-MM-dd").parse(checkOutDate);
            GenerateID idGenerator = new GenerateID();
            String orderId = idGenerator.getID(out, "SELECT count(*) FROM RoombookingDB.Orders");

            PreparedStatement pst = con.prepareStatement("INSERT INTO RoombookingDB.Orders VALUES (?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, orderId);
            pst.setString(2, room_id);
            pst.setString(3, cus_id);
            pst.setDate(4, new java.sql.Date(checkInDatePR.getTime()));
            pst.setDate(5, new java.sql.Date(checkOutDatePR.getTime()));
            pst.setString(6, customerPreferences);
            pst.setString(7, paymentType);

            int orderCount = pst.executeUpdate();
            if(orderCount == 1) {
                //out.println(orderCount + " order created.");
            }
            else {
               // out.println(orderCount + " orders created.");
            }
            return orderId;
        }
        catch (SQLException | ParseException e) {
            out.println("Exeption in createOrder: " + e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (con != null){
                con.close();
            }

        }
        return null;
    }

    /**
     * getAvailableRoomBetween finds the id of rooms of wanted type that is free in the selected period.
     * @param roomTypeId is the prefix of the room_id. E.g. if doubleroom is wanted pass the string "dr%"
     *                   to get all double rooms. If any type pass the string "%".
     * @param checkInDate is the wanted date for checking in.
     * @param checkOutDate is the wanted date for checking out.
     * @return a string with one of the available rooms.
     */
    public String getAvailableRoomBetween(String roomTypeId, String checkInDate, String checkOutDate) throws ParseException, SQLException {
        con = new DbTool().logIn(out);
        java.util.Date checkIndatePR = new SimpleDateFormat("yyyy-MM-dd").parse(checkInDate);
        java.util.Date checkOutdatePR = new SimpleDateFormat("yyyy-MM-dd").parse(checkOutDate);

        try{
            String stmt = "SELECT room_id FROM RoombookingDB.Room WHERE room_id LIKE ? AND room_id NOT IN(" +
                    "SELECT room_id FROM RoombookingDB.Orders WHERE room_id LIKE ? " +
                    "AND (order_checkindate BETWEEN ? AND (DATE_ADD(?, INTERVAL -1 DAY))" +
                    "OR order_checkoutdate BETWEEN (DATE_ADD(?, INTERVAL 1 DAY)) AND ?))";

            PreparedStatement preparedStatement = con.prepareStatement(stmt);
            preparedStatement.setString(1, roomTypeId);
            preparedStatement.setString(2, roomTypeId);
            preparedStatement.setDate(3, new java.sql.Date(checkIndatePR.getTime()));
            preparedStatement.setDate(4, new java.sql.Date(checkOutdatePR.getTime()));
            preparedStatement.setDate(5, new java.sql.Date(checkIndatePR.getTime()));
            preparedStatement.setDate(6, new java.sql.Date(checkOutdatePR.getTime()));

            ResultSet availableRooms = preparedStatement.executeQuery();

            if(availableRooms.next()){
                return availableRooms.getString(1);
            }
            else{
                return null;
            }
        }
        catch(SQLException e){
            out.println("Exeption thrown from getAvailableRoomBetween: " + e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (con != null){
                con.close();
            }
        }
        return null;
    }


    /**
     * getOrderInfo will return a string the info of the order related to the orderId passed to this method.
     * @param orderId
     * @return a string with the values of each column related to the orderId
     */
    public String getOrderInfo(String orderId) throws SQLException {
        try{
            String stmt = "SELECT * FROM RoombookingDB.Orders WHERE order_id = ?";
            PreparedStatement getOrderInfoStatement = con.prepareStatement(stmt);
            getOrderInfoStatement.setString(1, orderId);
            ResultSet orderInfoRS = getOrderInfoStatement.executeQuery();
            orderInfoRS.next();
            String orderInfoString =    "Order ID: "        + orderId +
                                        "Room ID: "         + orderInfoRS.getString(2) +
                                        "Customer ID: "     + orderInfoRS.getString(3) +
                                        "Check In Date: "   + orderInfoRS.getString(4) +
                                        "Check Out Date: "  + orderInfoRS.getString(5) +
                                        "Preferences: "     + orderInfoRS.getString(6) +
                                        "Payment type: "    + orderInfoRS.getString(7);
            return orderInfoString;
        }
        catch(SQLException e){
            out.println("Exeption in getOrderInfo: " + e);
        }finally {
            if (out != null) {
                out.close();
            }
            if (con != null){
                con.close();
            }

        }
        return null;
    }

    /**
     * This method is used to alter the bonus points of a user.
     * @param customerID The ID of the customer that will have the points altered.
     * @param bonuspoints The amount of bonus points that will be altered.
     */
    public void alterBonusPoints(String customerID, int bonuspoints) throws SQLException {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE RoombookingDB.customer " +
                    "SET cus_bonuspoints = (cus_bonuspoints + (?)) WHERE cus_id = (?)");
            pst.setInt(1, bonuspoints);
            pst.setString(2, customerID);
            pst.executeUpdate();
        }
        catch (SQLException ex) {
            out.println("Could not alter bonuspoints + " + ex);
        }finally {
            if (out != null) {
                out.close();
            }
            if (con != null){
                con.close();
            }

        }
    }


}
