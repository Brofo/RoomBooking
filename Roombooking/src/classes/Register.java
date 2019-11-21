/**
 * This class will register customers, users and orders in the database.
 */

package classes;

import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Register {
    private PrintWriter out;
    private GenerateID id;
    private String customerAndUserID;
    private final String sqlCustomerTable;  // SQL statement.
    private final String sqlOrderTable;     // SQL statement.

    public Register() {
        sqlCustomerTable =  "SELECT count(*) FROM RoombookingDB.Customer";
        sqlOrderTable =     "SELECT count(*) FROM RoombookingDB.Orders";
        id = new GenerateID();
    }

    /**
     * This method returns a Customer ID, that will be used by both the Customers
     * and the Users.
     * @return a generated Customer ID.
     */
    public String getCustomerAndUserID(PrintWriter out) throws SQLException {
        customerAndUserID = id.getID(out, sqlCustomerTable);
        return customerAndUserID;
    }

    /**
     * This method makes it possible for a customer to register name, email, and phone.
     * It uses Class getID to register an ID automatically.
     */
    public void registerCustomer(PrintWriter out, String customerID, String firstname,
                                 String lastname, String email, String phone) throws SQLException {
          DbTool dbtool = new DbTool();
          Connection conn = dbtool.logIn(out);

            try {


             PreparedStatement insert = conn.prepareStatement
                    ("INSERT INTO RoombookingDB.Customer(cus_id, cus_firstname, cus_lastname, cus_email, cus_phone) VALUES(?,?,?,?,?)");
                insert.setString(1, customerID);
                insert.setString(2, firstname);
                insert.setString(3, lastname);
                insert.setString(4, email);
                insert.setString(5, phone);
                insert.executeUpdate();
            }
            catch (SQLException ex) {
                out.println("Could not register the customer. " + ex);
            } finally {

                    conn.close();

            }

        }

    /**
     * This method is similar to registerCustomer, except it is used for creating
     * an account on the website. Password will be included when creating an account.
     */
    public void registerUser(PrintWriter out, String firstname, String lastname,
                             String email, String phone, String password) throws SQLException {

        String customerID = id.getID(out, sqlCustomerTable);
        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);
        try {


            PreparedStatement insert = conn.prepareStatement
                    ("INSERT INTO RoombookingDB.Customer(cus_id, cus_firstname, cus_lastname, " +
                            "cus_email, cus_phone, cus_password) VALUES(?,?,?,?,?,?)");
            insert.setString(1, customerID);
            insert.setString(2, firstname);
            insert.setString(3, lastname);
            insert.setString(4, email);
            insert.setString(5, phone);
            insert.setString(6, password);
            insert.executeUpdate();
        }
        catch (SQLException ex) {
            out.println("Could not register the user. " + ex);
        } finally {
            conn.close();

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
        Connection con = new DbTool().logIn(out);
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
            con.close();
        }
        return null;
    }
}