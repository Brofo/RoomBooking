package servlets.cancelServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import classes.AlterOrder;
import classes.CustomerFunctionality;
import classes.DbTool;

@WebServlet(
        name = "CancelPageUser",
        urlPatterns = {"/servlets.cancelServlets.CancelPageUser"})
public class CancelPageUser extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'></head>");
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
        //Rom type for å endre rom
        String rom = request.getParameter("rom");

        try {
            if(action.contains("navn")){
                System.out.println(lastName);
                order.changeName(out,conn,firstName,lastName,cID);
            }


        } catch (Throwable var17) {
            var4 = var17;
            throw var17;
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
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.processRequest(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.processRequest(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
