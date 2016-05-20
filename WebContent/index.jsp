<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="startbootstrap-full-1.0.4/css/bootstrap.min.css"
	rel="stylesheet">
<link href="startbootstrap-full-1.0.4/css/full.css" rel="stylesheet">
<title>Navy Band Request</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Navy Band Southeast</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a
						href="http://www.cnic.navy.mil/regions/cnrse/about/navy_bands/navy_band_southeast.html">About</a>
					</li>
					<li><a
						href="https://www.facebook.com/NavyBandSoutheast/?fref=ts">Facebook</a>
					</li>
					<li><a href="mailto:nbse.ops.fct@navy.mil">Contact</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<div class="container">
		<div class="row text-center">
			<h1>New User Registration</h1>
		</div>
		<form role="form" action="newPointOfContact.do" method="POST">
			<div class="row text-center">

				<!-- <div>Rank</div> -->
				<input class="form-group" type="text" name="rank" value=""
					placeholder="Rank">
				<!-- <div>First Name</div> -->
				<input class="form-group" type="text" name="firstName" value=""
					placeholder="First Name">
				<!-- <div>Last Name</div> -->
				<input class="form-group" type="text" name="lastName" value=""
					placeholder="Last Name">
				<!-- 	<div>Title</div>
 -->
				<input class="form-group" type="text" name="title" value=""
					placeholder="Title">
				<!-- <div>Street</div> -->
				<input class="form-group" type="text" name="street" value=""
					placeholder="Street">
				<!-- <div>Apt/PO Box Number</div> -->
				<input class="form-group" type="text" name="aptPoNumber" value=""
					placeholder="Apt/PO Box Number">
				<!-- <div>City</div> -->
				<input class="form-group" type="text" name="city" value=""
					placeholder="City">
				<!-- <div class="row text-center"> -->
				<!-- <div class="col-md-6 text-right"> -->
				<!-- <div>State</div>
 -->
				<input class="form-group" type="text" name="state" value=""
					size="15" placeholder="State">
				<!-- </div>
					<div class="col-md-6 text-left"> -->
				<!-- <div>Zip</div> -->

				<input class="form-group" type="text" name="zip" value="" size="5"
					placeholder="Zip">

				<!-- 	</div>
					<div>Work Phone</div>
				</div> -->
				<input class="form-group" type="text" name="workPhone" value=""
					placeholder="Work Phone">
				<!-- <div>Cell Phone</div> -->
				<input class="form-group" type="text" name="cellPhone" value=""
					placeholder="Cell Phone">
				<!-- <div>Home Phone</div> -->
				<input class="form-group" type="text" name="homePhone" value=""
					placeholder="Home Phone">
				<!-- <div>Email</div> -->
				<input class="form-group" type="text" name="email" value=""
					placeholder="Email">
				<!-- <div>Fax</div> -->
				<input class="form-group" type="text" name="fax" value=""
					placeholder="Fax">
				<!-- <div>Password</div> -->
				<input class="form-group" type="password" name="password" value=""
					placeholder="Password">
				<!-- <div>Confirm Password</div> -->

				<input class="form-group" type="password" name="passwordConfirm"
					value="" placeholder="Confirm Password"> <br> <input
					type="submit" name="submit" value="Create Profile">
			</div>
		</form>
		<div class="row text-center">
			<h3>Sign In</h3>
		</div>
		<div class="row text-center">
			<form role = "form" action="userLogIn.do" method="POST">
				
				<input class="form-group" type="text" name="email" value="" placeholder = "Email"> <input
					class="form-group" type="password" name="password" value="" placeholder = "Password"> <br> <input
					type="submit" value="Submit">

			</form>
		</div>
	</div>
	<div class = "container">
	<div class = "row text-center">
		<img class = "banner" src = "images/circle.png">
	</div>
	</div>
	<script src="startbootstrap-full-1.0.4/js/jquery.js"></script>
	<script src="startbootstrap-full-1.0.4/js/bootstrap.min.js"></script>

</body>
</html>