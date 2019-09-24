<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 11.09.2019
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Rombukser</title>
</head>
<body>

<form action="servlets.Servlet">
  Navn: <input type="text" name="name">
  Email: <input type="text" name="email">
  Telefon: <input type="text" name="phone">
  <input type="submit" name ="action"  value="Bestille">
  <input type="submit" name ="action"  value="Vis alle bestillinger">
  <input type="submit" name="action" value="kansellere bestilling">

  <br>
</form>



</body>
</html>