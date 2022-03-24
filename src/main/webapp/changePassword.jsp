<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<div align="center">
<h1>Input Username and New Password</h1>
<form method="post" action="changePassword">
<table>
<tr>
	<td>Username:</td>
	<td> <input type= "text" name="username"></td>
</tr>
<tr>
	<td>Password:</td>
	<td> <input type= "password" name="password"></td>
</tr>
<tr>
	<td><div align="center"><input type= "submit" value="Submit"></div></td>
</tr>
</table>
</form>
<a href="login.jsp">Return to Login Page</a>
</div>
</body>
</html>