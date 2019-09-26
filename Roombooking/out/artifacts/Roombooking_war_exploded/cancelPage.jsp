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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/CancelCulture.css" />
</head>

<br>
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
<form action="servlets.CancelPage">
    <fieldset>
        <legend> Endring på telefonnummer</legend>
    Telefonnummer: <br><input type="text" name="newphone"><br>
    Telefonnummer som skal bli endret:<br> <input type="text" name="oldphone"><br>
    <br> <input type="submit" name="action" value="Endre telefon">
    </fieldset>
</form>
<br>
<form action="servlets.CancelPage">
    <fieldset>
        <legend>Endring på romtype</legend>
        Bookingnummer: <br><input type="text" name="orderid">
       <br> <select>
            <option name="rom" value="sr">Singelrom</option>
            <option name="rom" value="dr">Dobbeltrom</option>
            <option name="rom" value="fr">Familierom</option>
            <option name="rom" value="zj">Suite</option>
        </select>
        <input type="submit" name="action" value="rom">
    </fieldset>
</form>
<form action="servlets.CancelPage">
    <fieldset>
        <legend> Kansellering av bestilling</legend>
    Bookingnummer: <br><input type="text" name="orderid"><br>
    <br> <input type="submit" name="action" value="kansellere">
</fieldset>
</form>
</body>
</html>
