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
 * etter å ha fylt inn innloggingsdetaljene i menyen til LogIn.jsp.
 */
public class LogInServlet2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
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
        String customerID = cusFun.getField("cus_id", "Customer", "cus_email", email);
        String correctPassword = cusFun.getField("cus_password", "Customer", "cus_id", customerID);
        String customerFirstName = cusFun.getField("cus_firstname", "Customer", "cus_id", customerID);


        if (customerID == null) {
                // Creates parameter for the errorMessage in LogIn.jsp.
                request.setAttribute("errorMessage","There is no account associated with this E-mail.");
                request.getRequestDispatcher("LogIn.jsp").forward(request, response);
            }
            else if (password.equals(correctPassword)) {
                // Passordet som er skrevet inn matcher passordet i databasen.
                // Oppretter derfor en informasjonskapsel (cookie), slik at brukeren
                // Forblir innlogget.
                Cookie makeCookie = new Cookie(customerFirstName, customerID);
                response.addCookie(makeCookie)
                ;
            Cookie ck[] = request.getCookies();
            String cID = ck[0].getValue();
            String firstname = ck[0].getName();
            //Uses the getField method to get cus_bonuspoints from the database and tie it to bonus
            String bonus = cusFun.getField("cus_bonuspoints","customer","cus_id",cID);
            request.setAttribute("firstname", firstname);
            request.setAttribute("bonus", bonus);


                // Saves the customer's name in the session.
                request.setAttribute("firstname", customerFirstName);
                // Sends the customer's name to Profile.jsp.
                request.getRequestDispatcher("Profile.jsp").forward(request, response);


            } else {
                // Creates parameter for the errorMessage in LogIn.jsp.
                request.setAttribute("errorMessage","Incorrect password. Please try again");
                request.getRequestDispatcher("LogIn.jsp").forward(request, response);
            }
    }

}
