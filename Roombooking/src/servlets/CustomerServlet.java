package servlets;

import classes.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;

@WebServlet(
    name = "servlets.CustomerServlet",
    urlPatterns = {"/servlets.CustomerServlet"}
)






public class CustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        String action = req.getParameter("getInfo");
        Customer cust = new Customer();

        try {
            if(action.contains("getInfo")) {
                out.println("Hallo");
                out.println(cust.getInfo());
            }
        }
        catch (Exception ex) {
            out.println("Exeption: " + ex);
        }



        

    }
}
