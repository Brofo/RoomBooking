<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 24.09.2019
  Time: 13:34

  Denne filen lager menyen for å opprette en bruker. Tar i bruk CreateUserServlet
  når man oppretter brukeren.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<html>
<head>
    <title>Creating user</title>
</head>
<body>

    <a href="index.jsp"> <b>Home</b></a>   |
    <a href="servlets.userServlets.LogInServlet1">Log in</a> |
    <a href="servlets.profileServlets.ProfileServlet">My profile</a> |
    <a href="servlets.userServlets.LogOutServlet">Log out</a>
    <hr>

<form action="servlets.userServlets.CreateUserServlet" method="post">
Fyll inn feltene for å opprette en bruker.
    <br>
    Navn:       <input type="text" name="name">
    Telefon:    <input type="text" name="phone">
    <br><br>Innlogging:</br>
    E-post:     <input type="text" name ="email">
    Passord:    <input type="password" name ="password">
    <input type="submit" name="action" value="Opprett bruker">
</form>

</body>
</html>
