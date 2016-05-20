<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Civilian Request</title>
</head>
<body>
	<div>Band ID</div>
	${band.id }
	<div>user ID</div>
	${user.id }
	<form action="newCivilianRequest.do" method="POST">
	<%-- 	<input type="hidden" name="addressId" value=""> 
		<input
			type="hidden" name="dateOfEventId" value="${request.dateOfEvent.id }">
		<input type="hidden" name="militaryRequestId" value="${request.id }"> --%>
		<input type = "hidden" name = "pointOfContactId" value = "${user.id}">
		<input type = "hidden" name = "pointOfContactEmail" value = "${user.email }">
		<div>Type of Event</div>
		<select name = "title">
			<option value = "publicConcert">Public Concert</option>
			<option value = "school">School</option>
			<option value = "parade">paradel</option>
			<option value = "other">Other</option>
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
		<div>Expected Attendance</div>
		<input type = "number" name = "attendance" value = "">
		<div>Will Other Military Units Be Attending?</div>
		<input type ="radio" name = "attending" value = "true">Yes<br>
		<input type = "radio" name = "attending" value = "false">No<br>
		<div>Describe Any Charges For Attendees of Event</div>
		<textarea rows="4" cols="50" name = "charges"></textarea>
		<div>Does This Event Have the Backing of the Government</div>
		<input type ="radio" name = "governmentBacking" value = "true">Yes<br>
		<input type = "radio" name = "governmentBacking" value = "false">No<br>
		<div>Is this an exclusive organization?</div>
		<input type ="radio" name = "exclusive" value = "true">Yes<br>
		<input type = "radio" name = "exclusive" value = "false">No<br>
		<div>Do you agree to fund a meal if required?</div>
		<input type ="radio" name = "meal" value = "true">Yes<br>
		<input type = "radio" name = "meal" value = "false">No<br>
		<div>Desciption of Event</div>
		<textarea rows="4" cols="50" name="description"></textarea>
		<br>
		<input type="submit" name="submit" value="Create New Civilian Event Request">
		<input type="submit" name="submit" value="Cancel">
	</form>
</body>
</html>