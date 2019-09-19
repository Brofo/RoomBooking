package classes;



import java.io.PrintWriter;
import java.sql.*;

public class Customer {



    public static String getInfo() throws Exception {
        try {
            Connection con = new DbTool().logInn();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select cus_id from Customer");

            rs.next();
            String id = rs.getString("cus_id");
            return id;

        }
        catch (Exception ex){
            System.out.println("Exeption: " + ex);
        }
        return null;

    }
}
