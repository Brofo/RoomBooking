package servlets.reviewServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import classes.Reviews;


@WebServlet(name = "servlets.reviewServlets.ReviewServletGet",
        urlPatterns = {"/servlets.reviewServlets.ReviewServletGet"}
)
/**
 * This servlets gets the data about reviews from the database, in order to
 * print the reviews on the review page, and get the review scores.
 */
public class ReviewServletGet extends HttpServlet {

    private Reviews rewFunc;
    private String averageReview;

    public ReviewServletGet() {
        rewFunc = new Reviews();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        averageReview = rewFunc.getAverageReview(out);
        ArrayList<String> reviewArray = rewFunc.getReviewArrays(out);

        // This loop will create several parameter values that can be accessed in the
        // Reviews.jsp file.
        int i = 0;
                for (String reviewData : reviewArray) {
                    request.setAttribute("reviewdata" + i, reviewData);
                    i++;
                }

        request.setAttribute("averageReview", averageReview);
        request.getRequestDispatcher("Reviews.jsp").forward(request, response);
    }
}
