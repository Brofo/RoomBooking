<%--
  Created by IntelliJ IDEA.
  User: 3005rekr
  Date: 2019-09-24
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Is this cancel culture?</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/CancelCulture.css" />
</head>

<body>
<br>
<!--
 Formen for funksjonen changeName
 -->
<form action="servlets.CancelPage">
    <fieldset>
    <legend>Navne Endring</legend>
    Navn:<br> <input type="text" name="newname"> <br>
    Navn som skal bli endret:<br> <input type="text" name="oldname"><br>
    KundeID:<br> <input type="text" name="customerID"><br>
    <br> <input type="submit" name="action" value="Endre navn">
    </fieldset>
</form>
<br>
<!--
 Formen for funksjonen changeMail
 -->
<form action="servlets.CancelPage">
    <fieldset>
        <legend>Endring på E-mail</legend>
    E-mail:<br> <input type="text" name="newmail"><br>
    E-mail som skal bli endret til:<br><input type="text" name="oldmail"><br>
    <br> <input type="submit" name="action" value="Endre E-mail">
    <br>
    </fieldset>
</form>
<br>
<!--
 Formen for funksjonen changePhone
 -->
<form action="servlets.CancelPage">
    <fieldset>
        <legend> Endring på telefonnummer</legend>
    Telefonnummer: <br><input type="text" name="newphone"><br>
    Telefonnummer som skal bli endret:<br> <input type="text" name="oldphone"><br>
    <br> <input type="submit" name="action" value="Endre telefon">
    </fieldset>
</form>
<br>
<!--
 Formen for funksjonen changeRoom
 -->
<form action="servlets.CancelPage">
    <fieldset>
        <legend>Endring på romtype</legend>
        Bookingnummer: <br><input type="text" name="orderid">
       <br> <select name="rom">
            <option value="sr">Singelrom</option>
            <option value="dr">Dobbeltrom</option>
            <option value="fr">Familierom</option>
            <option value="zj">Suite</option>
        </select>
        <input type="submit" name="action" value="rom">
    </fieldset>
</form>
<!--
 Formen for funksjonen changeCheckIn
 WIP
 -->
<form action="servlets.CancelPage">
    <fieldset>
    <legend>Endring på Checkin dato</legend>
        Bookingnummer: <input type="text" name="orderid"><br>
        Dag du vil endre til: <input type="date" name="date">
        <input type="submit" name="action" value="Endre Checkin">
    </fieldset>
</form>
<!--
 Formen for funksjonen changeCheckOut
 WIP
-->
<form action="servlets.CancelPage">
    <fieldset>
        <legend>Endring på Checkout dato</legend>
        Bookingnummer: <input type="text" name="orderid"><br>
        Dag du vil endre til: <input type="date" name="date">
        <input type="submit" name="action" value="Endre Checkout">
    </fieldset>
</form>

<!--
 Formen for funksjonen cancelOrder
 WIP
 -->
<form action="servlets.CancelPage">
    <fieldset>
        <legend> Kansellering av bestilling</legend>
    Bookingnummer: <br><input type="text" name="orderid"><br>Bookingnummer: <br>
       Enten skriv inn E-mailen din her: <input type="text" name="mail"><br>
        Eller skriv inn telefonnummere ditt her:<input type="text" name="phone"><br>
        <br> <input type="submit" name="action" value="kansellere">
</fieldset>
</form>
</body>
</html>
