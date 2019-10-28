<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 11.09.2019
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<html>
<head>
  <title>Rombukser</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>Hotel booking service</h1>


<form action="servlets.bookingServlets.BookingServlet1" method="post">

  <label for="roomChoice">Please choose room type: </label>
  <select id="roomChoice" name="roomType">
    <option name="Empty">Select...</option>
    <option name="Single room">Single room</option>
    <option name="Double room">Double room</option>
    <option name="Family room">Family room</option>
    <option name="Suite">Suite</option>
  </select>

  <p>Check in date: <input type="date" name="checkin"></p>
  <p>Check out date: <input type="date" name="checkout"></p>
  <input class="button" type="submit" name="createBooking" value="Create Booking"><br>

  <span style="color: red; "> ${errorMessage} </span><br>
</form>

</body>
</html>