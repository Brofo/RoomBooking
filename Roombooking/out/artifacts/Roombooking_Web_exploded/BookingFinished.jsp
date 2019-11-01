<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 23.10.2019
  Time: 14:44
  When a user/customer has finished a booking, they will be sent to this page.
  This includes an affirmation of the booking being successful and information
  about the booking, as well as button to navigate to other pages.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<html>
<head>
    <title>Booking successful</title>
</head>
<body>
<h2>Booking successful!</h2><br>
You have booked a ${roomType} <br>
from ${checkInDate}<br>
until ${checkOutDate}<br>
Payment type: ${paymentType}<br>
</body>
</html>