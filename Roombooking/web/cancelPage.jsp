<%--
  Created by IntelliJ IDEA.
  User: 3005rekr
  Date: 2019-09-24
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Is this cancel culture?</title>
</head>

<p>Under finner du skjema for å endre navn</p>
<form action="servlets.CancelPage">
    Navn: <input type="text" name="newname">
    Navn som skal bli endret: <input type="text" name="oldname">
    <input type="submit" name="action" value="Endre navn">

</form>

<p>Under finner du skjema for å endre på e-mail</p>
<form action="servlets.CancelPage">
    E-mail: <input type="text" name="oldmail">
    E-mail som skal bli endret til: <input type="text" name="newmail">
    <input type="submit" name="action" value="Endre E-mail">
    <br>
</form>

<p>Under kan du finne skjema for å endre på telfonnummeret</p>
<form action="servlets.CancelPage">
    Telefonnummer: <input type="text" name="oldphone">
    Telefonnummer som skal bli endret: <input type="text" name="newphone">
    <input type="submit" name="action" value="Endre telefon">
</form>

<p>Skal du kansellere bestilling?</p>
<p> Skriv inn bookingnummer i formen under </p>
<form action="servlets.CancelPage">
    Bookingnummer: <input type="text" name="orderid">
    <input type="submit" name="action" value="kansellere">
</form>
</body>
</html>