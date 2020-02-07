<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User list</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="generic-container">
	
		<!--  Message when user deleted -->
		
		<c:if test="${message=='success'}">
			<div class="alert alert-success">User successfully deleted.</div>
		</c:if>
		
		<c:if test="${message=='User can not be deleted'}">
			<div class="alert alert-danger">${message }<br> ${text }</div>
		</c:if>

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">User List </span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Username</th>
						<th>Email</th>
						<th>Role</th>
						<th>Requests</th>
						<th>Delete user</th>
					</tr>
				</thead>
				<tbody>


					<c:forEach var="user" items="${users}">
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

							<!-- Link to delete user -->
							<td><form:form
									action="${pageContext.request.contextPath}/admin/deleteUser/${user.id }"
									method="POST">

									<input type="submit" value="delete"
										class="btn btn-success custom-width" />
								</form:form></td>

						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>

	</div>

</body>
</html>