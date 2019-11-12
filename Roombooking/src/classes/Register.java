/**
 * This class will register customers, users and orders in the database.
 */

package classes;

import java.io.PrintWriter;
import java.sql.*;

public class Register {
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
     * This class returns a Customer ID, that will be used by both the Customers
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
                if (conn != null){
                    conn.close();
                }
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
            if (conn != null){
                conn.close();
            }
        }

    }

    /**
     * This method will register data into an order.
     * It uses Class getID to register an ID automatically.
     */
    public void registerOrder(PrintWriter out, String roomID, String customerID,
                              String checkInDate, String checkOutDate, String preferences) throws SQLException {

        String orderID = id.getID(out, sqlOrderTable);
        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try {
            PreparedStatement insert = conn.prepareStatement
                    ("INSERT INTO RoombookingDb.Orders(order_id, room_id, cus_id, " +
                            "order_checkindate, order_checkoutdate, order_preferences) VALUES (?,?,?,?,?,?)");
            insert.setString(1, orderID);
            insert.setString(2, roomID);
            insert.setString(3, customerID);
            insert.setString(4, checkInDate);
            insert.setString(5, checkOutDate);
            insert.setString(6, preferences);
            insert.executeUpdate();
        }
        catch (SQLException ex) {
                out.println(("Could not register the order " + ex));
            } finally {
            if (conn != null){
                conn.close();
            }
        }
        }
    }