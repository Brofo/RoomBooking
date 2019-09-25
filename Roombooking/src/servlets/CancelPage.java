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

import static java.lang.Integer.parseInt;

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
            String oldphoneS = request.getParameter("oldphone");
            String newphoneS = request.getParameter("newphone");
            String orderID = request.getParameter("orderid");
            String customerID = request.getParameter("customerID");
            String rom = request.getParameter("rom");
            String action = request.getParameter("action");

            DbTool dbtool = new DbTool();
            Connection conn = dbtool.logIn(out);

            AlterOrder alterOrder = new AlterOrder();
            out.println("Up to date with Database");

            if (action.contains("navn")){
                alterOrder.changeName(out,conn,oldname,newname,customerID);
            }
            else if (action.contains("E-mail")){
                alterOrder.changeEmail(out,conn,oldmail,newmail);

            }
            else if(action.contains("telefon")){
                int oldphone = Integer.parseInt(oldphoneS);
                int newphone = Integer.parseInt(newphoneS);
                alterOrder.changePhone(out,conn,oldphone,newphone);

            }
            else if(action.contains("rom")){
                out.println(rom);
                if (rom.contains("sr")){
                    System.out.println("It works");
                    alterOrder.changeRom(out,conn,orderID,"sr01");
                }
                else if(rom.contains("dr")){
                    System.out.println("It works");
                    alterOrder.changeRom(out,conn,orderID,"dr01");
                }
                else if(rom.contains("fr")){
                    System.out.println("It works");
                    alterOrder.changeRom(out,conn,orderID,"fr01");
                }
                else if(rom.contains("zj")){
                    System.out.println("It works");
                    alterOrder.changeRom(out,conn,orderID,"zj01");
                }
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
        System.out.println("Why?");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request,response);
        System.out.println("Help");
    }
}
