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
        name = "servlets.userServlets.LoggInnServlet",
        urlPatterns = {"/servlets.userServlets.LoggInnServlet"}
)
public class LoggInnServlet extends HttpServlet {

    public LoggInnServlet() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        CustomerFunctionality cusFun = new CustomerFunctionality(out);

        request.getRequestDispatcher("link.html").include(request, response);


        String email = request.getParameter("email");
        String password = request.getParameter("password");


        String customerID = cusFun.getId("Customer", "cus_email", email);
        // String correctPassword = cusFun.getPassword();
        if (customerID == null) {
            out.println("Det er ingen bruker tilknyttet denne e-posten.");
        }
        /**
        else if (password.equals(correctPassword)) {
            out.println("Du er nå logget inn./n");
            out.println("Velkommen "); //Legg til navn her.

           Cookie ck = new Cookie("user", customerID);
            response.addCookie(ck);
            //Legg til knapp eller gjør slik at man kommer til forsiden.
        }
        else {
            out.println("Passordet er feil! :(")
        }

*/
    }


}
