package classes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Room {
    Statement stmt;

    public void getRoomID(PrintWriter out, Connection conn) {
        PreparedStatement getRoomID;

        try {
            getRoomID = conn.prepareStatement("select room_id , room_type from roombookingdb.room order by ?");
            getRoomID.setString(1, "roomID");

            ResultSet rset = getRoomID.executeQuery();

            out.println("<h3 style=color:red> The Room IDS and Room type are the following </h3> " + "<br>");
            int rowCount = 0;

            while (rset.next()) {
                String roomID = rset.getString("room_id");
                String roomType = rset.getString("room_type");

                out.println("<div style= color:blue> RoomID: " + roomID + " Roomtype:  " + roomType + "</div><br>");
                out.println("<br>");

                ++rowCount;

            }

            out.println("Total number of roomID and Room are: " + rowCount);
        } catch (SQLException ex) {

            out.println("Ikke hentet fra databasen ERROR: " + ex);

        }


    }
// Printer ut romtypene.
    public void getRoomType(PrintWriter out, Connection conn) {
        PreparedStatement getRoomType;

        try {
            getRoomType = conn.prepareStatement("select room_type from roombookingdb.room order by ?");

            getRoomType.setString(1, "room_type");

            ResultSet rset = getRoomType.executeQuery();

            out.println("<h3 style=color:green> DETTE ER ROMTYPENE SOM DEKKER HVER EN SMAK ! </h3> " + "<br>");
            int rowCount = 0;

            while (rset.next()) {

                String roomType = rset.getString("room_type");

                out.println("<div style= color:black> Roomtype:  " + roomType + "</div><br>");
                out.println("<br>");

                ++rowCount;

            }
            out.println("Total number of Room are: " + rowCount);
        } catch (SQLException ex) {

            out.println("Ikke hentet fra databasen ERROR: " + ex);
        }
    }
// Printer ut hva romtypene koster
        public void getRoomPrice(PrintWriter out, Connection conn) {
            PreparedStatement getRoomPrice;

            try {
                getRoomPrice = conn.prepareStatement("select room_type , room_price from roombookingdb.room order by ?");
                getRoomPrice.setString(1, "room_price");

                ResultSet rset = getRoomPrice.executeQuery();

                out.println("<h3 style=color:red> Dette er romtypene og prisene </h3> " + "<br>");
                int rowCount = 0;

                while (rset.next()) {
                    String roomType = rset.getString("room_type");
                    String roomPrice = rset.getString("room_price");

                    out.println("<div style= color:blue> RoomType: " + roomType + " RoomPrice:  " + roomPrice + "</div><br>");
                    out.println("<br>");

                    ++rowCount;

                }

            } catch (SQLException ex) {

                out.println("Ikke hentet fra databasen ERROR: " + ex);

            }
        } //Kode fortsatt under konstruksjon, BE AWARE
/**public void getRoomAvailability(PrintWriter out, Connection conn) {
 PreparedStatement getRoomAvailability;

 try {
 getRoomAvailability = conn.prepareStatement("select room_available from roombookingdb.room order by ?");


 ResultSet rset = getRoomPrice.executeQuery();

 out.println("<h3 style=color:red> Dette er romtypene og prisene </h3> " + "<br>");
 int rowCount = 0;

 while (rset.next()) {
 String roomType = rset.getString("room_type");
 String roomPrice = rset.getString("room_price");

 out.println("<div style= color:blue> RoomType: " + roomType + " RoomPrice:  " + roomPrice + "</div><br>");
 out.println("<br>");

 ++rowCount;

 }

 } catch (SQLException ex) {

 out.println("Ikke hentet fra databasen ERROR: " + ex);

 }
 }**/
}


