package servlets.profileServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlets.profileServlets.seeBookings",
            urlPatterns = {"/servlets.profileServlets.seeBookings"}
)

/**
 * This servlet is used to print out a list of all the users bookings.
 */
public class SeeBookings extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();


    }
}
