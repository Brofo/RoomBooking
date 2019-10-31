package servlets.bookingServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servlets.bookingServlets.BookingServletNavigator",
        urlPatterns = {"/servlets.bookingServlets.BookingServletNavigator"}
)

public class BookingServletNavigator extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String availableRoomID = request.getParameter("availableRoomID");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String preferences = request.getParameter("preferences");
        String roomType = request.getParameter("roomType");
        String paymentType = request.getParameter("paymentType");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        request.setAttribute(availableRoomID, "availableRoomID");
        request.setAttribute(checkInDate, "checkInDate");
        request.setAttribute(checkOutDate, "checkOutDate");
        request.setAttribute(preferences, "preferences");
        request.setAttribute(roomType, "roomType");
        request.setAttribute(paymentType, "paymentType");
        request.setAttribute(firstname, "firstname");
        request.setAttribute(lastname, "lastname");
        request.setAttribute(email, "email");
        request.setAttribute(phone, "phone");

        if (paymentType.equals("card")) {
            request.getRequestDispatcher("CardPayment.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("BookingServlet2").forward(request, response);
        }
    }
}
