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

@WebServlet(name = "servlets.bookingServlets.MainBookingServlet",
            urlPatterns = {"/servlets.bookingServlets.MainBookingServlet"}
)

/**
 * For å fullføre denne servleten, trengs en metode (beskrevet lenger nede).
 *
 * Servleten brukes til å sjekke om et rom er ledig, og så sjekke om den som vil
 * booke rommet er en bruker eller en customer.
 *
 */
public class MainBookingServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        // Henter menyen på toppen av websiden.
        request.getRequestDispatcher("link.html").include(request, response);

        // Verdiene fra parameterne.
        String checkInDate = request.getParameter("checkin");
        String checkOutDate = request.getParameter("checkout");
        String roomType = request.getParameter("roomType");


        // OBS! her trengs en metode som sjekker at romtypen er ledig
        // for checkin og checkout datoene som er valgt. Slik forventes
        // denne metoden å se ut:
        // String availableRoomID = klasseNavn.getAvailableRoomID(checkInDate, checkOutDate, roomType);

        if (availableRoomID != null || !availableRoomID.equals("")) {
            // Rommet er ledig. Prøver derfor å finne ut om den som vil booke er en bruker,
            // eller en customer.

            // Gjør slik at verdiene blir lagret i session. Hvis man sender dem til
            // neste side kan man bruke dem der.
            request.setAttribute("checkInDate", checkInDate);
            request.setAttribute("checkOutDate", checkOutDate);
            request.setAttribute("roomType", roomType);
            request.setAttribute("roomID", availableRoomID);

            // Henter cookie
            Cookie existingCookies[] = request.getCookies();
            // Sjekker om brukeren er logget inn.
            if (existingCookies != null) {
                // Hvis logget inn, sendes den til formen for booking som bruker.
                String username = existingCookies[0].getName();
                out.println("You are booking as " + username);

                // Henter jsp. filen og tar med verdiene fra request.setAttribute(...).
                request.getRequestDispatcher("BookingAsUser.jsp").forward(request, response);
            }
            else {
                // Hvis ikke logget inn, sendes den til formen for booking som customer.
                // Henter jsp. filen og tar med verdiene fra request.setAttribute(...).
                request.getRequestDispatcher("BookingAsCustomer.jsp").forward(request, response);
            }
        }
        else {
            // Rommet er ikke ledig.
            out.println("This roomtype is fully booked for this period.");
            request.getRequestDispatcher("index.jsp").include(request, response); // Henter inn forsiden igjen.
        }
    }
}

/**
 String customerID = existingCookies[0].getValue(); // Finner customerID gjennom cookie.

 // Henter ut informasjon om brukeren ut ifra customerID.
 String customerName = cusFun.getSingleRecord("cus_name", "Customer", "cus_id", customerID);
 String customerPhone = cusFun.getSingleRecord("cus_phone", "Customer", "cus_id", customerID);
 String customerEmail = cusFun.getSingleRecord("cus_email", "Customer", "cus_id", customerID);

 reg.registerOrder(out, conn, availableRoomID, customerID, checkInDate, checkOutDate);
 */