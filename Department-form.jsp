<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.planon.mvc.model.Department"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Department-form</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
.container {
	margin-top: 10%;
	display: flex;
	margin-left: 700px;
}

input:hover {
	border: 3px solid PaleVioletRed;
}

#deptcode, #deptname {
	border-radius: 25px;
}

input {
	height: 25px;
	width: 200px;
	border-radius: 15px;
	align: "left";
	paddfing: 15px;
}

.deptform {
	background-image: url('images/ofc3.jpg');
	background-repeat: repeat;
	font-family: Lucida Sans;
	border-radius: 25px;
	border: 2px solid DarkBlue;
	background-size: cover;
	padding: 25px;
	background-repeat:no-repeat;
}

input {
	height: 25px;
	width: 200px;
	border-radius: 15px;
	align: "left";
	padding: 15px;
}

h2 {
	color:Black;
	font-family: Comic Sans MS;
}
</style>
</head>
<body>
	<div class="container col-md-5">
		<div class="deptform">

			<%
			Department deptexist = (Department) request.getAttribute("existingdepartment");
			%>
			<%
			ArrayList<Department> department = (ArrayList<Department>) request.getAttribute("listDepartment");
			%>
			<%
			if (deptexist != null) {
			%>
			<form action="deptupdate" method="post">
				<%
				}
				%>
				<%
				if (deptexist == null) {
				%>
				<form action="deptinsert" method="post">
					<%
					}
					%>

					<caption>
						<h2>
							<%
							if (deptexist != null) {
							%>
							Edit Department
							<%
							}
							%>
							<%
							if (deptexist == null) {
							%>
							Add New Department
							<%
							}
							%>
						</h2>
					</caption>
					<%
					if (deptexist != null) {
					%>
					<input type="hidden" name="id"
						value="<%=deptexist.getDepartmentid()%>" />
					<%
					}
					%>
					<%
					if (deptexist != null) {
					%>
					<fieldset class="form-group">
						<label>Department Code</label> <input type="text"
							class="form-control" id="deptcode" name="dcode"
							value="<%=deptexist.getDepartmentCode()%>" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Department Name</label> <input type="text"
							class="form-control" id="deptname" name="dname"
							value="<%=deptexist.getDepartmentName()%>" required="required">
					</fieldset>
					<%
					}
					%>

					<%
					if (deptexist == null) {
					%>
					<fieldset class="form-group">
						<label>Department Code</label> <input type="text"
							class="form-control" id="deptcode" name="dcode"
							required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Department Name</label> <input type="text"
							class="form-control" name="dname" id="deptname"
							required="required">
					</fieldset>
					<%
					}
					%>
					<br>
					<button type="submit" class="btn btn-success">Save</button>
					<button onClick="location.href ='deptlist'" class="btn btn-warning">
						<i class="fa fa-close"></i>Cancel
					</button>
				</form>
		</div>
	</div>
	</div>
</body>
</html>