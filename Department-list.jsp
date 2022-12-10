<%@page import="java.util.ArrayList"%>
<%@page import="com.planon.mvc.model.Department"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Department-list</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
	background-image: url("images/3.jpg");
	background-size: contain;
	height: auto;
	width: auto;
}

hr {
	border: 1px solid orange;
}

h2 {
	font-family: Chalkduster;
	color: DarkBlue;
	font-style: oblique;
	font-weight: bold;
}

input[type=text] {
	border: 2px solid PowderBlue;
	border-radius: 4px;
}

a:hover {
	text-decoration: none;
}

button {
	background: none;
	border: none;
	padding: 0;
	font-family: Georgia;
	color: darkmagenta;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	font-size: 20px;
}

button:focus {
	border: none;
	outline: none;
}

.btn1 {
	background-color: Azure;
	border: 2px solid PaleGreen;
	color: DarkBlue;
	padding: 10px 10px;
	font-size: 14px;
	cursor: pointer;
}

.table {
	border: 2px solid Black;
	font-family: Georgia, serif;
	color: Black;
	font-weight: 545;
	background-color: LightCyan;
}

tr {
	border-color: Black;
}

tr:hover {
	background-color: SkyBlue;
}

.input-icons i {
	position: absolute;
}

.input-icons {
	width: 100%;
	margin-bottom: 10px;
}

.icon {
	padding: 10px;
	min-width: 40px;
}

.input-field {
	width: 100%;
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>

	<div class="row">

		<div class="container">
			<h2 class="text-center">
				<strong>List of Departments</strong>
			</h2>
			<hr>
			<button onclick="location.href ='index.jsp'" class="btn1">
				<i class="fa fa-home"></i>HOME
			</button>
			<br> <br>
			<div class="col-md-3">
				<form action="deptquery" method="get">
					<div class="input-icons">
						<i class="fa fa-search icon fa-lg"></i> <input type="text"
							class="input-field" name="data" placeholder="Search here">
					</div>
				</form>
			</div>
			<div class="col-md-10 text-right">

				<a href="<%=request.getContextPath()%>/deptnew"
					class="btn btn-primary"><i class="fa fa-plus fa-lg"> Add
						New Department</i></a>
			</div>
			<br>
			<%
			List<Department> dept = (ArrayList<Department>) request.getAttribute("listDepartment");
			%>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><button onclick="location.href ='comparisions?id=1'"
								ondblclick="location.href ='comparisions?id=2'">DepartmentCode</button></th>
						<th><button onclick="location.href ='comparisions?id=3'"
								ondblclick="location.href ='comparisions?id=4'">DepartmentName</button></th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>

					<%
					for (Department department : dept) {
					%>

					<tr>
						<td><%=department.getDepartmentCode()%></td>
						<td><%=department.getDepartmentName()%></td>
						<td><a class="btn btn-info"
							href="deptedit?id=<%=department.getDepartmentid()%>"><i
								class="fa fa-pencil-square-o"> Edit</i></a>&nbsp; <a
							class="btn btn-danger"
							href="deptdelete?id=<%=department.getDepartmentid()%>"
							onclick="return confirm('Are you sure you want to delete this record?');"><i
								class="fa fa-trash"> Delete</i></a></td>
					</tr>
					<%
					}
					%>


				</tbody>

			</table>
		</div>
	</div>
</body>
</html>