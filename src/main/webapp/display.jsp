<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results Page</title>
</head>
<body>
<p id="results"></p>
<p onclick="previousPage()">Previous Page of Results</p>
<p onclick="nextPage()">Next Page of Results</p>
<script>
var page=1;
var val = '<%=request.getAttribute("records")%>';
const list=JSON.parse(val);
var maxPages = Math.floor(Object.keys(list).length/5+.9);
display();
//page = page+1;
function display(){
	document.getElementById("results").innerHTML="";
	for(var i=(page-1)*5; i<Object.keys(list).length && i<page*5; i++){
			document.getElementById("results").innerHTML += "Name: " + list[i].NAME + "    Skillset: " + list[i].SKILLLIST + "<br />";
	}
	document.getElementById("results").innerHTML += "Page " + page + " of " + maxPages + "<br />";
}
</script>

<script>

function previousPage(){
	if(page!=1){
		page--;
		display();
	}
}

function nextPage(){
	if(page<(Object.keys(list).length/5)){
		page++;
		display();
	}
}
</script>

<a href="search.jsp">Return to search page</a>
</body>
</html>