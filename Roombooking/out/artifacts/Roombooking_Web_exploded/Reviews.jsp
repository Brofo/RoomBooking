<%--
  Created by IntelliJ IDEA.
  User: Erlend Skaar
  Date: 28.10.2019
  Time: 19.53
  To change this template use File | Settings | File Templates.

  Dette er siden som skal kunne lagre og vise anmeldelser fra kunder. Kanskje mulighet for stjerner også.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@include  file="link.html"%>
<html>
<head>
    <title>Reviews</title>
</head>
<body>
<form action="servlets.servlets.Review" method="post">
    <h1>Reviews</h1>

    If you have stayed at our hotel, and wish to share your experiences and thoughts, please make a review.
    <br>
    Your review:<br>
    <textarea name="review" rows="10" cols="50" maxlength = "250" placeholder="Write here...(limit:250chr)"></textarea><br>
    <input type="submit" name="createReview" value="Post review">
</form>

<form action="servlets.servlets.Review" method="get">
    <h3>Other peoples reviews</h3>
    <br>
    <input type="submit" name="getReviews" value="See reviews">
</form>
</form>
</body>
</html>
