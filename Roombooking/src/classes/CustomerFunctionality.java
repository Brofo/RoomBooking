package classes;

import javax.persistence.SqlResultSetMapping;
import javax.xml.transform.Result;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
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

        //Dette under her kommer jeg antageligvis til å fjerne. Brukte det når dette var en CustomerFunctionality,
        //men nå er det vel mest en start på DBFunctionality, i mine øyne i alle fall.
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
    public String getField(String whatToSelect, String tableToSearch, String whereCondition, String whereParameter) {
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
    public void inputRecordInCustomer(String cus_id, String name, String phone, String email, String password, String bonuspoints) {
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
    public String inputRecordInOrders(String room_id, String cus_id, String checkInDate, String checkOutDate, String customerPreferences) {
        try {
            GenerateID idGenerator = new GenerateID();
            String orderId = idGenerator.getID(out, "SELECT count(*) FROM RoombookingDB.Orders");

            PreparedStatement pst = con.prepareStatement("INSERT INTO RoombookingDB.Orders VALUES (?, ?, ?, ?, ?, ?)");
            pst.setString(1, orderId);
            pst.setString(2, room_id);
            pst.setString(3, cus_id);
            pst.setString(4, checkInDate);
            pst.setString(5, checkOutDate);
            pst.setString(6, customerPreferences);

            int orderCount = pst.executeUpdate();
            if(orderCount == 1) {
                out.println(orderCount + " order created.");
            }
            else {
                out.println(orderCount + " orders created.");
            }
            return orderId;
        }
        catch (SQLException e) {
            out.println("Exeption in createOrder: " + e);
        }
        return null;
    }

    /**
     * checkIfAvailableRoom will check if the chosen room type is available for the time period and call createBooking
     * with the order information if it is.
     * @param name
     * @param email
     * @param phone
     * @param wantedRoomType
     * @param checkInDate
     * @param checkOutDate
     * @param preferences
     */
    public void checkIfRoomAvailable(String name, String email, String phone, String wantedRoomType, String checkInDate, String checkOutDate, String preferences) {
        String roomId = getAvailableRoomBetween(wantedRoomType, checkInDate, checkOutDate);
        if(roomId == null){
            out.println("No available rooms of chosen type available for this period.");
            out.println("Try different type or different period.");
        }
        else{
            createBooking(name, email, phone, roomId, checkInDate, checkOutDate, preferences);
        }

    }

    /**
     * createBooking will use checkForCustomer if some of the information is already used. If it is not it will call
     *  idGenerator to get a new cus_id and call inputRecordInCustomer with the new cus_id and the rest of the info.
     *  If the info is used it will use the cus_id related to the email used. (Preferably it would check if there is
     *  different cus_ids related to the different data but it doesn't do that yet.) At last it will call
     *  inputRecordInOrders with the info and either the used cus_id or the newly generated one.
     *  At last it prints out the string returned from getOrderInfo.
     * @param name
     * @param email
     * @param phone
     * @param checkInDate
     * @param checkOutDate
     */
    public void createBooking(String name, String email, String phone, String roomId, String checkInDate, String checkOutDate, String preferences) {    GenerateID idGenerator = new GenerateID();
        HashMap<String, String> usedPersonalInfoMap = checkForCustomer(name, email, phone);
        String customerId;
        if(usedPersonalInfoMap.isEmpty()) {
            customerId = idGenerator.getID(out,  "SELECT count(*) FROM RoombookingDB.Customer");
            inputRecordInCustomer(customerId, name, phone, email, null, null);
        }
        else{
            customerId = usedPersonalInfoMap.get(email);
        }
        String orderId = inputRecordInOrders(roomId, customerId, checkInDate, checkOutDate, preferences);
        out.println(getOrderInfo(orderId));
    }

    /**
     * getAvailableRoomBetween finds the id of rooms of wanted type that is free in the selected period.
     * @param roomTypeId is the prefix of the room_id. E.g. if doubleroom is wanted pass the string "dr%"
     *                   to get all double rooms. If any type pass the string "%".
     * @param checkInDate is the wanted date for checking in.
     * @param checkOutDate is the wanted date for checking out.
     * @return a string with one of the available rooms.
     */
    public String getAvailableRoomBetween(String roomTypeId, String checkInDate, String checkOutDate){
        try{
            String stmt = "SELECT room_id FROM RoombookingDB.Room WHERE room_id LIKE ? AND room_id NOT IN(" +
                    "SELECT room_id FROM RoombookingDB.Orders WHERE room_id LIKE ? " +
                    "AND (order_checkindate BETWEEN ? AND (DATE_ADD(?, INTERVAL -1 DAY))" +
                    "OR order_checkoutdate BETWEEN (DATE_ADD(?, INTERVAL 1 DAY)) AND ?))";

            PreparedStatement preparedStatement = con.prepareStatement(stmt);
            preparedStatement.setString(1, roomTypeId);
            preparedStatement.setString(2, roomTypeId);
            preparedStatement.setString(3, checkInDate);
            preparedStatement.setString(4, checkOutDate);
            preparedStatement.setString(5, checkInDate);
            preparedStatement.setString(6, checkOutDate);

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
        }
        return null;
    }

    /**
     * getOrderInfo will return a string the info of the order related to the orderId passed to this method.
     * @param orderId
     * @return a string with the values of each column related to the orderId
     */
    public String getOrderInfo(String orderId){
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
                                        "Preferences: "     + orderInfoRS.getString(6);
            return orderInfoString;
        }
        catch(SQLException e){
            out.println("Exeption in getOrderInfo: " + e);
        }
        return null;
    }

    /**
     * De to metodene under her klarer jeg ikke se noen bruk for til nå, men jeg lar de stå i tilfelle noe dukker opp.
     * Si gjerne i fra til Karl Martin om noen har bruk for de så jeg ikke plutselig fjerner de.
     */

    /**
     * getBookedRooms will take the date in question and create a ResultSet
     * with the room_id of all booked rooms for this date.
     * Unsure whether we will use this for anything, but here it is.
     * Can be made to return the whole ResultSet or just the room_ids or print them or what ever.
     * @param dateInQuestion the date on which you want to know which rooms are booked.
     */
    public void getBookedRooms(String dateInQuestion){
        try{
            ResultSet bookedRoomsResultSet = st.executeQuery("SELECT room_id " +
                                                                    "FROM roombookingdb.Orders " +
                                                                    "WHERE order_checkindate <= " + dateInQuestion +
                                                                    " AND order_checkoutdate > " + dateInQuestion);
            while(bookedRoomsResultSet.next()){

            }
        }
        catch(SQLException e){
            out.println("Exeption in getBookedRooms" + e);
        }
    }

    /**
     * getFreeRooms will find all free rooms for one date.
     * @param roomType wanted roomtype. In this you need to use room_type from Room and not room_id. E.g. "doubleroom".
     * @param dateInQuestion the date you want to see free rooms at.
     * @return an ArrayList with all free rooms.
     */
    public ArrayList getFreeRooms(String roomType, String dateInQuestion){
        try{
            String stmt = "SELECT room_id FROM RoombookingDB.Room WHERE room_type = ? AND room_id NOT IN(" +
                                "SELECT room_id FROM RoombookingDB.Orders " +
                                "WHERE order_checkindate <= ?" +
                                "AND order_checkoutdate > ?)";

            PreparedStatement preparedStatement = con.prepareStatement(stmt);
            preparedStatement.setString(1, roomType);
            preparedStatement.setString(2, dateInQuestion);
            preparedStatement.setString(3, dateInQuestion);
            ResultSet freeRoomsResultSet = preparedStatement.executeQuery();

            ArrayList<String> freeRoomIds = new ArrayList<>();
            while(freeRoomsResultSet.next()){
                freeRoomIds.add(freeRoomsResultSet.getString(1));
            }
            return freeRoomIds;
        }
        catch(SQLException e){
            out.println("Exeption in getFreeRooms: " + e);
        }
        return null;
    }
}
