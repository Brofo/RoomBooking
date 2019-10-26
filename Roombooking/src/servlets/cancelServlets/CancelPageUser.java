package servlets.cancelServlets;

import classes.AlterOrder;
import classes.CustomerFunctionality;
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
import java.text.ParseException;

@WebServlet(
        name = "CancelPageUser",
        urlPatterns = {"/servlets.cancelServlets.CancelPageUser"}
        )
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
        CustomerFunctionality cusFun = new CustomerFunctionality(out);
        DbTool dbTool = new DbTool();
        Connection conn = dbTool.logIn(out);

        AlterOrder order = new AlterOrder();

        //Henter ut kundeID til brukeren
        String cID = userCookie[0].getValue();
        String orderID = request.getParameter("orderid");
        String action = request.getParameter("action");
        //Navn som skal bli endret
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        //Email som skal bli endret
        String email = request.getParameter("email");
        //Telefon som skal bli endret
        String phone = request.getParameter("phone");
        String rom = request.getParameter("rom");
        //Dato som skal bli brukt
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        try {
            if(action.contains("navn")){
                order.changeName(out,conn,firstName,lastName,cID);
            }
            else if (action.contains("email")){
                order.changeEmail(out,conn,email,cID);

            }
            else if(action.contains("telefon")){
                order.changePhone(out,conn,phone,cID);

            }
            else if(action.contains("rom")){
                if (rom.contains("sr")){
                    System.out.println("It works");
                    order.changeRoom(out,conn,orderID,"sr01");
                    out.println("<p> endret rom til Singelrom</p>");

                }
                else if(rom.contains("dr")){
                    System.out.println("It works");
                    order.changeRoom(out,conn,orderID,"dr01");
                    out.println("<p> endret rom til Dobbeltrom</p>");

                }
                else if(rom.contains("fr")){
                    System.out.println("It works");
                    order.changeRoom(out,conn,orderID,"fr01");
                    out.println("<p>  endret rom til Familierom</p>");

                }
                else if(rom.contains("zj")){
                    System.out.println("It works");
                    order.changeRoom(out,conn,orderID,"zj01");
                    out.println("<p> endret rom til Suite</p>");
                }
            }
            else if(action.contains("dato")){
                order.changeDate(out,conn,orderID,"checkin",checkin);
                order.changeDate(out,conn,orderID,"checkout",checkout);

            }
            else if (action.contains("Kansellere")){
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
