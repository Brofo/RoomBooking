<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@include  file="link.html"%>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Checkout Form</title>
  <link rel="stylesheet" href="css/style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<input type="hidden" name="availableRoomID" value="${availableRoomID}">
<input type="hidden" name="roomType" value="${roomType}">
<input type="hidden" name="checkInDate" value="${checkInDate}">
<input type="hidden" name="checkOutDate" value="${checkOutDate}">

<input type="hidden" name="firstname" value="${firstname}">
<input type="hidden" name="lastname" value="${lastname}">
<input type="hidden" name="email" value="${email}">
<input type="hidden" name="phone" value="${phone}">
<input type="hidden" name="preferences" value="${preferences}">
<input type="hidden" name="paymentType" value="${paymentType}">

  <form class="checkout" action="servlets.bookingServlets.BookingServlet2" method="post">
    <div class="checkout-header">
      <h1 class="checkout-title">
        Checkout
        <span class="checkout-price">$10</span>
      </h1>
    </div>
    <p>
      <input type="text" class="checkout-input checkout-name" placeholder="Card holder name" autofocus>
      <input type="text" class="checkout-input checkout-exp" placeholder="MM">
      <input type="text" class="checkout-input checkout-exp" placeholder="YY">
    </p>
    <p>
      <input type="text" class="checkout-input checkout-card" placeholder="4111 1111 1111 1111">
      <input type="text" class="checkout-input checkout-cvc" placeholder="CVC">
    </p>
    <p>
      <input type="submit" value="Purchase" class="checkout-btn">
    </p>
  </form>

  <div class="about">
    <p class="about-links">
      <a href="http://www.cssflow.com/snippets/checkout-form" target="_parent">View Article</a>
      <a href="http://www.cssflow.com/snippets/checkout-form.zip" target="_parent">Download</a>
    </p>
    <p class="about-author">
      &copy; 2013 <a href="http://thibaut.me" target="_blank">Thibaut Courouble</a> -
      <a href="http://www.cssflow.com/mit-license" target="_blank">MIT License</a><br>
      Original PSD by <a href="http://dribbble.com/shots/960330-Checkout-Form-PSD" target="_blank">Umar Irshad</a>
    </p>
  </div>
</body>
</html>