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
    <title>Booking as a loyal member</title>
</head>
<body>
<form action="servlets.bookingServlets.UserBookingServlet">
    Room type: <input type="text" name="roomtype" value="${availableRoomID}"><br>
    Check in date: <input type="date" name="checkin" value ="${checkInDate}"><br>
    Check out date: <input type="date" name="checkout" value="${checkOutDate}"><br>
    Preferences (optional):   <textarea name="preferences" rows="10" cols="50" maxlength = "500" placeholder="Write here..."></textarea><br>

    <input type="submit" name="createBooking" value="Book room">
</form>
</body>
</html>
