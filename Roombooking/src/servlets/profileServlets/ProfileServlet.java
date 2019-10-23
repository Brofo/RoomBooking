package servlets.profileServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servlets.profileServlets.ProfileServlet",
            urlPatterns = "/servlets.profileServlets.ProfileServlet")
public class ProfileServlet extends HttpServlet {

    /**
     * Denne servlet sjekker om brukeren er logget inn, og gir dermed tilgang
     * til brukerens profil.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Henter informasjonskapselen (cookie) hvis den finnes, og putter den i en Array.
        Cookie ck[] = request.getCookies();

        if(ck != null) {
            // The user is logged in, because a cookie was detected. The user is welcomed
            // with their own name, and will have access to their personal data.
            String firstname = ck[0].getName();

            request.setAttribute("firstname", firstname);
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
        }
        else{
            // Not logged in. Sends the user to the Log In page, with the error message.
            request.setAttribute("errorMessage", "Please log in to access your profile.");
            request.getRequestDispatcher("LogIn.jsp").include(request, response);
        }
    }
}
