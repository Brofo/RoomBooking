<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 11.09.2019
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Rombukser</title>
  </head>
  <body>

  <form action="./servlets.Servlet">
    Fornavn: <input type="text" name="navn">
    Dato<input type="date" id="start" name="dato"
               min="2019-01-01"
               max="2099-12-31">

    <input type="submit" name ="action"  value="Registrer">
    <input type="submit" name ="action"  value="Vis alle">
  </form>

    <br>

  <form action="Registrer">
    Name: <input type="text" name="name"><br>
    Phone number: <input type="text" name="phone"><br>
    Email: <input type="email" name="email"><br>
    <input type="submit" name="registrer" value="Registrer">
  </form>
  <br>

  <form action="./servlets.CustomerServlet" method="get">
    <input type="submit" name="getInfo" value="Get Customer Info">
  </form>



  </body>
</html>
