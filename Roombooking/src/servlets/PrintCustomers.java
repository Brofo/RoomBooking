
package servlets;

import classes.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;


@WebServlet(
    name = "servlets.PrintCustomers",
    urlPatterns = {"/servlets.PrintCustomers"}
)

/**
 * GetCustomerInfo handles the request of getting customer info.
 * @author  Karl Martin Lund
 * @version 1.0
 * @since
 */

public class PrintCustomers extends HttpServlet {
    /**
     * doGet processes the request of the GetCustomerInfo servlet.
     * @param req
     * @param res
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        String action = req.getParameter("printCustomers");
        CustomerFunctionality cust = new CustomerFunctionality(out);
        try {
            if(action.contains("Print Customers")) {
                cust.printCustomers();
            }
        }
        catch (Exception ex) {
            out.println("Exeption: " + ex);
        }
    }
}
