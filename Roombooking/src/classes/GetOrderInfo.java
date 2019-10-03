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
}

