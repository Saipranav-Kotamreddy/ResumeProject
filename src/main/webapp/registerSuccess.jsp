<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
	<h1>You Successfully Registered! Your ID Number is <%= request.getAttribute("id") %></h1>
		<a href="welcome.html">Go to Home Page</a>
</body>
</html>