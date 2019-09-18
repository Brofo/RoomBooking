/**
 * Hensikten med denne klassen er å generere en unik og tifleldig ID til kunden,
 * samt gjøre det mulig for kunden å registrere navn, email og telefonnummer.
 */

package classes;

import java.io.PrintWriter;
import java.sql.*;
import java.util.Random;


public class RegisterCustomer {
    private Random randomGenerator;

    public RegisterCustomer() {
        randomGenerator = new Random();
    }


    /**
     * Denne metoden skal kombinere bokstaven C (for customer) og et tall opptil 99999.
     * Denne kombinasjonen av karakterer blir customer ID.
     * @return Ferdig generert Customer ID.
     */
    private String generateCustomerID() {
        String numberID = Integer.toString(randomGenerator.nextInt(99999));
        String letterID = "C";
        String customerID = letterID + numberID;
        return customerID;
    }


    /**
     * Metoden skal sjekke om verdien som opprettes for Customer ID allerede finnes i
     * databasen. Hvis den ikke finnes, blir verdien returnert og brukt i registrering av kunde.
     */
    private String getCustomerID(PrintWriter out, Connection conn) {
        String customerID = generateCustomerID();

        try {
            PreparedStatement select = conn.prepareStatement("SELECT count(*) FROM RoombookingDB.Customer");
            ResultSet rs = select.executeQuery();

            // Sjekk om Customer ID finnes fra før i databasen.
            // Hvis den gjør det, genereres en ny ID, og loopen starter på nytt.
            while(rs.next()) {
                if (customerID.equals(rs.getString(rs.getRow()))) {
                    customerID = generateCustomerID(); // Generer ny ID, ettersom den allerede eksisterer.
                    rs.first(); // Moves the cursor to the first row in this ResultSet object.
                }
            }
            return customerID;
        }
        catch (SQLException ex) {
            out.println("Kunne ikke opprette CustomerID " + customerID + ex);
            return null;
        }
    }


    /**
     * Denne metoden skal gjøre det mulig for kunden å registrere navn, epost og telefonnummer.
     * Den tar i bruk klassen getCustomerID for å registrere ID automatisk.
     */
    public void registerCustomer(PrintWriter out, Connection conn, String navn, String email, String telefon) {

        String customerID = getCustomerID(out, conn);

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
    }
