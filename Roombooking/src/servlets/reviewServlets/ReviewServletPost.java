package servlets.reviewServlets;

import classes.Reviews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlets.reviewServlets.ReviewServletPost",
        urlPatterns = {"/servlets.reviewServlets.ReviewServletPost"}
        )
/**
 * This servlet will post data into the database when a user makes a new review.
 */
public class ReviewServletPost extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Reviews rw = new Reviews();
        String review = request.getParameter("review");
        String rating = request.getParameter("rating");
        String displayname = request.getParameter("displayname");

        if (displayname.equals("")){
            displayname = "Guest";
        }
        if (rating.contains("Select")) {
            request.setAttribute("errorMessage", "Please select a rating score.");
            request.getRequestDispatcher("servlets.reviewServlets.ReviewServletGet").forward(request, response);
        } else {
            rw.registerReview(out, review, rating, displayname);
            request.getRequestDispatcher("servlets.reviewServlets.ReviewServletGet").forward(request, response);
        }
    }
}
