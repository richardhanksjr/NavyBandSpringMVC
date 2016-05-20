<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Civilian Event Request</title>
</head>
<body>
<h2>Civilian Event Information</h2>
<form action = "updateCivilianRequestInfo.do" method = "POST">
	<input type = "hidden" name = "addressId" value = "${request.address.id }">
	<input type = "hidden" name = "dateOfEventId" value = "${request.dateOfEvent.id }">
	<input type = "hidden" name = "militaryRequestId" value = "${request.id }">
	<input type = "hidden" name = "pointOfContactEmail" value = "${user.email }">
	<input type = "hidden" name = "origin" value = "${origin }">
	<div>Apt/PO Box Number</div>
	<input type = "text" name = "aptPoNumber" value = "${request.address.aptPoNumber }">
	<div>City</div>
	<input type = "text" name = "city" value = "${request.address.city }">
	<div>State</div>
	<input type = "text" name = "state" value = "${request.address.state }">
	<div>Zip</div>
	<input type = "text" name = "zip" value = "${request.address.zip }">
	<div>Year</div>
	<input type = "text" name = "year" value = "${request.dateOfEvent.year }">
	<div>Month</div>
	<input type = "text" name = "month" value = "${request.dateOfEvent.month }">
	<div>Day</div>
	<input type = "text" name = "day" value = "${request.dateOfEvent.day }">
	<div>Start Time</div>
	<input type = "text" name = "time" value = "${request.dateOfEvent.time }">
	<%-- <input type = "textarea" name = "description" value = "${request.description }"> --%>
	<textarea rows="4" cols="50" name = "description" >${request.description }</textarea>
	<input type = "submit" name = "submit" value = "Update Event Information">
	<input type = "submit" name = "submit" value = "Cancel">
</form>
${user.id }
</body>
</html>
