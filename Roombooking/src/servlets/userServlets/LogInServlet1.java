package servlets.userServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "servlets.userServlets.LogInServlet1",
        urlPatterns = {"/servlets.userServlets.LogInServlet1"}
        )

/**
 * LogInServlet1 brukes kun til å sjekke om brukeren allerede er logget inn.
 * Hvis brukeren er logget inn, får den beskjed om dette. Hvis ikke, blir den
 * videresendt til LogIn.jsp, som er formen til innloggingsmenyen.
 */
public class LogInServlet1 extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Cookie existingCookies[] = request.getCookies();
        if (existingCookies != null) {
            // The user is already logged in, and will be sent directly to their profile.
            String name = existingCookies[0].getName();
            request.setAttribute("name", name);
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
        }
        else {
            // The user is not logged in, and will be sent to the LogIn site.
            response.sendRedirect(request.getContextPath() + "/LogIn.jsp");
        }
    }


}
