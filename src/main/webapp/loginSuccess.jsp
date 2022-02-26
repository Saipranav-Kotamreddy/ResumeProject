<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
<div align="right">
<form method="post" action="logout">
	<button type="submit" name="logout"> logout</button>
</form>
</div>
<h1>Successfully logged in as <%= request.getAttribute("username") %> of <%= request.getAttribute("company") %> </h1>

<a href="search.jsp">Review Applicants</a>
<a href="input.jsp">Input New Resumes</a>
</body>
</html>