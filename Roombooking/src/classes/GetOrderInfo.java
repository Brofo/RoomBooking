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
            orderInfoRS.next();


            while(orderInfoRS.next())
            {
                String orderInfo =    "All Order information "        + customerID +
                        "Room ID: "         + orderInfoRS.getString(2) +
                        "Check In Date: "   + orderInfoRS.getString(4) +
                        "Check Out Date: "  + orderInfoRS.getString(5) +
                        "Preferences: "     + orderInfoRS.getString(6);

                return orderInfo;
            }

        }
        catch(SQLException ex)
        {
            out.println("Exeption in getOrderFromCustomerID: " + ex);
        }
        return null;
    }

}