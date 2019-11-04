<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 23.10.2019
  Time: 14:44
  When a user/customer has finished a booking, they will be sent to this page.
  This includes an affirmation of the booking being successful and information
  about the booking.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<html>
<head>
    <title>Booking complete</title>
</head>
<body>
<form class="finishedBooking">
<h2>Booking successful!</h2>
    You have booked a <text style="color:firebrick;"><b>${roomType}</b></text><br><br>
    from <text style="color:firebrick;"><b>${checkInDate}</b></text>
    until <text style="color:firebrick;"><b>${checkOutDate}</b></text><br><br>
    Payment option selected: <text style="color:firebrick;"><b>${paymentType}</b></text>
</form>
</body>
</html>