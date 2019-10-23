<%--
  Created by IntelliJ IDEA.
  User: Kristian
  Date: 20.10.2019
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/CancelCulture.css" />
</head>
<body>
<form action="servlets.cancelServlets.CancelPageUser">
    <p> Navn registret på bruker: ${name}</p>


</form>
</body>
</html>
