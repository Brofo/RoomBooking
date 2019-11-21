package servlets.userServlets;

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

@WebServlet(
        name = "servlets.userServlets.LogInServlet2",
        urlPatterns = {"/servlets.userServlets.LogInServlet2"}
)

/**
 * This servlet is activated when the user attempts to log in after typing
 * the log in details. If the details are incorrect, there will be an error.
 * If the details are correct, a Cookie will be created with the user information.
 */
public class LogInServlet2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DbLib fun = new DbLib(out);

        // Get the meny bar on the top of the website.
        request.getRequestDispatcher("link.html").include(request, response);

        // Get parameters from the log in page.
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Email is used to find customer ID.
        // The password, name and bonus points are found from the customers ID.
        String customerID = null;
        String correctPassword = null;
        String customerFirstName = null;
        String bonus = null;

        try {
            customerID = fun.getField("cus_id", "Customer", "cus_email", email);
            correctPassword = fun.getField("cus_password", "Customer", "cus_id", customerID);
            customerFirstName = fun.getField("cus_firstname", "Customer", "cus_id", customerID);
            bonus = fun.getField("cus_bonuspoints","customer","cus_id",customerID);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customerID == null) {
                // Creates parameter for the errorMessage in LogIn.jsp.
                request.setAttribute("errorMessage","There is no account associated with this E-mail.");
                request.getRequestDispatcher("LogIn.jsp").forward(request, response);
            }
            else if (password.equals(correctPassword)) {
            // The password that was written, matches the password in the database.

            //Cookies can not handle whitespace. Replace potential whitespace with underline.
            assert customerFirstName != null;
            customerFirstName = customerFirstName.replaceAll("\\s+","_");

                // Create a Cookie to keep the user logged in.
                Cookie makeCookie = new Cookie(customerFirstName, customerID);
                response.addCookie(makeCookie);


                // Uses the getField method to get cus_bonuspoints from the database and tie it to bonus.
                // Saves the customer's name in the session.
                request.setAttribute("firstname", customerFirstName);
                request.setAttribute("bonus", bonus);
                // Sends the customer's name to Profile.jsp.
                request.getRequestDispatcher("Profile.jsp").forward(request, response);


            } else {
                // Creates parameter for the errorMessage in LogIn.jsp.
                request.setAttribute("errorMessage","Incorrect password. Please try again");
                request.getRequestDispatcher("LogIn.jsp").forward(request, response);
            }
    }
}
