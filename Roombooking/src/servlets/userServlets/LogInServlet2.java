package servlets.userServlets;

import classes.CustomerFunctionality;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "servlets.userServlets.LogInServlet2",
        urlPatterns = {"/servlets.userServlets.LogInServlet2"}
)

/**
 * LogInServlet2 har sin funksjonalitet når brukeren forsøker å trykke på "Logg Inn"
 * etter å ha fylt inn innloggingsdetaljene i menyen til LoggInn.jsp.
 */
public class LogInServlet2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        CustomerFunctionality cusFun = new CustomerFunctionality(out);

        // Henter inn menyen på toppen av websiden.
        request.getRequestDispatcher("link.html").include(request, response);

        // Epost og passord er innloggingsdetaljene som brukeren skriver inn.
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Epost brukes til å finne Customer ID.
        // Passordet som hentes er det som er tilknyttet Customer ID.
        // Navnet som hentes er tilknyttet Customer ID.
        String customerID = cusFun.getId("Customer", "cus_email", email);
        String correctPassword = cusFun.getField("password", "Customer", "cus_id", customerID);
        String customerName = cusFun.getField("cus_name", "Customer", "cus_id", customerID);


        if (customerID == null) {
                out.println("Det er ingen bruker tilknyttet denne e-posten.");
                request.getRequestDispatcher("LoggInn.jsp").include(request, response);
            }
            else if (password.equals(correctPassword)) {
                out.println("Du er nå logget inn.");
                out.println("Velkommen " + customerName);

                // Passordet som er skrevet inn matcher passordet i databasen.
                // Oppretter derfor en informasjonskapsel (cookie), slik at brukeren
                // Forblir innlogget.
                Cookie makeCookie = new Cookie(customerName, customerID);
                response.addCookie(makeCookie);

            } else {
                out.println("Passordet er feil! :(");
                request.getRequestDispatcher("LoggInn.jsp").include(request, response);
            }
    }

}
