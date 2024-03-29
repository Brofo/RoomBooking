<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 26.09.2019
  Time: 14:30

  After writing information in the index.jsp form, the user will be sent here if
  it is not logged in. If it chooses not to log in, it will just continue as a
  customer, and it has to type information about themselves as opposed to the
  user that is logged in.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<html>
<head>
    <title>Booking as a guest</title>
</head>
<body>

<form class="bookingCustomer" action ="LogIn.jsp">

    <h2>You are currently booking as a guest</h2>
    If you would like to book as a user, click here:
    <input type="submit" name="LogIn" value="Log In"><br>

</form>

<form class="bookingCustomer" action ="index.jsp">
    Room type:      <b>${roomType}</b><br>
    Check in date:  <b>${checkInDate}</b><br>
    Check out date: <b>${checkOutDate}</b><br>
    Change room type or dates:
    <input type="submit" value="Change">
    <br>
</form>

<form class="bookingCustomer" action="servlets.bookingServlets.BookingServletNavigator" method="post">

    <input type="hidden" name="availableRoomID" value="${availableRoomID}">
    <input type="hidden" name="roomType" value="${roomType}">
    <input type="hidden" name="checkInDate" value="${checkInDate}">
    <input type="hidden" name="checkOutDate" value="${checkOutDate}">

    First name: <input type="text" name="firstname" value="${firstname}"><br>
    Last name: <input type="text" name="lastname" value="${lastname}"><br>
    Email: <input type="email" name="email" value="${email}"><br>
    Phone number: <input type="tel" name="phone" value="${phone}"><br>
    Payment method: <select id="paymentMethod" name="paymentType">
                        <option name="empty">Select...</option>
                        <option name="creditCard">Credit Card</option>
                        <option name="uponArrival">Pay Upon Arrival</option>
    </select><br>
    <span style="color: red; "> ${errorMessage} </span>
   <br>Preferences (optional): <br>
    <textarea name="preferences" rows="10" cols="50" maxlength = "500" placeholder="Write here..."></textarea><br>

    <input type="submit" name="createBooking" value="Book room">
</form>
</body>
</html>
