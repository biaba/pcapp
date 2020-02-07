<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<title>PC request page</title>
</head>

<body>
<div class="generic-container">
	<h2>PC request Page</h2>
	
	<br><h4> Hello ${userName }</h4>
	<p>Please, fill in the form to make a pc part request!</p>

	<!-- Link for all authorized users -->
	<form:form action="${pageContext.request.contextPath}/app" method="GET">
	
			<input type="submit" value="new request" class="btn btn-danger custom-width"/>
	
	</form:form>
		
<!-- Link for manager -->
	<sec:authorize access="hasRole('MANAGER')">
		<form:form action="${pageContext.request.contextPath}/leader" method="GET">
	
			<input type="submit" value="view requests" class="btn btn-success custom-width"/>
	
		</form:form>
	</sec:authorize>

<!-- Links for admin -->
	<sec:authorize access="hasRole('ADMIN')">
		<form:form action="${pageContext.request.contextPath}/admin/users" method="GET">

			<input type="submit" value="view users" class="btn btn-success custom-width"/>

		</form:form>
		<form:form action="${pageContext.request.contextPath}/admin/requests" method="GET">
	
			<input type="submit" value="requests" class="btn btn-success custom-width"/>
	
		</form:form>
	</sec:authorize>
	
	
<!-- Edit option if form saved for later submission-->
	<c:if test="${requestStatus=='NOT_SUBMITTED'}">
		<form:form action="${pageContext.request.contextPath}/app/edit" method="GET">

			<input type="submit" value="edit request" class="btn btn-success custom-width" />

		</form:form>
	</c:if>
	
<!-- Log out option -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">

		<input type="submit" value="logout" class="btn btn-success custom-width"/>

	</form:form>

<!-- Option to login with another username -->
	<br> You are not ${userName }?
	<a href="${pageContext.request.contextPath}/loginForm">Login with another user</a>
	<br>
	</div>
</body>

</html>