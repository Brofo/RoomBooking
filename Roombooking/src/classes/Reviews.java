package classes;

import java.sql.*;
import java.io.PrintWriter;

/**
 * Hovedformålet med denne klassen er å lagre/hente anmeldelser i/fra databasen.
 */
public class Reviews {

    public void registerReview (PrintWriter out, String reviews) {
        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try {
            PreparedStatement insert = conn.prepareStatement("INSERT INTO RoombookingDB.reviews(reviews) VALUES (?)");

            insert.setString(1, reviews);
            insert.executeUpdate();
        } catch (SQLException ex) {
            out.println("Something went wrong with your attempt to post your review. :(");
        }
    }
    /**
     * Denne metoden skal kunne hente reviews fra databasen og vise de til kunden på nettsiden.
     */
    public String getReviews (PrintWriter out) {

        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try {
            String query = "SELECT * FROM RoombookingDB.reviews";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            String reviews = "";
            while (rs.next())

            {

                reviews = reviews + "<p></p>" + rs.getString("reviews");


            }
            st.close();
            return reviews;


        }
        catch (SQLException e) {
            System.err.println("Could not find reviews");

        }
        return null;
    }

}

