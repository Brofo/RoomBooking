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

  <input type="submit" name ="action"  value="Vis alle bestillinger">
  <input type="submit" name ="action"  value="Vis romtyper :)">
  <input type="submit" name ="action"  value="Vis priser for rom">
  <br>

</form>

<form action="servlets.bookingServlets.BookingServlet1" method="post">

  <label for="roomChoice">Please choose room type: </label>
  <select id="roomChoice" name="roomType">
    <option name="Empty">Select...</option>
    <option name="Single room">Single room</option>
    <option name="Double room">Double room</option>
    <option name="Family room">Family room</option>
    <option name="Suite">Suite</option>
  </select><br>

  Check in date: <input type="date" name="checkin"><br>
  Check out date: <input type="date" name="checkout"><br>
  <input type="submit" name="createBooking" value="Create Booking">
</form>



</body>
</html>