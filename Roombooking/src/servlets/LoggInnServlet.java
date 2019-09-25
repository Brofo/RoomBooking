package servlets;

import classes.CustomerFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;

@WebServlet(
        name = "LoggInnServlet",
        urlPatterns = {"/servlets.LoggInnServlet"}
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
        if (customerID == null) {
            out.println("Det er ingen bruker tilknyttet denne e-posten.");
        }
    }


}
