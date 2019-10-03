package servlets;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.DbTool;
import classes.Register;
import classes.RoomTypes;


@WebServlet(
        name = "servlets.Servlet",
        urlPatterns = {"/servlets.Servlet"}
)
public class Servlet extends HttpServlet {
   private RoomTypes roomTyp;
   private Register reg;

    public Servlet() {

        roomTyp = new RoomTypes();
        reg = new Register();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Throwable var4 = null;

        try {

            String action = request.getParameter("action");

            DbTool dbtool = new DbTool();
            Connection conn = dbtool.logIn(out);

            out.println("Log in worked");


            if (action.contains("alle")) {
                out.println("Valgt, skriver ut alle romIDer og romtyper");
                roomTyp.getRoomID(out, conn);
            }
             else if (action.contains("Kansellere")){

                 //Sender brukeren videre til en side hvor den eventuelt kan gjøre endring og/eller kansellere
                 out.println("Valgt å kansellere bestilling");
                 response.sendRedirect(request.getContextPath() + "/cancelPage.jsp");
            }
            else if (action.contains("Opprett bruker")){
                response.sendRedirect(request.getContextPath() + "/CreateUser.jsp");
            }


        } catch (Throwable var17) {
            var4 = var17;
            throw var17;
        } finally {
            if (out != null) {
                if (var4 != null) {
                    try {
                        out.close();
                    } catch (Throwable var16) {
                        var4.addSuppressed(var16);
                    }
                } else {
                    out.close();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}