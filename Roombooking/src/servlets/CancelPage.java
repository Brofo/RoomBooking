package servlets;

import classes.AlterOrder;
import classes.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(
        name = "CancelPage",
        urlPatterns = {"/servlets.CancelPage"}
)
public class CancelPage extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Throwable var4 = null;
        //WIP Du er god kristian husk å skrive kommentarer
        try {
            //init alle variablene som vil bli trengt i cancelpage
            String oldname = request.getParameter("oldname");
            String newname = request.getParameter("newname");
            String oldmail = request.getParameter("oldmail");
            String newmail = request.getParameter("newmail");
            String oldphone = request.getParameter("oldphone");
            String newphone = request.getParameter("newphone");
            String orderID = request.getParameter("orderid");
            String action = request.getParameter("action");

            DbTool dbtool = new DbTool();
            Connection conn = dbtool.logIn(out);

            AlterOrder alterOrder = new AlterOrder();
            out.println("Up to date with Database");

            if (action.contains("navn")){
                alterOrder.changeName(out,conn,oldname,newname);
            }
            else if (action.contains("E-mail")){


            }
            else if(action.contains("telefon")){


            }
            else if (action.contains("kansellere")){


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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request,response);
    }
}
