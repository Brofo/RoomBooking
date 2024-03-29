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

import classes.Email;
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

        //Check if the user  inserted all the correct values.
        if (firstname.equals("") || lastname.equals("") || phone.equals("") || email.equals("") || password.equals("")) {
            request.setAttribute("errorMessage", "Please do not leave any empty fields");
            request.getRequestDispatcher("CreateUser.jsp").forward(request, response);
        } else {

            //Insert the information written by the user into the database.
            try {
                regUser.registerUser(out, firstname, lastname, email, phone, password);
                request.getRequestDispatcher("LogIn.jsp").include(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Email
            String subject = "Created user in Cohesion Hotel";
            String text = "Congratulations with your new user! \n Now you can log in with your Email and Password.";
            try {
                Email.sendMail(email, subject, text);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}