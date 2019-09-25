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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("link.html").include(request, response);

        Cookie ck[] = request.getCookies();
        if (ck != null) {
            String userID = ck[0].getValue();
            out.println(userID);
            out.println(ck.length);
            out.print("<b>Welcome to Profile</b>");
        } else {
            response.sendRedirect(request.getContextPath() + "/LoggInn.jsp");
        }
    }
}
