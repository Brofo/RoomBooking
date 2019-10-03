/**
 * Hensikten med denne klassen er å generere en unik og tifleldig ID til enten
 * kunden eller order.
 */

package classes;

import java.io.PrintWriter;
import java.sql.*;
import java.util.Random;


public class GenerateID {
    private Random randomGenerator;

    public GenerateID() {
        randomGenerator = new Random();
    }


    /**
     * Denne metoden skal kombinere bokstaven C (for customer) eller O (for order)
     * og et tall opptil 99999. Denne kombinasjonen av karakterer blir customer ID
     * eller order ID, avhengig av parameteret.
     *
     * @param sqlTable - Hva slags tabell det skal generere ID for.
     *                 Denne variabelen er en SQL statement.
     * @return Ferdig generert ID.
     */
    private String generateID(String sqlTable, PrintWriter out) {
        String numberID = Integer.toString(randomGenerator.nextInt(99999));
        String customerLetterID = "C";
        String orderLetterID = "O";

        if (sqlTable.equals("SELECT count(*) FROM RoombookingDB.Customer")) {
            String customerID = customerLetterID + numberID;
            return customerID;
        }
        if (sqlTable.equals("SELECT count(*) FROM RoombookingDB.Orders")) {
            String orderID = orderLetterID + numberID;
            return orderID;
        }
        else {
            out.println("Kunne ikke generere ID. Parameter må enten være " +
                    "'RoombookingDB.Customer' eller 'RoombookingDB.Orders'");
            return null;
        }
    }


    /**
     * Metoden skal sjekke om verdien som opprettes for ID allerede finnes i databasen.
     * Hvis den ikke finnes, blir verdien returnert og brukt i registrering av customer eller order.
     */
    public String getID(PrintWriter out, String sqlTable) {

        String ID = generateID(sqlTable, out);
        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try {
            PreparedStatement select = conn.prepareStatement(sqlTable);
            ResultSet rs = select.executeQuery();

            // Sjekk om ID finnes fra før i databasen.
            // Hvis den gjør det, genereres en ny ID, og loopen starter på nytt.
            while (rs.next()) {
                if (ID.equals(rs.getString(rs.getRow()))) {
                    ID = generateID(sqlTable, out); // Generer ny ID, ettersom den allerede eksisterer.
                    rs.first(); // Moves the cursor to the first row in this ResultSet object.
                }
            }
            return ID;  // Returnerer ID dersom den ikke eksisterer fra før.
        } catch (SQLException ex) {
            out.println("Kunne ikke opprette ID " + ID + ex);
            return null;
        }
    }
}