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
 * DbLib provides the functionality for the roombooking project.
 * The methods used are primarily intended to update and read the database.
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
            con.close();
        }
        return null;
    }


    /**
     * This method is used to alter the bonus points of a user.
     * @param customerID The ID of the customer that will have the points altered.
     * @param bonuspoints The amount of bonus points that will be altered.
     */
    public void alterBonusPoints(String customerID, int bonuspoints) throws SQLException {
        con = new DbTool().logIn(out);
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE RoombookingDB.customer SET cus_bonuspoints = (cus_bonuspoints + (?)) WHERE cus_id = (?)");
            pst.setInt(1, bonuspoints);
            pst.setString(2, customerID);
            pst.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Could not alter bonuspoints:  " + ex);

        } finally {
            con.close();
        }
    }
}
