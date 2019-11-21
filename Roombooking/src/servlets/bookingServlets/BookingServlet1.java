package servlets.bookingServlets;

import classes.DbLib;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "servlets.bookingServlets.BookingServlet1",
            urlPatterns = {"/servlets.bookingServlets.BookingServlet1"}
)

/**
 * This servlet will process the data from the first booking page (index.jsp), and return
 * a result based on this data.
 *
 * This servlet is used to check if:
 *  - The desired room is available.
 *  - The person booking the room is a Customer or a User.
 *
 * If the room is not available, the customer will get an error message.
 * If the person is a customer, they will be sent to customer booking.
 * If the person is a user, they will be sent to user booking.
 */
public class BookingServlet1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        DbLib fun = new DbLib(out);

        // Get values from the first booking page.
        String checkInDate = request.getParameter("checkin");
        String checkOutDate = request.getParameter("checkout");
        String roomType = request.getParameter("roomType");

            // Check what room type has been chosen, in order to assign
            // a room ID to the booking order.
            String roomTypeID;
            if (roomType.equals("Single room")) {
                roomTypeID = "sr%";
            } else if (roomType.equals("Double room")) {
                roomTypeID = "dr%";
            } else if (roomType.equals("Family room")) {
                roomTypeID = "fr%";
            } else if (roomType.equals("Suite")) {
                roomTypeID = "zj%";
            } else {
                roomTypeID = null;
                request.setAttribute("errorMessage","Please select a room type.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        String availableRoomID = null;
        try {
            availableRoomID = fun.getAvailableRoomBetween(roomTypeID, checkInDate, checkOutDate);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }

        if (availableRoomID != null && !availableRoomID.equals("")) {
                // The room is available. Next step is to check whether the person
                // booking is a customer or a user.

                // Save the values as attributes, so that they can be received
                // In the next booking page.
                request.setAttribute("checkInDate", checkInDate);
                request.setAttribute("checkOutDate", checkOutDate);
                request.setAttribute("roomType", roomType);
                request.setAttribute("availableRoomID", availableRoomID);

                // Get Cookie details.
                Cookie existingCookies[] = request.getCookies();
                // Check if the user is logged in.
                if (existingCookies != null) {
                    // If the user is logged in, they will be sent to the page for
                    // booking as user.
                    String username = existingCookies[0].getName();
                    request.setAttribute("username", username);
                    request.getRequestDispatcher("BookingAsUser.jsp").forward(request, response);
                } else {
                    // If the user is not logged in, they will be sent to the page
                    // for booking as customer.
                    request.getRequestDispatcher("BookingAsCustomer.jsp").forward(request, response);
                }
            } else {
                if (roomTypeID == null) {
                    // Do nothing. Did not choose room type. This error is handled at the beginning of the servlet.
                }
                else {
                    // The room is not available, and the customer will receive an error.
                    request.setAttribute("errorMessage","There are no rooms of this room type available for this period.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
    }
}