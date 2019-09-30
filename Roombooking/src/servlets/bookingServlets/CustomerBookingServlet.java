package servlets.bookingServlets;

import classes.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlets.bookingServlets.CustomerBookingServlet",
            urlPatterns = {"/servlets.bookingServlets.CustomerBookingServlet"}
)
public class CustomerBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Register reg = new Register();

        PrintWriter out = response.getWriter();

        // Henter menyen på toppen av websiden.
        request.getRequestDispatcher("link.html").include(request, response);

        // Verdiene fra parameterne:
        String customerID = reg.getCustomerAndUserID(out);

        String availableRoomID = request.getParameter("availableRoomID");
        String checkInDate = request.getParameter("checkin");
        String checkOutDate = request.getParameter("checkout");
        String preferences = request.getParameter("preferences");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        String roomType = request.getParameter("roomtype");


    }


}
