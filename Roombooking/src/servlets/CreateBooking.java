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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(
        name = "servlets.CreateBooking",
        urlPatterns = {"/servlets.CreateBooking"}
)

public class CreateBooking extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        CustomerFunctionality cusFunc = new CustomerFunctionality(out);
        String name = req.getParameter("name").toLowerCase();
        String email = req.getParameter("email").toLowerCase();
        String phone = req.getParameter("phone").toLowerCase();
        String roomType = req.getParameter("roomType").toLowerCase();
        String checkIn = req.getParameter("checkin");
        String checkOut = req.getParameter("checkout");
        String preferences = ("her skal formen til Dan og Erlend inn.").toLowerCase();
        String paymentType = req.getParameter("paymentType");
        String action = req.getParameter("createBooking");

        if (action.contains("Create Booking")) {
            try {
                cusFunc.checkIfRoomAvailable(name, email, phone, roomType, checkIn, checkOut, preferences, paymentType);
            } catch (ParseException | SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            out.println("What the fuck?");
        }
    }
}
