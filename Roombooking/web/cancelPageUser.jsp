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
    <title>Account Information</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/CancelCulture.css" />

</head>
<body>
    <p>Name: ${firstname} ${lastname}</p>
    <div id="navnForm" >
        <form action="servlets.cancelServlets.CancelPageUser" method="post" >
            Firstname: <input type="text" name="firstname" >
            Lastname:<input type="text" name="lastname" ><br>

            <input class="profile" type="submit" name="action" value="Press here to change name">
            <input class="profile" onclick="closeForm('navnForm','navnKnapp')" type="button" value="cancel">
        </form>
    </div>

    <div id="navnKnapp" >
    <button class="profile" onclick="openForm('navnForm','navnKnapp')">Change name</button>
    </div>

    <p>E-mail: ${email}</p>
    <div id="emailForm">
        <form action="servlets.cancelServlets.CancelPageUser"  method="post" >
            E-mail: <input type="text" name="email">
            <br>
            <input class="profile" type="submit" name="action" value="Press here to change email">
            <input class="profile" onclick="closeForm('emailForm','emailKnapp')" type="button" value="cancel">

        </form>
    </div>

    <div id="emailKnapp" >
        <button class="profile"  onclick="openForm('emailForm','emailKnapp')">Change e-mail</button>
    </div>
    <p>Phone: ${phone}</p>
    <div id="telefonForm">
        <form action="servlets.cancelServlets.CancelPageUser"  method="post"  >
            Phone: <input type="text" name="phone">
            <br>
            <input class="profile"  type="submit" name="action" value="Press here to change phone?">
            <input class="profile" onclick="closeForm('telefonForm','telefonKnapp')" type="button" value="cancel">

        </form>
    </div>

    <div id="telefonKnapp" >
        <button class="profile"  onclick="openForm('telefonForm','telefonKnapp')">Change phone</button>
    </div>
    <p>Password</p>
    <div id="passwordForm">
        <form action="servlets.cancelServlets.CancelPageUser"  method="post"  >
            Old Password: <input type="password" name="oldpassword" required><br>
            New Password: <input type="password" name="newpassword" required>
            <br>
            <input class="profile"  type="submit" name="action" value="Press here to change password?">
            <input class="profile" onclick="closeForm('passwordForm','passwordKnapp')" type="button" value="cancel">

        </form>
    </div>
    <div id="passwordKnapp">
        <button class="profile"  onclick="openForm('passwordForm','passwordKnapp')">Change password</button>
    </div>

    <br>
    <div>
        <button class="profile" onclick="openForm('kansellereForm',null), closeForm('romForm',null),closeForm('datoForm',null)">Cancel order</button>
        <button class="profile" onclick="openForm('romForm',null), closeForm('kansellereForm',null),closeForm('datoForm',null)">Change room</button>
        <button class="profile" onclick="openForm('datoForm',null), closeForm('romForm',null),closeForm('kansellereForm',null)">Change date </button>
    </div>

    <div id="kansellereForm">
        <form action="servlets.cancelServlets.CancelPageUser"  method="post" >
            <p>Write in your bookingnumber in the field down below to cancel your order</p>
            Bookingnumber: <input type="text" name="orderid"><br>

            <br> <input class="profile"  type="submit" name="action" value="Cancel booking">
            <input class="profile" onclick="closeForm('kansellereForm',null)" type="button" value="cancel">
        </form>
    </div>

    <div id="romForm" >
        <form action="servlets.cancelServlets.CancelPageUser"  method="post"  >
            Bookingnumber: <input type="text" name="orderid">
            <br> <select name="rom">
            <option value="sr">Singelrom</option>
            <option value="dr">Dobbeltrom</option>
            <option value="fr">Familierom</option>
            <option value="zj">Suite</option>
        </select>
            <input class="profile"  type="submit" name="action" value="Change room">
            <input class="profile" onclick="closeForm('romForm',null) " type="button" value="cancel">
        </form>
    </div>

    <div id="datoForm">
        <form action="servlets.cancelServlets.CancelPageUser" method="post">
            Bookingnumber: <input type="text" name="orderid"><br>
            Checkindate: <input type="date" name="checkin"><br>
            Checkoutdate: <input type="date" name="checkout"><br>
            <input class="profile" type="submit" name="action" value="Change date">
            <input class="profile" onclick="closeForm('datoForm',null)" type="button" value="cancel">
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
