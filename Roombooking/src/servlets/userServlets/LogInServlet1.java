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
        name = "servlets.userServlets.LogInServlet1",
        urlPatterns = {"/servlets.userServlets.LogInServlet1"}
        )

/**
 * This servlet is activated when a person tries to access the Log In page.
 * The functionality it provides, is to check whether this person is already logged in.
 * If so, they are sent directly to their profile. If not, they are sent to the
 * Log In page.
 */
public class LogInServlet1 extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Cookie existingCookies[] = request.getCookies();
        if (existingCookies != null) {
            PrintWriter out = response.getWriter();
            DbLib fun = new DbLib(out);

            // The user is already logged in, and will be sent directly to their profile.
            String firstname = existingCookies[0].getName();
            String cID = existingCookies[0].getValue();
            String bonus = null;
            try {
                bonus = fun.getField("cus_bonuspoints","customer","cus_id",cID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("firstname", firstname);
            request.setAttribute("bonus",bonus);

            request.getRequestDispatcher("Profile.jsp").forward(request, response);
        }
        else {
            // The user is not logged in, and will be sent to the LogIn site.
            response.sendRedirect(request.getContextPath() + "/LogIn.jsp");
        }
    }


}
