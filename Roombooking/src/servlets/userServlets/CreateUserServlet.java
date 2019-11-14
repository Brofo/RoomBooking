/**
 * This servlet is used when someone wants to create a user.
 * The information will be stored in Customer table in the database.
 */

package servlets.userServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import classes.DbTool;
import classes.Register;

@WebServlet(
        name = "servlets.userServlets.CreateUserServlet",
        urlPatterns = {"/servlets.userServlets.CreateUserServlet"}
                )

/**
 * This servlet receives the parameters from the CreateUser.jsp site, which contains
 * information about the user that wants to be registered. It puts the information
 * into the database, in order to register the user.
 */

public class CreateUserServlet extends HttpServlet {
    private Register regUser;

    public CreateUserServlet() {
        regUser = new Register();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

            //Informasjonen fra parameterne, altså tekstfeltene som brukeren skriver i,
            //blir puttet inn i databasen, slik at brukeren blir opprettet.
        try {
            regUser.registerUser(out, firstname, lastname, email, phone, password);
            request.getRequestDispatcher("LogIn.jsp").include(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


}