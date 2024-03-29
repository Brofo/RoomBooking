package servlets.bookingServlets;

import classes.DbLib;
import classes.Email;
import classes.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.bookingServlets.BookingServlet2",
        urlPatterns = {"/servlets.bookingServlets.BookingServlet2"}
)

/**
 * This servlet is used when either a logged in user, or a customer is at the last step
 * of booking a room. If it is a user, an order will be registered, using the user's customer ID.
 * If it is a customer, the customer will be registered first, and then the order.
 */
public class BookingServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        DbLib fun = new DbLib(out);
        Register reg = new Register();

        // Getting menu bar on the top of the website
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("link.html").include(request, response);
         out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'></head>");

        // Values from parameters
        String availableRoomID = request.getParameter("availableRoomID");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String preferences = request.getParameter("preferences");
        String paymentType = request.getParameter("paymentType");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        String roomType = request.getParameter("roomType");

        String customerID;

        // Expression to make sure the payment was successful.
        // Will only be set to false if the user does not have enough bonus points.
        boolean paymentSuccess = true;

        // Requesting cookie to check if a user is logged in
        Cookie existingCookies[] = request.getCookies();

        try {
        //Check whether the customer is logged in or not.
        if (existingCookies != null) {
            // The user is logged in. Fetch CustomerID from cookie:
            customerID = existingCookies[0].getValue();

            //If the user paid with card, add bonuspoints to the user:
            if (paymentType.contains("Card")) {
                int bonuspointsAquired = 0;
                // Calculate the amount of bonuspoints the user will get.
                if (roomType.contains("Single")) {
                    bonuspointsAquired = 2500;
                } else if (roomType.contains("Double")) {
                    bonuspointsAquired = 5000;
                } else if (roomType.contains("Family")) {
                    bonuspointsAquired = 7500;
                } else if (roomType.contains("Suite")) {
                    bonuspointsAquired = 10000;
                }
                fun.alterBonusPoints(customerID, bonuspointsAquired);

                // Register the order, using the users Customer ID.
                reg.inputRecordInOrders(availableRoomID, customerID, checkInDate, checkOutDate, preferences, paymentType);

                // If the user paid with bonuspoints, remove the points from the user:
            } else if (paymentType.contains("Bonuspoints")) {
                int bonuspointsPrice = 0;

                // Calculate the amount of bonuspoints the user will be charged.
                if (roomType.contains("Single")) {
                    bonuspointsPrice = 25000;
                } else if (roomType.contains("Double")) {
                    bonuspointsPrice = 50000;
                } else if (roomType.contains("Family")) {
                    bonuspointsPrice = 75000;
                } else if (roomType.contains("Suite")) {
                    bonuspointsPrice = 100000;
                }

                // Check if the customer has enough bonuspoints for the order.
                int currentBonuspoints = 0;

                    currentBonuspoints = Integer.parseInt(fun.getField("cus_bonuspoints",
                            "customer", "cus_id", customerID));

                if (currentBonuspoints < bonuspointsPrice) {
                    //The user does not have enough points.
                    paymentSuccess = false;
                    request.setAttribute("errorMessage","You do not have enough bonus points to make this order.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    // The user does have enough points. Subtract bonuspoints from the user.
                    bonuspointsPrice = -bonuspointsPrice;

                    // Register the order, using the users Customer ID.
                    reg.inputRecordInOrders(availableRoomID, customerID, checkInDate, checkOutDate, preferences, paymentType);
                    fun.alterBonusPoints(customerID, bonuspointsPrice);

                }
            } else {
                // The user selected to pay upon arrival. This requires no further
                // action than to register the order, using the users Customer ID.
                reg.inputRecordInOrders(availableRoomID, customerID, checkInDate, checkOutDate, preferences, paymentType);
            }

        }

        else {
            //The customer is not logged in. Register the customer in the database.
            //Then use the customer ID of this customer to register an order.
                customerID = reg.getCustomerAndUserID(out);
                reg.registerCustomer(out, customerID, firstname, lastname, email, phone);
                reg.inputRecordInOrders(availableRoomID, customerID, checkInDate, checkOutDate, preferences, paymentType);

        }

        if (paymentSuccess) {
            //Get email from user:
            email = fun.getField("cus_email", "Customer", "cus_id", customerID);
            firstname = fun.getField("cus_firstname", "Customer", "cus_id", customerID);
            lastname = fun.getField("cus_lastname", "Customer", "cus_id", customerID);
            //Send email confirmation.
            String subject = "Order Confirmation";
            String text = "You have successfully created a booking. \n\n" + firstname + " " + lastname + "\n" +
                    "Room: " + roomType + "\nCheck in date: " + checkInDate + "\n" +
                    "Check out date: " + checkOutDate + "\n\nThank you for choosing Cohesion Hotel";
            try {
                Email.sendMail(email, subject, text);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        request.setAttribute("roomType", roomType);
        request.setAttribute("checkInDate", checkInDate);
        request.setAttribute("checkOutDate", checkOutDate);
        request.setAttribute("paymentType", paymentType);
        request.getRequestDispatcher("BookingFinished.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}