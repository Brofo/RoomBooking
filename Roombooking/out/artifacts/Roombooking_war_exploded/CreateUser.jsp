<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 24.09.2019
  Time: 13:34

  Denne filen lager menyen for å opprette en bruker. Tar i bruk CreateUserServlet
  når man oppretter brukeren.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@include  file="link.html"%>
<html>
<head>
    <title>Creating user</title>
</head>
<body>

<form action="servlets.userServlets.CreateUserServlet" method="post">
Fill the empty slots in order to create a user.
    <br>
    First name:       <input type="text" name="firstname">
    Last name:     <input type="text" name="lastname">
    <br><br>
    Phone:    <input type="text" name="phone">
    E-mail:     <input type="text" name ="email">
    Password:    <input type="password" name ="password">
    <input type="submit" name="action" value="Opprett bruker">
</form>

</body>
</html>
