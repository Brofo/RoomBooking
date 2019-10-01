<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 26.09.2019
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Booking as a guest</title>
</head>
<body>
<form action ="LoggInn.jsp">

    <h2>You are currently booking as a guest</h2>
    If you would like to book as a user, click here:
    <input type="submit" name="LogIn" value="Log In"><br>

</form>
<form action="servlets.bookingServlets.BookingServlet2" method="post">

    <input type="hidden" name="availableRoomID" value="${availableRoomID}">

    Room type: <input type="text" name="roomType" value="${roomType}"><br>
    Check in date: <input type="date" name="checkin" value ="${checkInDate}"><br>
    Check out date: <input type="date" name="checkout" value="${checkOutDate}"><br>
    Name: <input type="text" name="name"><br>
    Email: <input type="email" name="email"><br>
    Phone number: <input type="text" name="phone"><br>
    Preferences (optional): <br><textarea name="preferences" rows="10" cols="50" maxlength = "500" placeholder="Write here..."></textarea><br>

    <input type="submit" name="createBooking" value="Book room">
</form>
</body>
</html>
