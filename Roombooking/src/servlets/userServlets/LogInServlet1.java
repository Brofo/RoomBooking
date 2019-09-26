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
 * videresendt til LoggInn.jsp, som er formen til innloggingsmenyen.
 */
public class LogInServlet1 extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Henter inn menyen på toppen av websiden.
        request.getRequestDispatcher("link.html").include(request, response);

        Cookie existingCookies[] = request.getCookies();
        if (existingCookies != null) {
            String userName = existingCookies[0].getName();
            out.println("Du er allerede logget inn som " + userName);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/LoggInn.jsp");
        }
    }


}
