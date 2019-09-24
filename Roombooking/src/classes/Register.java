/**
 * Denne klassen skal registrere customer og order i databasen.
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
    public String getCustomerAndUserID(PrintWriter out, Connection conn) {
        customerAndUserID = id.getID(out, conn, sqlCustomerTable);
        return customerAndUserID;
    }

    /**
     * This method makes it possible for a customer to register name, email, and phone.
     * It uses Class getID to register an ID automatically.
     */
    public void registerCustomer(PrintWriter out, Connection conn, String name, String email, String phone) {

        String customerID = getCustomerAndUserID(out, conn);

            try {

             PreparedStatement insert = conn.prepareStatement
                    ("INSERT INTO RoombookingDB.Customer(cus_id, cus_name, cus_email, cus_phone) VALUES(?,?,?,?)");
                insert.setString(1, customerID);
                insert.setString(2, name);
                insert.setString(3, email);
                insert.setString(4, phone);
                insert.executeUpdate();
            }
            catch (SQLException ex) {
                out.println("Kunne ikke registrere kunde " + ex);
            }

        }

    /**
     * This method is similar to registerCustomer, except it is used for creating
     * an account on the website. Password will be included when creating an account.
     */
    public void registerUser(PrintWriter out, Connection conn, String name, String email, String phone, String password) {

        String customerID = id.getID(out, conn, sqlCustomerTable);

        try {

            PreparedStatement insert = conn.prepareStatement
                    ("INSERT INTO RoombookingDB.Customer(cus_id, cus_name, cus_email, cus_phone, password) VALUES(?,?,?,?,?)");
            insert.setString(1, customerID);
            insert.setString(2, name);
            insert.setString(3, email);
            insert.setString(4, phone);
            insert.setString(5, password);
            insert.executeUpdate();
        }
        catch (SQLException ex) {
            out.println("Kunne ikke registrere kunde " + ex);
        }

    }

    /**
     * Denne metoden skal gjøre det mulig for å registrere data i en order.
     * Den tar i bruk klassen getID for å registrere ID automatisk.
     */
    public void registerOrder(PrintWriter out, Connection conn,
                              String roomID, String customerID,  String checkInDate, String checkOutDate) {

        String orderID = id.getID(out, conn, sqlOrderTable);

        try {
            PreparedStatement insert = conn.prepareStatement
                    ("INSERT INTO RoombookingDb.Orders(order_id, room_id, cus_id, " +
                            "order_checkindate, order_checkoutdate) VALUES (?,?,?,?,?)");
            insert.setString(1, orderID);
            insert.setString(2, roomID);
            insert.setString(3, customerID);
            insert.setString(4, checkInDate);
            insert.setString(5, checkOutDate);
            insert.executeUpdate();
        }
        catch (SQLException ex) {
                out.println(("Kunne ikke registrere order " + ex));
            }
        }
    }