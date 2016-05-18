<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Navy Band Request</title>
</head>
<body>
	<!-- <form action = "test.do" method = "GET">
		<input type = "submit" name = "id" value = "1">
	</form>
	<form action = "getAllMilitaryRequests.do" method = "GET">
		<input type = "submit" name = "submit" value = "Get All Military Requests">
	</form>
	<form action = "getAllCivilianRequests.do" method = "GET">
		<input type = "submit" name = "submit" value = "Get All Civilian Requests">
	</form> -->
	<h1>New User Registration</h1>
	<form action="newPointOfContact.do" method="POST">
		<div>Rank</div>
		<input type="text" name="rank" value="">
		<div>First Name</div>
		<input type="text" name="firstName" value="">
		<div>Last Name</div>
		<input type="text" name="lastName" value="">
		<div>Title</div>
		<input type="text" name="title" value="">
		<div>Street</div>
		<input type="text" name="street" value="">
		<div>Apt/PO Box Number</div>
		<input type="text" name="aptPoNumber" value="">
		<div>City</div>
		<input type="text" name="city" value="">
		<div>State</div>
		<input type="text" name="state" value="" size="2">
		<div>Zip</div>
		<input type="text" name="zip" value="" size="5">
		<div>Work Phone</div>
		<input type="text" name="workPhone" value="">
		<div>Cell Phone</div>
		<input type="text" name="cellPhone" value="">
		<div>Home Phone</div>
		<input type="text" name="homePhone" value="">
		<div>Email</div>
		<input type="text" name="email" value="">
		<div>Fax</div>
		<input type="text" name="fax" value="">
		<div>Password</div>
		<input type="password" name="password" value="">
		<div>Confirm Password</div>
		<input type="password" name="passwordConfirm" value=""> <input
			type="submit" name="submit" value="Create Profile">
	</form>

	<h3>Sign In</h3>
	<form action="userLogIn.do" method="POST">
		<div>Email</div>
		<input type="text" name="email" value=""> <input
			type="password" name="password" value=""> <input
			type="submit" value="Submit">

	</form>
</body>
</html>