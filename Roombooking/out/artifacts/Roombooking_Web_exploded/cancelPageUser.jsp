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
    <p>Navn: ${firstname} ${lastname}</p>
    <div id="navnForm">
        <form action="servlets.cancelServlets.CancelPageUser">
            Fornavn: <input type="text" name="firstname" >
            Etternavn:<input type="text" name="lastname" ><br>

            <input type="submit" name="action" value="Trykk her for å endre navn">
            <input onclick="closeForm('navnForm','navnKnapp')" type="button" value="avbryt">
        </form>
    </div>
    <div id="navnKnapp" >
    <button onclick="openForm('navnForm','navnKnapp')">Endre navn?</button>
    </div>

    <p>E-mail: ${email}</p>
    <div id="emailForm">
        <form action="servlets.cancelServlets.CancelPageUser">
            E-mail: <input type="text" name="email">
            <br>
            <input type="submit" name="action" value="Trykk her for å endre email">
            <input onclick="closeForm('emailForm','emailKnapp')" type="button" value="avbryt">

        </form>
    </div>
    <div id="emailKnapp" >
        <button onclick="openForm('emailForm','emailKnapp')">Endre email?</button>
    </div>
    <p>Telefon: ${phone}</p>
    <div id="telefonForm">
        <form action="servlets.cancelServlets.CancelPageUser">
            E-mail: <input type="text" name="phone">
            <br>
            <input type="submit" name="action" value="Trykk her for å endre telefon?">
            <input onclick="closeForm('telefonForm','telefonKnapp')" type="button" value="avbryt">

        </form>
    </div>
    <div id="telefonKnapp" >
        <button onclick="openForm('telefonForm','telefonKnapp')">Endre telefon?</button>
    </div>
    <br>
    <div>
        <button onclick="openForm('kansellereForm',null), closeForm('romForm',null),closeForm('datoForm',null)">Kansellere rom?</button>
        <button onclick="openForm('romForm',null), closeForm('kansellereForm',null),closeForm('datoForm',null)">Endre rom?</button>
        <button onclick="openForm('datoForm',null), closeForm('romForm',null),closeForm('kansellereForm',null)">Endre dato? </button>
    </div>
    <div id="kansellereForm">
        <form action="servlets.cancelServlets.CancelPageUser">
            Bookingnummer: <input type="text" name="orderid"><br>
            Enten skriv inn E-mailen din her: <input type="text" name="mail"><br>
            Eller skriv inn telefonnummere ditt her:<input type="text" name="phone"><br>
            <br> <input type="submit" name="action" value="Kansellere">
            <input onclick="closeForm('kansellereForm',null)" type="button" value="Avbryt">
        </form>
    </div>
    <div id="romForm">
        <form action="servlets.cancelServlets.CancelPageUser">
            Bookingnummer: <input type="text" name="orderid">
            <br> <select name="rom">
            <option value="sr">Singelrom</option>
            <option value="dr">Dobbeltrom</option>
            <option value="fr">Familierom</option>
            <option value="zj">Suite</option>
        </select>
            <input type="submit" name="action" value="rom">
            <input onclick="closeForm('romForm',null) " type="button" value="Avbryt">
        </form>
    </div>
    <div id="datoForm">
        <form action="servlets.cancelServlets.CancelPageUser">
            Bookingnummer: <input type="text" name="orderid"><br>
            Dag du vil endre til: <input type="date" name="date"><br>
            <input type="submit" name="action" value="Endre Checkin">
            <input onclick="closeForm('datoForm',null)" type="button" value="Avbryt">
        </form>
    </div>
    <script>
        function openForm (navn, navnID) {
            document.getElementById(navn).style.display = "block";
            if(navnID != null){
            document.getElementById(navnID).style.display ="none";
            }
        }

        function closeForm(navn,navnID) {
            document.getElementById(navn).style.display = "none";
            if(navnID != null){
                document.getElementById(navnID).style.display ="block";
            }
        }

    </script>
</body>
</html>
