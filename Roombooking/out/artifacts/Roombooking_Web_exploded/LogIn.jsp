<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 24.09.2019
  Time: 15:04

  Dette er en fil som lager innloggingsmenyen. Når man trykker logg inn,
  brukes funksjonaliteten fra LogInServlet2. Hvis man trykker lag ny bruker,
  sendes man til CreateUser.jsp, som er en meny for å lage bruker.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<html>
<head>
    <title>Logg Inn</title>
</head>
<body>

<form action="servlets.userServlets.LogInServlet2" method ="post">

    <h1>Log in</h1>

    <span style="color: red; "> ${errorMessage} </span><br>
    E-mail: <input type="text" name="email"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" name="action" value="Log in"><br>


</form>

<p>If you would like to create a new account, please press the button:</p>
<form action="CreateUser.jsp">

    <input type="submit" name ="action"  value="Create new account">
</form>

</body>
</html>
