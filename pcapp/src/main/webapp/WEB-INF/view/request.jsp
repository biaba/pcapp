<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Request </span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Username</th>
						<th>Reason</th>
						<th>Date</th>
						<th>Required parts</th>
						<th>Status</th>

					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${userName}</td>
						<td>${request.text}</td>
						<td>${request.dateSubmitted}</td>
						<td><c:forEach var="part" items="${request.computerParts}">
									${part.partName}
									<br>
							</c:forEach></td>
						<td>${request.requestStatus}</td>

					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>