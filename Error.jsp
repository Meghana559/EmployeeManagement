<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
h1, h2 {
	color: red;
	text-align: center
}
</style>
<title>Error</title>
</head>
<body>
	<%
	if (exception != null) {
	%>
	<h2><%=exception.getMessage()%></h2>
	<%
	}
	%>
	<h1>Oops! Error occured:)</h1>
	<%
	String errorMessage = (String) request.getAttribute("ShowErrorMessage");
	%>
	<h1><%=errorMessage%></h1>
</body>
</html>