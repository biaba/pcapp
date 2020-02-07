<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<meta charset="ISO-8859-1">
<title>PC request page</title>
</head>
<body>
	<div class="generic-container">
		<h1>PC request page</h1>
		<c:if test="${param.logout != null}">

			<div class="alert alert-success col-xs-offset-1 col-xs-10">You
				have logged out</div>

		</c:if>

		<form:form action="${pageContext.request.contextPath}/loginForm"
			method="GET">

			<input type="submit" value="Log in"
				class="btn btn-success custom-width" />

		</form:form>
	</div>
</body>
</html>