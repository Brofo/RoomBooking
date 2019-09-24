<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 24.09.2019
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logg Inn</title>
</head>
<body>

<form action="servlets.LoggInnServlet" method="post">

    <h1>Logg inn</h1>
    Epost: <input type="text" name="email"><br>
    Passord: <input type="text" name="password"><br>
    <input type="submit" name="action" value="Logg inn">



</form>

</body>
</html>
