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

    <h2>Welcome to your profile, ${name}</h2>

    <form action="servlets.profileServlets.SeeBookings" method="get">
        <input type="submit" name="action" value="My bookings">
    </form>
</body>
</html>
