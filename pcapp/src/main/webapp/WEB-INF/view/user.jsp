<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">User Profile </span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Username</th>
						<th>Email</th>
						<th>Role</th>
						<th>Requests</th>

					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td><c:forEach var="role" items="${user.roles}">
									${role.roleName}
									<br>
							</c:forEach></td>
						<td><c:forEach var="request" items="${user.requestList}">
									${request.dateSubmitted}
									<br>
							</c:forEach></td>

					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>