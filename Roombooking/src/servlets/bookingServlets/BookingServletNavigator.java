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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paymentType = request.getParameter("paymentType");

        if (paymentType.contains("Card")) {
            request.getRequestDispatcher("CardPayment.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("servlets.bookingServlets.BookingServlet2").forward(request, response);
        }
    }
}
