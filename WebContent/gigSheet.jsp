<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gig Sheet</title>
</head>
<body>
	<h1>Gig Sheet</h1>
	<%-- 	<c:forEach var = "info" items = "${militaryRequest }">
		${info}
	</c:forEach> --%>
	<table>
		<tr>
			<th>Type of Gig</th>
			
			<th>Street</th>
			<th>City</th>
			<th>State</th>
			<th>POC Name</th>
			<th>POC Phone Number</th>
			<th>POC Email</th>
			<th>Year</th>
			<th>Month</th>
			<th>Day</th>
			<th>Time</th>
			
		</tr>

		<tr>
			<td>${militaryRequest.type }</td>
			
			<td>${militaryRequest.address.street }</td>
			<td>${militaryRequest.address.city }</td>
			<td>${militaryRequest.address.state }</td>
			<td>${militaryRequest.pointOfContact.firstName }</td>
			<td>${militaryRequest.pointOfContact.email }</td>
			<td>${militaryRequest.dateOfEvent.year }</td>
			<td>${militaryRequest.dateOfEvent.month }</td>
			<td>${militaryRequest.dateOfEvent.day }</td>
			
		</tr>
	</table>
	<br><br><br><br>
	<table>
	<tr>
	<th>Moveable Date</th>
	<th>Assigned Unit</th>
			<th>Prescribed Uniform</th>
			<th>Description</th>
			<th>Booking Status</th>
			<th>After Action</th>
	</tr>
	<tr>
	<td>${militaryRequest.moveableDate }</td>
	<td>${militaryRequest.ensemble.name }</td>
	<td>${militaryRequest.prescribedUniform }</td>
			<td>${militaryRequest.description}</td>
			<td>${militaryRequest.bookingStatus.id }</td>
			<td>${militaryRequest.afterAction }</td>
	</tr>
	</table>
	<form action = "returnToBandPage.do" method = "GET">
		<input type = "submit" value = "Return to Band Page">
	</form>
</body>
</html>