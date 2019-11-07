package servlets;

import classes.DbTool;
import classes.Reviews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "servlets.servlets.Review",
        urlPatterns = {"/servlets.servlets.Review"})
public class Review extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Reviews rw = new Reviews();
        String review = request.getParameter("review");

        rw.registerReview(out,review);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        Reviews re = new Reviews();
        String review = request.getParameter("review");
        out.println(re.getReviews(out));
    }
}
