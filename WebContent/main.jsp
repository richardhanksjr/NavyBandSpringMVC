<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
</head>
<body>
	<h1>Navy Band Request Portal</h1>
	<form action="logOut.do" method="GET">
		<input type="submit" value="Log Out">
	</form>
	<div>Welcome, ${user.firstName}</div>
	<br>
	<br>
	<br>
	<br>
	<div>Contact the Band</div>
	<a href="mailto:${band.email }" target="_top">Contact the Band</a>
	<c:if test="${! empty(user.civilianRequests) }">
		<h2>Your Civilian Event Band Requests</h2>
		<table>
			<tr>
				<th>Year</th>
				<th>Month</th>
				<th>Day</th>
				<th>Booking Status</th>
				<th>Event Description</th>
			</tr>
			<c:forEach var="requests" items="${user.civilianRequests }">
				<tr>
					<td>${requests.dateOfEvent.year}</td>
					<td>${requests.dateOfEvent.month }</td>
					<td>${requests.dateOfEvent.day}</td>
					<td>${requests.bookingStatus.status}</td>
					<td>${requests.description }</td>
					<td>
						<form action="editCivilianRequest.do" method="POST">
							<input type = "hidden" name = "requestId" value = "${requests.id}">
							<input type="submit" name="submit"
								value="Edit Request Information">
						</form>

					</td>
			</c:forEach>
		</table>
	</c:if>
	<br>
	<br>
	<c:if test="${! empty(user.militaryRequests) }">
		<h2>Your Military Event Band Requests</h2>
		<table>
			<tr>
				<th>Year</th>
				<th>Month</th>
				<th>Day</th>
				<th>Booking Status</th>
				<th>Event Description</th>
			</tr>
			<c:forEach var="requests" items="${user.militaryRequests }">
				<tr>
					<td>${requests.dateOfEvent.year}</td>
					<td>${requests.dateOfEvent.month }</td>
					<td>${requests.dateOfEvent.day}</td>
					<td>${requests.bookingStatus.status}</td>
					<td>${requests.description }</td>
					<td>
						<form action="editMilitaryRequest.do" method="POST">
							<input type="submit" name="submit"
								value="Edit Request Information">
						</form>

					</td>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>