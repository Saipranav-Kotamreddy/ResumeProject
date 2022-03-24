<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Resumes to Database</title>
</head>
<body>
<div align="right">
<form method="post" action="logout">
	<button type="submit" name="logout"> logout</button>
</form>
</div>
<div align="center">
<h1>Input Resume Page:</h1>
<p id="errorMessage"></p>
<script>
errorCheck();
function errorCheck(){
	if('<%=request.getAttribute("errorFlag")%>'=="true"){
		document.getElementById("errorMessage").innerHTML+="Error, Failed to insert Resume";
	}	
}
</script>
<form method="post" action="resume" enctype = "multipart/form-data">
<table>
<tr>
	<td>Applicant Name:</td>
	<td> <input type= "text" name="name"></td>
</tr>
<tr>
	<td>Phone Number:</td>
	<td> <input type= "text" name="phone_number"></td>
</tr>
<tr>
	<td>Email:</td>
	<td> <input type= "text" name="email"></td>
</tr>
<tr>
	<td>Resume PDF Link:</td>
	<td> <input type= "file" name="resume_link"></td>
</tr>
<tr>
	<td>Applicant Image:</td>
	<td> <input type= "file" name="picture"></td>
</tr>
<tr>
	<td>Skill List(comma separated, no spaces, Capitalize all skills):</td>
	<td> <input type= "text" name="skills"></td>
</tr>

<tr>
	<td><div align="center"><input type= "submit" value="Submit"></div></td>
</tr>
</table>
</form>
<p>Batch Upload using CSV</p>
<form method="post" action="batch" enctype = "multipart/form-data">
<table>
<tr>
	<td>CSV File:</td>
	<td> <input type= "file" name="batchUpload"></td>
</tr>
<tr>
	<td><div align="center"><input type= "submit" value="Submit"></div></td>
</tr>
</table>
</form>
</div>
</body>
</html>