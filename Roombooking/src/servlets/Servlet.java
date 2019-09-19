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
import classes.DbFunctionality;
import classes.Register;


@WebServlet(
        name = "servlets.Servlet",
        urlPatterns = {"/servlets.Servlet"}
)
public class Servlet extends HttpServlet {
   private Register reg;

    public Servlet() {
        reg = new Register();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Throwable var4 = null;

        try {

            String navn = request.getParameter("navn");
            String email = request.getParameter("email");
            String telefon = request.getParameter("telefon");
            String action = request.getParameter("action");

            DbTool dbtool = new DbTool();
            Connection conn = dbtool.loggInn(out);

            out.println("Log in complete ");
            DbFunctionality dbfunctionality = new DbFunctionality();

            if (action.contains("Bestille")) {
                out.println("Registrer valgt ");
                reg.registerCustomer(out, conn, navn, email, telefon);


            } else if (action.contains("alle")) {
                out.println("Valg, skrive alle tabeller");
                dbfunctionality.printName(out, conn);
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