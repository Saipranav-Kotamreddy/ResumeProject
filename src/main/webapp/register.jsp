<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
<div align="center">
<h1>Registration Page</h1>
<p id="errorMessage"></p>
<script>
errorCheck();
function errorCheck(){
	if('<%=request.getAttribute("errorFlag")%>'=="true"){
		document.getElementById("errorMessage").innerHTML+="Error, Failed to register";
	}	
}
</script>
<form method="post" action="register">
<table>
<tr>
<td>Email Address:</td>
<td><input type="text" name="email"></td>
</tr>
<tr>
<td>First Name:</td>
<td><input type="text" name="first_name"></td>
</tr>
<tr>
<td>Last Name:</td>
<td><input type="text" name="last_name"></td>
</tr>
<tr>
<td>Username:</td>
<td><input type="text" name="username"></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="text" name="password"></td>
</tr>
<!--  <tr>
<td>Company Name:</td>
<td><input type="text" name="company"></td>
</tr>-->
<tr>
<td><div align="center"><input type= "submit" value="Submit"></div></td>
</tr>
</table>
</form>
</div>
</body>
</html>