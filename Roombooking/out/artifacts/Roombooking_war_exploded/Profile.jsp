<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 02.10.2019
  Time: 17:25

  This file is the main file for the profile. This is where the
  user can navigate between the different options on the profile,
  and find information about their own user data.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@include  file="link.html"%>
<html>
<head>
    <title>My Profile</title>
</head>
<body>

    <h1>Welcome to your profile, ${firstname} </h1>
    <h2></h2>
    <h3>Bonuspoints: ${bonus}</h3>

    <form class="" action="Bonusinfo.jsp" method="get">
        <input class="profile" type="submit" name="action" value="About Bonuspoints">
    </form>

    <form class="seeBookings" action="servlets.profileServlets.SeeBookings" method="get">
        <input class="profile" type="submit" name="action" value="My bookings">
    </form>

    <form class="cancelPage" action="servlets.cancelServlets.CancelPage">
        <input  class="profile" type="submit" name="action" value="Manage account & bookings">
    </form>
</body>
</html>
