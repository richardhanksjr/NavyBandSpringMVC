<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Band Portal</title>
</head>
<body class = "full">
	${band.email}
	<form action="logOut.do" method="GET">
		<input type="submit" value="Log Out">
	</form>
	<form action="viewMilitaryRequests.do" action="GET">
		<input type="submit" value="Get All Military Requests">
	</form>
	<form action="viewCivilianRequests.do" action="GET">
		<input type="submit" value="Get All Civilian Requests">
	</form>
	<table>
		<tr>
			<th>Year</th>
			<th>Month</th>
			<th>Day</th>
			<th>Booking Status</th>
			<th>Event Description</th>
		</tr>
		<c:forEach var="requests" items="${civilianRequests }">
			<tr>
				<td>${requests.dateOfEvent.year}</td>
				<td>${requests.dateOfEvent.month }</td>
				<td>${requests.dateOfEvent.day}</td>
				<td>${requests.bookingStatus.status}</td>
				<td>${requests.description }</td>
				<td>
					<form action="editCivilianRequest.do" method="POST">
						<input type="hidden" name="requestId" value="${requests.id}">
						<input type="hidden" name="pointOfContactId" " value="${user.id }">
						<input type="hidden" name="pointOfContactId" " value="${user.id }">
						<!--  Send the place this method is originating from so response can be directed appropriately-->
						<input type="hidden" name="origin" value="band.jsp"> <input
							type="submit" name="submit" value="Edit Request Information">
					</form>
				</td>
				<td>
					<form action="setCivilianBookingStatus.do" method="GET">
						<input type="hidden" name="bookingId" value="${requests.id }">
						<input type="hidden" name="userEmail" value="${user.email }">
						<input type="hidden" name="pointOfContactId" " value="${user.id }">
						<!--  Send the place this method is originating from so response can be directed appropriately-->
						<!-- <input type="hidden" name="origin" value="band"> -->
						<div>Set Booking Status of Event</div>
						<select name="statusId">
							<option value="1">Unconfirmed</option>
							<option value="2">Confirmed</option>
							<option value="3">Cancelled</option>
						</select> <input type="submit" value="Set Status">
					</form>
				</td>
				<td>
					<form action="assignUnitCivilian.do" method="POST">
						
						<input type="hidden" name="requestId" value="${requests.id }">
						<select name="unitId">
							<option value="1">Brass Quintet</option>

						</select> <input type="submit" value="Assign Unit">
					</form>

				</td>
			</tr>
		</c:forEach>
	</table>
	<table>
		<tr>
			<th>Year</th>
			<th>Month</th>
			<th>Day</th>
			<th>Booking Status</th>
			<th>Event Description</th>
		</tr>

		<c:forEach var="requests" items="${militaryRequests}">
			<tr>
			<c:if test = "${not empty requests }">
				<td>${requests.dateOfEvent.year}</td>
				<td>${requests.dateOfEvent.month }</td>
				<td>${requests.dateOfEvent.day}</td>
				<td>${requests.bookingStatus.status}</td>
				<td>${requests.description }</td>
			</c:if>
				<td>
					<form action="editMilitaryRequest.do" method="POST">
						<input type="hidden" name="requestId" value="${requests.id}">
						<input type="hidden" name="pointOfContactId" value="${user.id }">
						<input type="hidden" name="origin" value="band.jsp"> <input
							type="submit" name="submit" value="Edit Request Information">
					</form>
				</td>
				<td>
					<form action="setMilitaryBookingStatus.do" method="GET">
						<input type="hidden" name="bookingId" value="${requests.id }">
						<input type="hidden" name="userEmail" value="${user.email }">
						<input type="hidden" name="origin" value="band.jsp"> <select
							name="statusId">
							<option value="1">Unconfirmed</option>
							<option value="2">Confirmed</option>
							<option value="3">Cancelled</option>

						</select> <input type="submit" value="Set Status">
						<!-- <input
							type="submit" value="Cancel Event"> -->
					</form>
					</td>
					<td>
					<form action="displayGigSheetMilitary.do" method="GET">
						<input type="hidden" name="requestId" value="${requests.id }">
						<input type="submit" value="Get Gig Sheet">
					</form>
					</td>
					<td>
					<form action="assignUnitMilitary.do" method="POST">
						
						<input type="hidden" name="requestId" value="${requests.id }">
						<select name="unitId">
							<option value="1">Brass Quintet</option>

						</select> <input type="submit" value="Assign Unit">
					</form>
				</td>
		</c:forEach>
		<%-- </c:if> --%>
	</table>
</body>
</html>