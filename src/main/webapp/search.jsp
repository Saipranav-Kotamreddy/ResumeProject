<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Uploaded Resumes</title>
</head>
<body>
<div align="right">
<form method="post" action="logout">
	<button type="submit" name="logout"> logout</button>
</form>
</div>
<div align="center">
<h1>Search Uploaded Resumes</h1>
<p>Capped at 50 Results</p>
<form method="post" action="search">
<table>
<tr>
	<td>Search Criteria(leave blank to see all records):</td>
	<td> <input type= "text" name="criteria"></td>
</tr>
<tr>
	<td><div align="center"><input type= "submit" value="Search"></div></td>
</tr>
</table>
</form>
</div>

</body>
</html>