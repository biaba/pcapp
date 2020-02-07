<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<style>
.error {
	color: red
}
</style>
<title>Computer part request form</title>
</head>
<body>
<div class="generic-container">
	<h1>Computer part request form</h1>
	

	<form:form action="${pageContext.request.contextPath}/app/submitRequest" modelAttribute="request">

Describe the problem(*):<form:textarea path="text" rows = "6" cols = "60"/>
		<form:errors path="text" cssClass="error" />
		<br>
		<br>

		<!-- Opting for required parts. Can be multiple options // DropDown from Properties file -->
		
Computer part/s required:
(press Ctrl for multiple options)
		<form:select path="cpList" multiple="true">
			<form:options items="${computerParts}" />
		</form:select>
		<br>
		<div class="error">${cpError}</div>
		<br>
		<br>
		
		<form:hidden path="id"/>

	<!-- Option - submit now or save for later // Single radiobuttons -->

Your request :
		<br>
		Save for later<form:radiobutton path="requestStatus"
			value="NOT_SUBMITTED" />
		Submit today<form:radiobutton path="requestStatus"
			value="SUBMITTED" />
			<br>
			<form:errors path="requestStatus" cssClass="error" />
		<br>
		<br>
		<input type="submit" value="Submit" class="btn btn-success custom-width" />
	</form:form>
	
</div>
</body>
</html>