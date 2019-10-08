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
<html>
<head>
    <title>My Profile</title>
</head>
<body>

    <a href="index.jsp"> <b>Home</b></a>   |
    <a href="servlets.userServlets.LogInServlet1">Log in</a> |
    <a href="servlets.profileServlets.ProfileServlet">My profile</a> |
    <a href="servlets.userServlets.LogOutServlet">Log out</a>
    <hr>

    <h2>Welcome to your profile, ${name}</h2>

    <form action="servlets.profileServlets.SeeBookings" method="get">

    </form>
</body>
</html>
