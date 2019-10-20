package servlets.cancelServlets;

import classes.AlterOrder;
import classes.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;

import static java.lang.Integer.parseInt;

@WebServlet(
        name = "CancelPageCustomer",
        urlPatterns = {"/servlets.cancelServlets.CancelPageCustomer"}
)
public class CancelPageCustomer extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'></head>");
        request.getRequestDispatcher("link.html").include(request, response);
        Throwable var4 = null;

        //WIP Du er god kristian husk å skrive kommentarer
        try {

            //init alle variablene som vil bli trengt i cancelpage

            //String til changeName
            String oldname = request.getParameter("oldname");
            String newname = request.getParameter("newname");

            //String til changeEmail
            String oldmail = request.getParameter("oldmail");
            String newmail = request.getParameter("newmail");
           //String til changePhone
            String oldphoneS = request.getParameter("oldphone");
            String newphoneS = request.getParameter("newphone");
            //string til changeDate
            String date = request.getParameter("date");
            //String til changeRoom
            String orderID = request.getParameter("orderid");
            String rom = request.getParameter("rom");
            //String til cancelOrder
            String mail = request.getParameter("mail");
            String phone = request.getParameter("phone");
            //For at servlet skal fungere
            String action = request.getParameter("action");

            //Connection til databasen
            DbTool dbtool = new DbTool();
            Connection conn = dbtool.logIn(out);
            //Alterorder for funksjonen til servlet
            AlterOrder alterOrder = new AlterOrder();

            //tar inn gammelt navn og nytt navn og endrer på det i databasen, kunne fungert bedre om vi hadde et etternavn også da slipper man og bruke kundenummer
            if (action.contains("navn")){
                Cookie userCookie[] = request.getCookies();
                System.out.println(userCookie);
                if(userCookie != null){
                String customerID = userCookie[0].getValue();
                alterOrder.changeName(out,conn,oldname,newname,customerID);
                } else{
                    String customerID = request.getParameter("customerID");
                    alterOrder.changeName(out,conn,oldname,newname,customerID);
                }


            }
            //endrer på emailen verdien til kunden med å ta inn det gamle og den nye emailen.
            else if (action.contains("E-mail")){
                alterOrder.changeEmail(out,conn,oldmail,newmail);

            }
            //tar inn int verdien gammelt telefon nummer og nytt telefonnummer og oppdaterer det i databasen
            else if(action.contains("telefon")){
                int oldphone = Integer.parseInt(oldphoneS);
                int newphone = Integer.parseInt(newphoneS);
                alterOrder.changePhone(out,conn,oldphone,newphone);

            }
            //sjekker om action har order rom i seg utfører så en if statment på select tagen til rom, og sjekker hvilke value select tag har utfører så hva valuen til sier. Kan muligens bli refactored til en switch
            else if(action.contains("rom")){
                if (rom.contains("sr")){
                    System.out.println("It works");
                    alterOrder.changeRoom(out,conn,orderID,"sr01");
                    out.println("<p> endret rom til Singelrom</p>");
                }
                else if(rom.contains("dr")){
                    System.out.println("It works");
                    alterOrder.changeRoom(out,conn,orderID,"dr01");
                    out.println("<p> endret rom til Dobbeltrom</p>");
                }
                else if(rom.contains("fr")){
                    System.out.println("It works");
                    alterOrder.changeRoom(out,conn,orderID,"fr01");
                    out.println("<p>  endret rom til Familierom</p>");
                }
                else if(rom.contains("zj")){
                    System.out.println("It works");
                    alterOrder.changeRoom(out,conn,orderID,"zj01");
                    out.println("<p> endret rom til Suite</p>");
                }
            }
            //kommer minst to else if statments til, som omhandler checkin og checkout date men først må vi fikse databasen på det.
            else if(action.contains("Checkin")){
                alterOrder.changeDate(out,conn,orderID,"checkin",date);
            }
            else if(action.contains("Checkout")){
                alterOrder.changeDate(out,conn,orderID,"checkout",date);
            }
            //Tar inn orderId og mail eller telefon
            else if (action.contains("kansellere")){
                alterOrder.cancelOrder(out,conn,orderID,mail,phone);
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
        try {
            this.processRequest(request,response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.processRequest(request,response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
