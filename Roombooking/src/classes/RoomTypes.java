package classes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomTypes {

    public void getRoomID(PrintWriter out, Connection conn){
        PreparedStatement getRoomID;

        try {
            getRoomID = conn.prepareStatement("select room_id , room_type from roombookingdb.room order by ?");
            getRoomID.setString(1, "roomID");

            ResultSet rset = getRoomID.executeQuery();

            out.println("<h3 style=color:red> The Room IDS and Room type are the following </h3> " + "<br>");
            int rowCount = 0;

            while (rset.next()){
                String roomID = rset.getString("room_id");
                String roomType = rset.getString("room_type");

                out.println("<div style= color:blue> RoomID: "+ roomID + " Roomtype:  " + roomType + "</div><br>");
                out.println("<br>");

              ++rowCount;

            }

            out.println("Total number of roomID and RoomTypes are: " + rowCount);
        }
        catch (SQLException ex){

            out.println("Ikke hentet fra databasen ERROR: " + ex);

        }


    }
}
