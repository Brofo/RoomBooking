package classes;

import java.sql.*;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class is the logic behind all review functions. It is used to register reviews
 * into the database, get the reviews back from the database, and calculate the average
 * rating score from all the ratings in the database.
 *
 */
public class Reviews {

    /**
     * Method that register a review in the database.
     */
    public void registerReview (PrintWriter out, String review, String rating, String displayname) {
        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try {
            PreparedStatement insert = conn.prepareStatement("INSERT INTO roombookingdb.reviews(review, rating, displayname) VALUES (?,?,?)");
            insert.setString(1, review);
            insert.setString(2, rating);
            insert.setString(3, displayname);
            insert.executeUpdate();

        } catch (SQLException ex) {
            out.println("Something went wrong with your attempt to post your review. " + ex);
        }
    }
    /**
     * This method gets the review data from the database, and puts them into an array.
     * The array is returned with the values from every row in the database.
     * Row 1 will be indexed with 0-2. Row 2 will be indexed 3-5. Row 3 will be indexed 6-8. And so on.
     */
    public ArrayList<String> getReviewArrays (PrintWriter out) {

        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try {
            String query = "SELECT * FROM roombookingdb.reviews";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            ArrayList<String> reviewArray = new ArrayList<>();


            //First check if the table has any values:
            if (rs.next()) {
                rs.afterLast(); // Move the cursor to after the last column.
                while (rs.previous()) {
                    reviewArray.add(rs.getString("review"));
                    reviewArray.add(rs.getString("rating"));
                    reviewArray.add(rs.getString("displayname"));
                }
                st.close();
            }
                return reviewArray;
        }
        catch (SQLException e) {
            System.err.println("Could not find reviews");

        }
        return null;
    }
    /**
      * This metod will calculate the average rating from the database, and return this value.
    **/
    public String getAverageReview(PrintWriter out) {
        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try {
            String query = "SELECT rating FROM roombookingdb.reviews";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            ArrayList<String> ratings = new ArrayList<>();

            // For each rating, add the value to the list of ratings.
            while (rs.next()) {
                ratings.add(rs.getString("rating"));
            }
            st.close();

            // Find average by adding all ratings together, and divide by number of ratings.
            double averageReviewNumber = 0;
            for (String rating : ratings) {
                averageReviewNumber = averageReviewNumber + Double.parseDouble(rating);
            }

            // Check if there are no reviews in the database.
            if (averageReviewNumber > 0) {
                // To avoid getting a bunch of decimal numbers, turn value into int
                // multiplied by 10, and then convert it back to double.
                double averageReview = averageReviewNumber / ratings.size();
                int temp = (int) (averageReview * 10.0);
                double fixedReviewValue = ((double) temp) / 10.0;
                return Double.toString(fixedReviewValue);
            } else {
                // no reviews found, just return a star :)
                return "*";
            }
        }
        catch (SQLException ex) {
            System.err.println("Problem with finding average review " + ex);
        }
        return null;
    }
}

