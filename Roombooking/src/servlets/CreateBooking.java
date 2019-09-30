package servlets;

import classes.CustomerFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;

@WebServlet(
        name = "servlets.CreateBooking",
        urlPatterns = {"/servlets.CreateBooking"}
)

public class CreateBooking extends HttpServlet {
    private CustomerFunctionality cusFunc;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        cusFunc = new CustomerFunctionality(out);
        String name = req.getParameter("name").toLowerCase();
        String email = req.getParameter("email").toLowerCase();
        String phone = req.getParameter("phone").toLowerCase();
        String checkIn = req.getParameter("checkin").toLowerCase();
        String checkOut = req.getParameter("checkout").toLowerCase();
        String action = req.getParameter("createBooking");

        if (action.contains("Create Booking")) {
            try {
                //out.println(cusFunc.getId("Customer","cus_name", name));
                cusFunc.checkForCustomer(name, email, phone);
                //cusFunc.getSingleRecord("bonuspoints", "Customer", "cus_id", "c001");
                //cusFunc.createBooking(name, email, phone, checkIn, checkOut);
            }
            catch (Exception e) {
                out.println("Exeption in createOrder servlet: " + e);
            }
        }
    }
}
