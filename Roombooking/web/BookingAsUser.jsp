<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 26.09.2019
  Time: 14:30

  After writing information in the index.jsp file, the user will be sent
  to this form if it is logged in. The data about the user will be
  automatically retrieved, and the user does not have to type it again.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<html>
<head>
    <title>Booking as a loyal member</title>
</head>
<body>

<form class="bookingUser" action ="index.jsp">
    <h2>You are booking as ${username}</h2>
    Room type:      <b>${roomType}</b><br>
    Check in date:  <b>${checkInDate}</b><br>
    Check out date: <b>${checkOutDate}</b><br>
    Change room type or dates:
    <input type="submit" value="Change">
    <br>
</form>

<form class="bookingUser" action="servlets.bookingServlets.BookingServletNavigator" method="get">

    <input type="hidden" name="availableRoomID" value="${availableRoomID}">
    <input type="hidden" name="roomType" value="${roomType}">
    <input type="hidden" name="checkInDate" value="${checkInDate}">
    <input type="hidden" name="checkOutDate" value="${checkOutDate}">

    Preferences (optional):<br>
    <textarea name="preferences" rows="10" cols="50" maxlength = "500" placeholder="Write here..."></textarea><br>

    <input type="submit" name="createBooking" value="Book room">
</form>
</body>
</html>
