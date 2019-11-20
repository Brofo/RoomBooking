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
        name = "servlets.userServlets.LogOutServlet",
        urlPatterns = {"/servlets.userServlets.LogOutServlet"}
)

/**
 * Når brukeren trykker på "Logg ut" i menyen på toppen av websiden, aktiveres denne servlet.
 * Da blir informasjonskapslen (cookien) til brukeren slettet. Med andre ord, brukeren blir logget ut.
 */
public class LogOutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'></head>");
        // Henter menyen på toppen av websiden.
        request.getRequestDispatcher("link.html").include(request, response);

        // Sjekker om brukeren er logget inn. Hvis den er det, blir cookien slettet.
        Cookie existingCookies[] = request.getCookies();
        if (existingCookies != null) {
            out.println("<p>Your are now logged out</p>");
            out.println("<meta http-equiv=\"Refresh\" content=\"2;url= index.jsp\">");
            existingCookies[0].setMaxAge(0);
            response.addCookie(existingCookies[0]);
        } else {
            out.println("<p>You have to log in before you can log out.<p>");
        }
    }
}
