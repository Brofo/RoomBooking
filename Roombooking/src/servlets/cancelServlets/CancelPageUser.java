package servlets.cancelServlets;

import classes.AlterOrder;
import classes.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(
        name = "CancelPageUser",
        urlPatterns = {"/servlets.cancelServlets.CancelPageUser"}
        )
/**
 * This Servlet Uses DbTool and AlterOrder
*/
public class CancelPageUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'>" +
                "<title> My Info </title>" +
                "</head>");
        request.getRequestDispatcher("link.html").include(request, response);
        Throwable var4 = null;

        Cookie userCookie[] = request.getCookies();
        DbTool dbTool = new DbTool();
        Connection conn = dbTool.logIn(out);
        AlterOrder order = new AlterOrder();

        //Variables for the Customer,Order and Action
        String cID = userCookie[0].getValue();
        String orderID = request.getParameter("orderid");
        String action = request.getParameter("action");

        //Names that will be changed
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        //Email that will be changed
        String email = request.getParameter("email");

        //Phone that will be changed
        String phone = request.getParameter("phone");
        String rom = request.getParameter("rom");

        //Date that will be changed
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");

        //Password that will be changed
        String oldpass = request.getParameter("oldpassword");
        String newpass = request.getParameter("newpassword");

        /*
        * The function that will decided which action function will be used
        * */
        try {
            //Function that calls for changeName in AlterOrder
            if(action.contains("name")){
                order.changeName(out,conn,firstName,lastName,cID);
            }
            //Function that calls for changeEmail in AlterOrder
            else if (action.contains("email")){
                order.changeEmail(out,conn,email,cID);
            }
            //Function that calls for changePhone in AlterOrder
            else if(action.contains("phone")){
                order.changePhone(out,conn,phone,cID);
            }
            //Function that calls for changePassword in AlterOrder
            else if(action.contains("password")){
                order.changePassword(out,conn,newpass,oldpass,cID);
            }
            //Function that calls for changeRoom in AlterOrder
            else if(action.contains("room")){
                //Changes room to a singelroom
                if (rom.contains("sr")){
                    order.changeRoom(out,conn,orderID,"sr01");
                    out.println("<p>Changed room to Singelroom</p>");

                }
                //Changes room to a doubleroom
                else if(rom.contains("dr")){
                    order.changeRoom(out,conn,orderID,"dr01");
                    out.println("<p> Changed room to Doubleroom</p>");

                }
                //Changes room to a famillyroom
                else if(rom.contains("fr")){
                    order.changeRoom(out,conn,orderID,"fr01");
                    out.println("<p>  Changed room to Familyroom</p>");

                }
                //Changes room to a Suiteroom
                else if(rom.contains("zj")){
                    System.out.println("It works");
                    order.changeRoom(out,conn,orderID,"zj01");
                    out.println("<p> Changed room to Suite</p>");
                }
            }
            //Function that calls for changeDate in AlterOrder, The first for checkin and the second for checkout
            else if(action.contains("date")){

                order.changeDate(out,conn,orderID,"checkin",checkin);
                order.changeDate(out,conn,orderID,"checkout",checkout);

            }

            //Function that calls for cancelOrder in AlterOrder
            else if (action.contains("Cancel booking")){

                order.cancelOrder(out,conn,orderID,cID);

            }

        } catch (Throwable var17) {
            var4 = var17;
        } finally {
            if (out != null) {
                if (var4 != null) {
                    try {
                        out.close();
                    } catch (Throwable var16) {
                        var4.addSuppressed(var16);
                    }
                } else {
                    out.close();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
