<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
	<jsp:useBean id="user" scope="session" class="com.fulllearn.model.User"></jsp:useBean>
	<%-- <h1>Welcome!</h1>
	<c:out value="${user.id}" />
	<br>
	<c:out value="${user.firstName}" />
	<br>
	<c:out value="${user.lastName}" /> --%>

	<div id="welcomeDiv" class="card"
		style="top: 50px; margin-left: 500px; height: 200px; width: 300px;">
		<div class="card-header"
			style="color: white; background-color: darkcyan;">Welcome !!!</div>
		<div class="card-block">
			<span id="usernameSpan"><c:out value="${user.firstName}"/> </span><img
				align='right' id="profilePic" src="${user.photoId}"
				style='border-radius: 50px' width='50px' height='50px'></img> <a
				align="right" class="link" style="text-align: right" href=/logout>Logout</a>
		</div>
	</div>


</body>
</html>