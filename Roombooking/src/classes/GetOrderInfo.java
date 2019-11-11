package classes;

import java.sql.*;
import java.io.PrintWriter;

public class GetOrderInfo {

    /*
        Denne metoden skal ta inn customer ID som parameter, og bruke denne til å printe ut informasjon
        om order til alle orders som inneholder denne Customer ID.
         "Customer ID: "     + orderInfoRS.getString(3) +
     */
    public String getOrderFromCustomerId(String customerID, PrintWriter out)
    {
        DbTool dbtool = new DbTool();
        Connection conn = dbtool.logIn(out);

        try
        {
            String stmt = "SELECT * FROM RoombookingDB.Orders WHERE cus_id = ?";
            PreparedStatement getOrderInfoStatement = conn.prepareStatement(stmt);
            getOrderInfoStatement.setString(1, customerID);
            ResultSet orderInfoRS = getOrderInfoStatement.executeQuery();

            String orderInfo = "<h1>All Order information: </h1>";
            while(orderInfoRS.next())
            {
                        String orderID = "<p>Order ID:"+ orderInfoRS.getString(1) + "</p>";
                        String roomID = "<p>Room type:"+ orderInfoRS.getString(2) + "</p>";
                        String checkInDate = "<p>Check in date: " + orderInfoRS.getString(4) + "</p>";
                        String checkOutDate = "<p>Check out date: " + orderInfoRS.getString(5) + "</p>";
                        String paymentMethod = "<p>Payment method:" + orderInfoRS.getString(7) + "</p>";
                        String preferences = "<p>Preferences: " + orderInfoRS.getString(6) + "</p><br>";

                        if (roomID.contains("sr")) {
                                roomID = "<p> Room type: Single room</> </p>";
                            }
                        else if (roomID.contains("dr")) {
                                roomID = "<p> Room type: <Double room</> </p>";
                            }
                        else if (roomID.contains("fr")) {
                                roomID = "<p> Room type: <Family room</> </p>";
                            }
                        else if (roomID.contains("zj")) {
                                roomID = "<p> Room type: Suite</> </p>";
                            }
                    orderInfo = orderInfo + "<br>" + orderID + roomID + checkInDate + checkOutDate + paymentMethod + preferences;
                 }
            return orderInfo;
        }
        catch(SQLException ex)
        {
            out.println("Exeption in getOrderFromCustomerID: " + ex);
        }
        return null;
    }

}