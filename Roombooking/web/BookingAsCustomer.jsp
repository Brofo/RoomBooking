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

    If you have an account, you can log in here: <br>
    <input type="submit" name="LogIn" value="Log In"><br>
    If not, fill in the empty spaces to continue as a guest.

</form>
<form action="servlets.bookingServlets.CustomerBookingServlet">

    <input type="hidden" name="availableRoomID" value="${availableRomID}">

    Room type: <input type="text" name="roomtype" value="${roomType}"><br>
    Check in date: <input type="date" name="checkin" value ="${checkInDate}"><br>
    Check out date: <input type="date" name="checkout" value="${checkOutDate}"><br>
    Name: <input type="text" name="name"><br>
    Email: <input type="email" name="email"><br>
    Phone number: <input type="text" name="phone"><br>
    Preferences (optional):   <textarea name="preferences" rows="10" cols="50" maxlength = "500" placeholder="Write here..."></textarea><br>

    <input type="submit" name="createBooking" value="Book room">
</form>
</body>
</html>
