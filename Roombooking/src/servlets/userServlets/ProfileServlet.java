package servlets.userServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlets.userServlets.ProfileServlet",
            urlPatterns = "/servlets.userServlets.ProfileServlet")
public class ProfileServlet extends HttpServlet {

    /**
     * Denne servlet sjekker om brukeren er logget inn, og gir dermed tilgang
     * til brukerens profil.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Henter menyen på toppen av websiden.
        request.getRequestDispatcher("link.html").include(request, response);

        // Henter informasjonskapselen (cookie) hvis den finnes, og putter den i en Array.
        Cookie ck[] = request.getCookies();

        // Hvis cookien finnes, får brukeren som eier cookien tilgang til sin profil.
        if(ck != null) {
            String name = ck[0].getName();
            out.println("Velkommen til din profil, " + name);
        }
        else{
            out.print("Vennligst logg inn først.");
            request.getRequestDispatcher("LoggInn.jsp").include(request, response);
        }
    }
}
