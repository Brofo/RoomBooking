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

<form action="servlets.Servlet">
  <a href="index.jsp"> <b>Forsiden</b></a> |
  <a href="servlets.userServlets.ProfileServlet">Min profil</a>
  <hr>
  Navn: <input type="text" name="name">
  Email: <input type="text" name="email">
  Telefon: <input type="text" name="phone">
  <input type="submit" name ="action"  value="Bestille">
  <input type="submit" name ="action"  value="Vis alle bestillinger">
  <input type="submit" name ="action"  value="Opprett bruker">
  <br>
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