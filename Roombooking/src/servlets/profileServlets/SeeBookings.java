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
        GetOrderInfo ordInfo = new GetOrderInfo();

        Cookie userCookie[] = request.getCookies();
        String customerID = userCookie[0].getValue();

        out.println(ordInfo.getOrderFromCustomerId(customerID, out));
    }
}
