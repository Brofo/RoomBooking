package servlets;

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
        name = "servlets.CreateUserServlet",
        urlPatterns = {"/servlets.CreateUserServlet"}
                )
public class CreateUserServlet extends HttpServlet {
    private Register regUser;

    public CreateUserServlet(){
        regUser = new Register();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String action = request.getParameter("action");

            DbTool dbtool = new DbTool();
            Connection conn = dbtool.logIn(out);

            if (action.contains("Opprett bruker")) {
                regUser.registerUser(out, conn, name, email, phone, password);
            }
    }
}