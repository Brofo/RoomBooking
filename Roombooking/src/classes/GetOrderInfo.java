package classes;

import java.sql.*;
import java.io.PrintWriter;

public class GetOrderInfo {

    public void retrive(Connection con) {
        try {
            Statement st = con.createStatement();
            var resultSetOne = st.executeQuery("select cus_name, cus_email, cus_phone from RoombookingDB.customer");
            var resultSetTwo = st.executeQuery("select order_checkInDate, order_checkOutDate, preferences from RoombookingDB.orders");

            while (resultSetOne.next()) {
                var cusName = resultSetOne.getString("cus_name");
                var cusEmail = resultSetOne.getString("cus_email");
                var cusPhone = resultSetOne.getString("cus_phone");
                System.out.println(cusName + " - " + cusEmail + " - " + cusPhone);
            }

            while (resultSetTwo.next()) {
                var checkInDate = resultSetTwo.getString("order_checkInDate");
                var checkOutDate = resultSetTwo.getInt("order_checkOutDate");
                var preferences = resultSetTwo.getString("preferences");
                System.out.println(checkInDate + " - " + checkOutDate + " - " + preferences);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


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

            String orderInfo = "All Order information: <br>";
            while(orderInfoRS.next())
            {
                        String roomID = orderInfoRS.getString(2) + "<br>";
                        String checkInDate = "Check in date: " + orderInfoRS.getString(4) + "<br>";
                        String checkOutDate = "Check out date: " + orderInfoRS.getString(5) + "<br>";
                        String preferences = "Preferences: " + orderInfoRS.getString(6) + "<br><br>";

                        if (roomID.contains("sr")) {
                                roomID = "Room type: Single room <br>";
                            }
                        else if (roomID.contains("dr")) {
                                roomID = "Room type: Double room <br>";
                            }
                        else if (roomID.contains("fr")) {
                                roomID = "Room type: Family room <br>";
                            }
                        else if (roomID.contains("zj")) {
                                roomID = "Room type: Suite <br>";
                            }
                    orderInfo = orderInfo + "<br>" + roomID + checkInDate + checkOutDate + preferences;
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