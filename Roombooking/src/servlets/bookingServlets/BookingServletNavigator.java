package servlets.bookingServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlets.bookingServlets.BookingServletNavigator",
        urlPatterns = {"/servlets.bookingServlets.BookingServletNavigator"}
)

/**
 * This servlet checks what payment type is used. The user will be sent to a
 * new site depending on the payment method. It also makes sure a customer has
 * filled inn all fields for personal data.
 */
public class BookingServletNavigator extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paymentType = request.getParameter("paymentType");
        String availableRoomID = request.getParameter("availableRoomID");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String preferences = request.getParameter("preferences");
        String roomType = request.getParameter("roomType");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        request.setAttribute("availableRoomID", availableRoomID);
        request.setAttribute("checkInDate", checkInDate);
        request.setAttribute("checkOutDate", checkOutDate);
        request.setAttribute("preferences", preferences);
        request.setAttribute("roomType", roomType);
        request.setAttribute("paymentType", paymentType);
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);

        //Create a cookie object for potential cookies that will be handled.
        Cookie existingCookies[] = request.getCookies();

        if (paymentType.contains("Card")) {
            // The user/customer wishes to pay with card.

            if (existingCookies != null) {
                // The user is logged in, and all personal data is already in the database.
                request.getRequestDispatcher("CardPayment.jsp").forward(request, response);
            } else {
                // Make sure the user has inserted values into all fields.
                if (firstname.equals("") || lastname.equals("") || email.equals("") || phone.equals("")) {
                    request.setAttribute("errorMessage", "Please do not leave any empty fields.");
                    request.getRequestDispatcher("BookingAsCustomer.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("CardPayment.jsp").forward(request, response);
                }
            }

        } else if (paymentType.contains("Select...")) {
            // The user/customer did not select a payment option, and will get an error message.
            if (existingCookies != null) {
                request.setAttribute("username", existingCookies[0].getName());
                request.setAttribute("errorMessage", "You have to select a payment method.");
                request.getRequestDispatcher("BookingAsUser.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "You have to select a payment method.");
                request.getRequestDispatcher("BookingAsCustomer.jsp").forward(request, response);
            }
        } else {
            if (existingCookies != null) {
                request.getRequestDispatcher("servlets.bookingServlets.BookingServlet2").forward(request, response);
            } else {
                // Make sure the user has inserted values into all fields.
                if (firstname.equals("") || lastname.equals("") || email.equals("") || phone.equals("")) {
                    request.setAttribute("errorMessage", "Please do not leave any empty fields.");
                    request.getRequestDispatcher("BookingAsCustomer.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("servlets.bookingServlets.BookingServlet2").forward(request, response);
                }
            }
        }
    }
}