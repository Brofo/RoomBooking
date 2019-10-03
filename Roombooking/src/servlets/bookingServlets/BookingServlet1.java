package servlets.bookingServlets;

import classes.CustomerFunctionality;
import classes.DbTool;
import classes.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;

@WebServlet(name = "servlets.bookingServlets.BookingServlet1",
            urlPatterns = {"/servlets.bookingServlets.BookingServlet1"}
)

/**
 * For å fullføre denne servleten, trengs en metode (beskrevet lenger nede).
 *
 * Servleten brukes til å sjekke om et rom er ledig, og så sjekke om den som vil
 * booke rommet er en bruker eller en customer.
 *
 */
public class BookingServlet1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        CustomerFunctionality cusFun = new CustomerFunctionality(out);

        // Verdiene fra parameterne.
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
                request.getRequestDispatcher("index.jsp").include(request, response);
                out.println("Please select a room type.");
            }

        String availableRoomID = null;
        try {
            availableRoomID = cusFun.getAvailableRoomBetween(roomTypeID, checkInDate, checkOutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (availableRoomID != null && !availableRoomID.equals("")) {
                // Rommet er ledig. Prøver derfor å finne ut om den som vil booke er en bruker,
                // eller en customer.

                // Gjør slik at verdiene blir lagret i session. Hvis man sender dem til
                // neste side kan man bruke dem der.
                request.setAttribute("checkInDate", checkInDate);
                request.setAttribute("checkOutDate", checkOutDate);
                request.setAttribute("roomType", roomType);
                request.setAttribute("availableRoomID", availableRoomID);

                // Henter cookie
                Cookie existingCookies[] = request.getCookies();
                // Sjekker om brukeren er logget inn.
                if (existingCookies != null) {
                    // Hvis logget inn, sendes den til formen for booking som bruker.
                    String username = existingCookies[0].getName();
                    request.setAttribute("username", username);

                    // Henter jsp. filen og tar med verdiene fra request.setAttribute(...).
                    request.getRequestDispatcher("BookingAsUser.jsp").forward(request, response);
                } else {
                    // Hvis ikke logget inn, sendes den til formen for booking som customer.
                    // Henter jsp. filen og tar med verdiene fra request.setAttribute(...).
                    request.getRequestDispatcher("BookingAsCustomer.jsp").forward(request, response);
                }
            } else {
                if (roomTypeID == null) {
                    // Do nothing. Did not choose room type. This error is handled at the beginning of the servlet.
                } else {
                    // Rommet er ikke ledig.
                    request.getRequestDispatcher("index.jsp").include(request, response); // Henter inn forsiden igjen.
                    out.println("This roomtype is fully booked for this period.");
                }
            }
    }
}