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
<html>
<head>
    <title>Logg Inn</title>
</head>
<body>

<form action="servlets.userServlets.LogInServlet2" method ="post">

    <h1>Logg inn</h1>
    Epost: <input type="text" name="email"><br>
    Passord: <input type="text" name="password"><br>
    <input type="submit" name="action" value="Logg inn">

</form>

<br> Hvis du ikke har en bruker, kan du opprette en bruker her:
<form action="CreateUser.jsp">

    <input type="submit" name ="action"  value="Lag ny bruker">
</form>

</body>
</html>
