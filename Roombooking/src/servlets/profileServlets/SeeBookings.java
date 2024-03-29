package servlets.profileServlets;

import classes.GetOrderInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.profileServlets.SeeBookings",
            urlPatterns = {"/servlets.profileServlets.SeeBookings"}
)

/**
 * This servlet is used to print out a list of all the users bookings.
 */
public class SeeBookings extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'></head>");
        request.getRequestDispatcher("link.html").include(request, response);

        GetOrderInfo ordInfo = new GetOrderInfo();

        // Henter menyen på toppen av websiden.

        Cookie userCookie[] = request.getCookies();
        String customerID = userCookie[0].getValue();

        try {
            out.println(ordInfo.getOrderFromCustomerId(customerID, out));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
