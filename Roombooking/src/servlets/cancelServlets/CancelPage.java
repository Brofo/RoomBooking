package servlets.cancelServlets;

import classes.AlterOrder;
import classes.CustomerFunctionality;
import classes.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



import static java.lang.Integer.parseInt;

@WebServlet(
        name = "CancelPage",
        urlPatterns = {"/servlets.cancelServlets.CancelPage"}
)
public class CancelPage extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<head><link rel='stylesheet' type='text/css' href='css/indexStyle.css'></head>");
        request.getRequestDispatcher("link.html").include(request, response);
        Throwable var4 = null;
        Cookie userCookie[] = request.getCookies();
        CustomerFunctionality cusFun = new CustomerFunctionality(out);

        try {
            if(userCookie != null){
                String cID = userCookie[0].getValue();

                String firstName = cusFun.getField("cus_firstname","customer","cus_id", cID);
                String lastName = cusFun.getField("cus_lastname", "customer","cus_id", cID);
                String eMail = cusFun.getField("cus_email","customer","cus_id", cID);
                String phone = cusFun.getField("cus_phone","customer","cus_id", cID);


                request.setAttribute("firstname", firstName);
                request.setAttribute("lastname", lastName);
                request.setAttribute("email", eMail);
                request.setAttribute("phone", phone);

                request.getRequestDispatcher("cancelPageUser.jsp").forward(request, response);



            } else{
                request.getRequestDispatcher("LogIn.jsp").forward(request, response);

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
            destroy();

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.processRequest(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            destroy();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.processRequest(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            destroy();
        }

    }
}
