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
<title>Request list</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="generic-container">
	
			<!--  Message when request deleted -->
		
		<c:if test="${message=='Status changed'}">
			<div class="alert alert-success">${message }</div>
		</c:if>
		
		<c:if test="${message=='Not allowed'}">
			<div class="alert alert-danger">${text }</div>
		</c:if>
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				 <span class="lead">List of Requests </span>
				 </div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Date submitted</th>
						<th>Status</th>
						<th>Reason</th>
						<th>Required parts</th>
						<!-- Option to view user - admin only -->
						<sec:authorize access="hasRole('ADMIN')">
							<th>User</th>
						</sec:authorize>
						<!-- Option to view request - manager only -->
						<sec:authorize access="hasRole('MANAGER')">
							<th>Request</th>
						</sec:authorize>
						<!-- Option to change request status to completed - manager only -->
						<sec:authorize access="hasRole('MANAGER')">
							<th>Status</th>
						</sec:authorize>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${requests}" var="request">
						<tr>
							<td>${request.dateSubmitted}</td>
							<td>${request.requestStatus}</td>
							<td>${request.text}</td>
							<td><c:forEach var="cPart" items="${request.computerParts}">
									${cPart.partName}
									<br>
								</c:forEach></td>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a
									href="<c:url value='/admin/user/${request.userId}' />"
									class="btn btn-success custom-width">view</a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('MANAGER')">
								<td><a
									href="<c:url value='/leader/showRequest/${request.id}' />"
									class="btn btn-success custom-width">view</a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('MANAGER')">
								<td><form:form
										action="${pageContext.request.contextPath}/leader/changeStatus/${request.id }"
										method="POST">

										<input type="submit" value="change"
											class="btn btn-success custom-width" />

									</form:form></td>
							</sec:authorize>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>

</body>
</html>