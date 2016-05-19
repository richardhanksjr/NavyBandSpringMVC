<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Military Request</title>
</head>
<body>
	<div>Band ID</div>
	${band.id }
	<div>User ID</div>
	${user.id }
	<form action="newMilitaryRequest.do" method="POST">
	<%-- 	<input type="hidden" name="addressId" value=""> 
		<input
			type="hidden" name="dateOfEventId" value="${request.dateOfEvent.id }">
		<input type="hidden" name="militaryRequestId" value="${request.id }"> --%>
		<input type = "hidden" name = "pointOfContactId" value = "${user.id}">
		<div>Type of Event</div>
		<select name = "type">
			<option value = "changeOfCommand">Change Of Command</option>
			<option value = "retirement">Retirement</option>
			<option value = "shipArrival">Ship Arrival</option>
			<option value = "funeralHonors">Funeral Honors</option>
		</select>
		<div>Apt/PO Box Number</div>
		<input type="text" name="aptPoNumber"
			value="">
		<div>Street</div>
		<input type = "text" name = "street" value = "">
		<div>City</div>
		<input type="text" name="city" value="">
		<div>State</div>
		<input type="text" name="state" value="">
		<div>Zip</div>
		<input type="text" name="zip" value="">
		<div>Year</div>
		<input type="text" name="year" value="">
		<div>Month</div>
		<input type="text" name="month" value="">
		<div>Day</div>
		<input type="text" name="day" value="">
		<div>Start Time</div>
		<input type="text" name="time" value="">
		<div>Is the date/time of this event moveable?</div>
		<input type ="radio" name = "moveable" value = "true">Yes<br>
		<input type = "radio" name = "moveable" value = "false">No<br>
		<%-- <input type = "textarea" name = "description" value = "${request.description }"> --%>
		<div>Desciption of Event</div>
		<textarea rows="4" cols="50" name="description"></textarea>
		<br>
		<input type="submit" name="submit" value="Create New Military Event Request">
		<input type="submit" name="submit" value="Cancel">
	</form>
</body>
</html>