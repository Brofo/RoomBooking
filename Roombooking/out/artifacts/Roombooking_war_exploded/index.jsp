<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 11.09.2019
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
  <title>Rombukser</title>
</head>
<body>

<form action="servlets.Servlet">
  <a href="index.jsp"> <b>Forsiden</b></a>   |
  <a href="servlets.userServlets.LogInServlet1">Logg inn</a> |
  <a href="servlets.userServlets.ProfileServlet">Min profil</a> |
  <a href="servlets.userServlets.LogOutServlet">Logg ut</a>
  <hr>
  Navn: <input type="text" name="name">
  Email: <input type="text" name="email">
  Telefon: <input type="text" name="phone">
  <input type="submit" name ="action"  value="Bestille">
  <input type="submit" name ="action"  value="Vis alle bestillinger">
  <input type="submit" name="action" value="Kansellere bestilling">
    <input type="submit" name ="action" value ="Opprett bruker">
  <input type="submit" name ="action"  value="Vis romtyper :)">
  <input type="submit" name ="action"  value="Vis priser for rom">

  <br>
  Preferanser for bestilling:
  <br>
  <textarea rows="10" cols="50" maxlength = "500" placeholder="Preferanser...(maks 500 karakterer)">
</textarea>
</form>

<form action="./servlets.CreateBooking" method="post">
  Name: <input type="text" name="name"><br>
  Email: <input type="email" name="email"><br>
  Phone number: <input type="text" name="phone"><br>
  Check in date: <input type="date" name="checkin"><br>
  Check out date: <input type="date" name="checkout"><br>
  <input type="submit" name="createBooking" value="Create Booking">
</form>



</body>
</html>