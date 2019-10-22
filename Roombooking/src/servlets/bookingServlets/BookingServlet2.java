package servlets.bookingServlets;

import classes.CustomerFunctionality;
import classes.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlets.bookingServlets.BookingServlet2",
            urlPatterns = {"/servlets.bookingServlets.BookingServlet2"}
)

/**
 * This servlet is used when either a logged in user, or a customer is at the last step
 * of booking a room. If it is a user, an order will be registered, using the user's customer ID.
 * If it is a customer, the customer will be registered first, and then the order.
 */
public class BookingServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        CustomerFunctionality cusFun = new CustomerFunctionality(out);
        Register reg = new Register();

        // Henter menyen på toppen av websiden.
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("link.html").include(request, response);
        out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'></head>");

        // Verdiene fra parameterne:
        String availableRoomID = request.getParameter("availableRoomID");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String preferences = request.getParameter("preferences");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        String roomType = request.getParameter("roomType");

        // Requesting cookie to check if a user is logged in
        Cookie existingCookies[] = request.getCookies();


        if (existingCookies != null) {
            //If logged in, just register the order, using the users Customer ID.
            String customerID = existingCookies[0].getValue();
            cusFun.inputRecordInOrders(availableRoomID, customerID, checkInDate, checkOutDate, preferences);
            out.println("<p> You have successfully booked a </p4> " +"<h4>" + roomType +"</h4>"+ " <p> from </p><p>" + checkInDate +"</p>"+ " <p> until </p> <p>" + checkOutDate + ".</p>");
        }

        else {
            //If not logged in, register the customer in the database.
            //Then use the customer ID of this customer to register an order.
            String customerID = reg.getCustomerAndUserID(out);
            reg.registerCustomer(out, customerID, firstname, lastname, email, phone);
            cusFun.inputRecordInOrders(availableRoomID, customerID, checkInDate, checkOutDate, preferences);
            out.println("<p> You have successfully booked a </p4> " +"<h4>" + roomType +"</h4>"+ " <p> from </p><p class='date'>" + checkInDate +"</p>"+ " <p> until </p> <p class='date'>" + checkOutDate + ".</p>");
        }
    }
}
