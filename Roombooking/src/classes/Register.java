/**
 * Denne klassen skal registrere customer og order i databasen.
 */

package classes;

import java.io.PrintWriter;
import java.sql.*;

public class Register {
    private GenerateID id;
    private final String sqlCustomerTable;  // Dette er en SQL statement.
    private final String sqlOrderTable;     // Dette er en SQL statement.


    public Register() {
        sqlCustomerTable = "SELECT count(*) FROM RoombookingDB.Customer";
        sqlOrderTable = "SELECT count(*) FROM RoombookingDB.Orders";
        id = new GenerateID();
    }


    /**
     * Denne metoden skal gjøre det mulig for kunden å registrere navn, epost og telefonnummer.
     * Den tar i bruk klassen getID for å registrere ID automatisk.
     */
    public void registerCustomer(PrintWriter out, Connection conn, String navn, String email, String telefon) {

        String customerID = id.getID(out, conn, sqlCustomerTable);

            try {

             PreparedStatement insert = conn.prepareStatement
                    ("INSERT INTO RoombookingDB.Customer(cus_id, cus_name, cus_email, cus_phone) VALUES(?,?,?,?)");
                insert.setString(1, customerID);
                insert.setString(2, navn);
                insert.setString(3, email);
                insert.setString(4, telefon);
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