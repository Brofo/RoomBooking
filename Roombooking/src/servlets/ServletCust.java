package servlets;

import classes.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*@WebServlet(name = "ServletCust",
            urlPatterns = {"/servlets.ServletCust"}
)
public class ServletCust extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String action = req.getParameter("getInfo");
        Customer cust = new Customer();

        try {
            if(action.contains("Get Customer Info")) {
                out.println("Hallo");
                out.println(cust.getInfo());
            }
        }
        catch (Exception ex) {
            out.println("Exeption: " + ex);
        }
    }
}
