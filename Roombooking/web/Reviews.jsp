<%--
  Created by IntelliJ IDEA.
  User: Erlend Skaar
  Date: 28.10.2019
  Time: 19.53
  To change this template use File | Settings | File Templates.

  This page displays the average rating score and the 10 most recent reviews.
  It also gives a user the option to post a review.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@include  file="link.html"%>
<html>
<head>
    <title>Reviews</title>
</head>
<body>

<span style="color: firebrick;"><h1>Reviews</h1></span><br>

<div>

    Average review score:<br>
    <font size="+20"><span style="color: goldenrod;"> <b>${averageReview}</b></span></font><b>/10</b>
    <br><br><br>
</div>

<form action="servlets.reviewServlets.ReviewServletPost" method="get">

    <span style="color: firebrick;">Make a review</span><br>
    Display name: <input type="text" name="displayname" placeholder="Guest" >
    Rating score: <select id="score" name="rating">
        <option>Select...</option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <option>6</option>
        <option>7</option>
        <option>8</option>
        <option>9</option>
        <option>10</option>
    </select><br><br>
    <textarea name="review" rows="8" cols="50" maxlength = "250" placeholder="Please write your review here..."></textarea><br>
    <input type="submit" name="createReview" value="Post review"><br>
    <span style="color: red;"> <font size="-1"> ${errorMessage}<br></font></span>
</form>

<br>
<br>

<div>

    <text style="color:firebrick;"><h3>Recent reviews from our guests</h3></text>

    <font size="+2"> ${reviewdata2}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata1}</b></span> </font> <br> ${reviewdata0}<br><br><br>
    <font size="+2"> ${reviewdata5}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata4}</b></span> </font> <br> ${reviewdata3}<br><br><br>
    <font size="+2"> ${reviewdata8}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata7}</b></span> </font> <br> ${reviewdata6}<br><br><br>
    <font size="+2"> ${reviewdata11}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata10}</b></span> </font> <br> ${reviewdata9}<br><br><br>
    <font size="+2"> ${reviewdata14}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata13}</b></span> </font> <br> ${reviewdata12}<br><br><br>
    <font size="+2"> ${reviewdata17}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata16}</b></span> </font> <br> ${reviewdata15}<br><br><br>
    <font size="+2"> ${reviewdata20}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata19}</b></span> </font> <br> ${reviewdata18}<br><br><br>
    <font size="+2"> ${reviewdata23}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata22}</b></span> </font> <br> ${reviewdata21}<br><br><br>
    <font size="+2"> ${reviewdata26}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata25}</b></span> </font> <br> ${reviewdata24}<br><br><br>
    <font size="+2"> ${reviewdata29}  </font> <font size ="+10"> <span style="color: goldenrod;"><b>${reviewdata28}</b></span> </font> <br> ${reviewdata27}<br><br><br>

    </div>
</body>
</html>