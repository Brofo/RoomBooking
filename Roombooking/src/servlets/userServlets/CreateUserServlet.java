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

import classes.DbTool;
import classes.Register;

@WebServlet(
        name = "servlets.userServlets.CreateUserServlet",
        urlPatterns = {"/servlets.userServlets.CreateUserServlet"}
                )

public class CreateUserServlet extends HttpServlet {
    private Register regUser;

    public CreateUserServlet() {
        regUser = new Register();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

            //Informasjonen fra parameterne, altså tekstfeltene som brukeren skriver i,
            //blir puttet inn i databasen, slik at brukeren blir opprettet.
            regUser.registerUser(out, name, email, phone, password);
            out.println("Du har nå opprettet en bruker. Nå kan du logge inn med denne brukeren.");
            request.getRequestDispatcher("LoggInn.jsp").include(request, response);

    }


}