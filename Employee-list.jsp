<%@page import="java.awt.PageAttributes"%>
<%@page import="java.util.Optional"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="com.planon.mvc.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MEmployee Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="bootstrap.min.css" rel="stylesheet" />
<style>
body {
	background-image: url("images/4.png");
	width:auto;
	/*background-repeat:repeat;
background-size: contain;*/
}

hr {
	border: 1px solid green;
}

h2 {
	font-family: Snell Roundhan;
	color: Indigo;
	font-style: oblique;
}

input[type=text] {
	border: 2px solid pink;
	border-radius: 4px;
}

a:hover {
	text-decoration: none;
}

button {
	background: none;
	border: none;
	padding: 0;
	font-family: Garamond;
	color: DarkBlue;
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
	background-color: WhiteSmoke;
	border: 2px solid LightSalmon;
	color: Navy;
	padding: 10px 10px;
	font-size: 14px;
	cursor: pointer;
	border: 2px solid LightSalmon;
}

.table {
	border: 2px solid Black;
	font-family: Georgia, serif;
	color: Black;
	font-weight: 545;
	margin-top: 1%;
	background-color: LavenderBlush;
	width: 80%;
}

tr {
	border-color: #96D4D4;
}

tr:hover {
	background-color: #F67280;
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

#input-field {
	width: 100%;
	padding: 5px;
	text-align: center;
	margin-left: -4%
}

input[type=submit] {
	margin-top: -4.5%;
	border: 2px solid red;
	font-size: 18px;
	font-family: Malgun Gothic;
}

#add {
	margin-right: -25%;
}

#checkpic {
	width: 25px;
	height: 25px;
}
</style>

</head>
<body>

	<div class="row">
		<div class="container">
			<h2 class="text-center">
				<strong>List of Employees</strong>
			</h2>
			<hr>
			<button onclick="location.href ='index.jsp'" class="btn1">
				<i class="fa fa-home"></i>HOME
			</button>
			&nbsp;&nbsp;
			<button onClick="location.href ='QueryList.jsp'">EmpSalaryQueries</button>
			<br> <br>
			<div class="col-md-3">
				<form action="empquery" method="get">
					<div class="input-icons">
						<i class="fa fa-search icon fa-lg"></i> <input type="text"
							id="input-field" name="data" placeholder="Search here">
					</div>
				</form>
			</div>
			<div class="col-md-45 text-right">
				<a href="<%=request.getContextPath()%>/empnew" id="add"
					class="btn btn-primary"><i class="fa fa-user-plus fa-lg">
						Add New Employee</i></a><br> <br>
			</div>
			<%
			ArrayList<Employee> employees = (ArrayList<Employee>) request.getAttribute("listEmployee");
			%>
			<form action="empMultiple" method="post">
				<input type="submit" value="Delete Selected" class="btn btn-danger"
					onclick="return confirm('Are you sure you want to delete these records ?');" />
				<table class="table table-bordered" id="tab">
					<thead>
						<tr>
							<th><input type="checkbox" name="mainCheck" id="checkall"
								onchange="checkAll(this)" /><img src="images/checklist.png"
								id="checkpic" /></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=1'"
									ondblclick="location.href ='empcomparisions?id=2'">Employee
									ID</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=3'"
									ondblclick="location.href ='empcomparisions?id=4'">Department
									Code</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=5'"
									ondblclick="location.href ='empcomparisions?id=6'">Department
									Name</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=7'"
									ondblclick="location.href ='empcomparisions?id=8'">First
									Name</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=9'"
									ondblclick="location.href ='empcomparisions?id=10'">Last
									Name</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=11'"
									ondblclick="location.href ='empcomparisions?id=12'">Email</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=13'"
									ondblclick="location.href ='empcomparisions?id=14'">Gender</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=15'"
									ondblclick="location.href ='empcomparisions?id=16'">DateOfBirth</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=17'"
									ondblclick="location.href ='empcomparisions?id=18'">DateOfJoininig</button></th>
							<th><button type="button"
									onclick="location.href ='empcomparisions?id=19'"
									ondblclick="location.href ='empcomparisions?id=20'">Salary</button></th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Employee emp : employees) {
						%>
						<tr>
							<td><input type="checkbox" onchange="checkChange()"
								name="deleteMultipleEmp" value=<%=emp.getEmployeeId()%>
								class="checkitem"></td>
							<td><%=emp.getEmployeeId()%></td>
							<td><%=emp.getDepartment().getDepartmentCode()%>
							<td><%=emp.getDepartment().getDepartmentName()%>
							<td><%=emp.getFirstName()%></td>
							<td><%=emp.getLastName()%></td>
							<td><%=emp.getEmail()%></td>
							<td><%=emp.getGender()%></td>
							<td><%=emp.getDateOfBirth()%></td>
							<td><%=emp.getDateOfJoining()%></td>
							<td><%=emp.getSalary()%></td>
							<td><a class="btn btn-info"
								href="empedit?id=<%=emp.getEmployeeId()%>"><i
									class="fa fa-pencil-square-o"> Edit</i></a></td>
							<td><a class="btn btn-danger"
								onclick="return confirm('Are you sure you want to delete this record ?');"
								href="empdelete?id=<%=emp.getEmployeeId()%>"><i
									class="fa fa-trash"> Delete</i></a></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<script src="script.js"></script>
				<script>
					function checkChange() {
						var totalCheckbox = document
								.querySelectorAll("input[type='checkbox']").length;
						var totalChecked = document
								.querySelectorAll("input[type='checkbox']:checked").length;
						var totalCheckedMain = document
								.querySelector("input[name='mainCheck']");

						if (totalCheckbox == totalChecked) {
							document.getElementsByName("mainCheck")[0].checked = true;
						} else if (totalCheckbox - 1 == totalChecked
								&& totalCheckedMain.checked == false) {
							document.getElementsByName("mainCheck")[0].checked = true;

						} else {
							document.getElementsByName("mainCheck")[0].checked = false;
						}
					}
				</script>
			</form>
			<div class="my">
				<a href="#1" class="btn">1</a> <a href="#2" class="btn">2</a> <a
					href="#3" class="btn">3</a> <a href="#4" class="btn">4</a>
			</div>
			<div id="1">
				<h2 class="text-center">
					<strong>List of High Paid Employees</strong>
				</h2>
				<hr>
				<%
				ArrayList<Employee> highPaidEmployees = (ArrayList<Employee>) request.getAttribute("highestPaidList");
				%>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><button onclick="location.href ='comparisions?id=1'"
									ondblclick="location.href ='comparisions?id=2'">Employee
									ID</button></th>
							<th><button onclick="location.href ='comparisions?id=3'"
									ondblclick="location.href ='comparisions?id=4'">Department
									Code</button></th>
							<th><button onclick="location.href ='comparisions?id=5'"
									ondblclick="location.href ='comparisions?id=6'">Department
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=7'"
									ondblclick="location.href ='comparisions?id=8'">First
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=9'"
									ondblclick="location.href ='comparisions?id=10'">Last
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=11'"
									ondblclick="location.href ='comparisions?id=12'">Email</button></th>
							<th><button onclick="location.href ='comparisions?id=13'"
									ondblclick="location.href ='comparisions?id=14'">Gender</button></th>
							<th><button onclick="location.href ='comparisions?id=15'"
									ondblclick="location.href ='comparisions?id=16'">DateOfBirth</button></th>
							<th><button onclick="location.href ='comparisions?id=17'"
									ondblclick="location.href ='comparisions?id=18'">DateOfJoininig</button></th>
							<th><button onclick="location.href ='comparisions?id=19'"
									ondblclick="location.href ='comparisions?id=20'">Salary</button></th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Employee emp2 : highPaidEmployees) {
						%>
						<tr>
							<td><%=emp2.getEmployeeId()%></td>
							<td><%=emp2.getDepartment().getDepartmentCode()%>
							<td><%=emp2.getDepartment().getDepartmentName()%>
							<td><%=emp2.getFirstName()%></td>
							<td><%=emp2.getLastName()%></td>
							<td><%=emp2.getEmail()%></td>
							<td><%=emp2.getGender()%></td>
							<td><%=emp2.getDateOfBirth()%></td>
							<td><%=emp2.getDateOfJoining()%></td>
							<td><%=emp2.getSalary()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div id="2">
				<h2 class="text-center">
					<strong>List of Minimum Paid Employees</strong>
				</h2>
				<hr>
				<%
				ArrayList<Employee> minPaidEmployees = (ArrayList<Employee>) request.getAttribute("minPaidList");
				%>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><button onclick="location.href ='comparisions?id=1'"
									ondblclick="location.href ='comparisions?id=2'">Employee
									ID</button></th>
							<th><button onclick="location.href ='comparisions?id=3'"
									ondblclick="location.href ='comparisions?id=4'">Department
									Code</button></th>
							<th><button onclick="location.href ='comparisions?id=5'"
									ondblclick="location.href ='comparisions?id=6'">Department
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=7'"
									ondblclick="location.href ='comparisions?id=8'">First
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=9'"
									ondblclick="location.href ='comparisions?id=10'">Last
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=11'"
									ondblclick="location.href ='comparisions?id=12'">Email</button></th>
							<th><button onclick="location.href ='comparisions?id=13'"
									ondblclick="location.href ='comparisions?id=14'">Gender</button></th>
							<th><button onclick="location.href ='comparisions?id=15'"
									ondblclick="location.href ='comparisions?id=16'">DateOfBirth</button></th>
							<th><button onclick="location.href ='comparisions?id=17'"
									ondblclick="location.href ='comparisions?id=18'">DateOfJoininig</button></th>
							<th><button onclick="location.href ='comparisions?id=19'"
									ondblclick="location.href ='comparisions?id=20'">Salary</button></th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Employee emp3 : minPaidEmployees) {
						%>
						<tr>
							<td><%=emp3.getEmployeeId()%></td>
							<td><%=emp3.getDepartment().getDepartmentCode()%>
							<td><%=emp3.getDepartment().getDepartmentName()%>
							<td><%=emp3.getFirstName()%></td>
							<td><%=emp3.getLastName()%></td>
							<td><%=emp3.getEmail()%></td>
							<td><%=emp3.getGender()%></td>
							<td><%=emp3.getDateOfBirth()%></td>
							<td><%=emp3.getDateOfJoining()%></td>
							<td><%=emp3.getSalary()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div id="3">
				<h2 class="text-center">
					<strong>List of High Paid Employees for each Department</strong>
				</h2>
				<hr>
				<%
				ArrayList<Employee> maxpaidListforeachDept = (ArrayList<Employee>) request.getAttribute("maxpaidListforeachDept");
				%>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><button onclick="location.href ='comparisions?id=1'"
									ondblclick="location.href ='comparisions?id=2'">Employee
									ID</button></th>
							<th><button onclick="location.href ='comparisions?id=3'"
									ondblclick="location.href ='comparisions?id=4'">Department
									Code</button></th>
							<th><button onclick="location.href ='comparisions?id=5'"
									ondblclick="location.href ='comparisions?id=6'">Department
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=7'"
									ondblclick="location.href ='comparisions?id=8'">First
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=9'"
									ondblclick="location.href ='comparisions?id=10'">Last
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=11'"
									ondblclick="location.href ='comparisions?id=12'">Email</button></th>
							<th><button onclick="location.href ='comparisions?id=13'"
									ondblclick="location.href ='comparisions?id=14'">Gender</button></th>
							<th><button onclick="location.href ='comparisions?id=15'"
									ondblclick="location.href ='comparisions?id=16'">DateOfBirth</button></th>
							<th><button onclick="location.href ='comparisions?id=17'"
									ondblclick="location.href ='comparisions?id=18'">DateOfJoininig</button></th>
							<th><button onclick="location.href ='comparisions?id=19'"
									ondblclick="location.href ='comparisions?id=20'">Salary</button></th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Employee emp4 : maxpaidListforeachDept) {
						%>
						<tr>
							<td><%=emp4.getEmployeeId()%></td>
							<td><%=emp4.getDepartment().getDepartmentCode()%>
							<td><%=emp4.getDepartment().getDepartmentName()%>
							<td><%=emp4.getFirstName()%></td>
							<td><%=emp4.getLastName()%></td>
							<td><%=emp4.getEmail()%></td>
							<td><%=emp4.getGender()%></td>
							<td><%=emp4.getDateOfBirth()%></td>
							<td><%=emp4.getDateOfJoining()%></td>
							<td><%=emp4.getSalary()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div id="4">
				<h2 class="text-center">
					<strong>List of Minimum Paid Employees for each Department</strong>
				</h2>
				<hr>
				<%
				ArrayList<Employee> minpaidListforeachDept = (ArrayList<Employee>) request.getAttribute("minpaidListforeachDept");
				%>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><button onclick="location.href ='comparisions?id=1'"
									ondblclick="location.href ='comparisions?id=2'">Employee
									ID</button></th>
							<th><button onclick="location.href ='comparisions?id=3'"
									ondblclick="location.href ='comparisions?id=4'">Department
									Code</button></th>
							<th><button onclick="location.href ='comparisions?id=5'"
									ondblclick="location.href ='comparisions?id=6'">Department
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=7'"
									ondblclick="location.href ='comparisions?id=8'">First
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=9'"
									ondblclick="location.href ='comparisions?id=10'">Last
									Name</button></th>
							<th><button onclick="location.href ='comparisions?id=11'"
									ondblclick="location.href ='comparisions?id=12'">Email</button></th>
							<th><button onclick="location.href ='comparisions?id=13'"
									ondblclick="location.href ='comparisions?id=14'">Gender</button></th>
							<th><button onclick="location.href ='comparisions?id=15'"
									ondblclick="location.href ='comparisions?id=16'">DateOfBirth</button></th>
							<th><button onclick="location.href ='comparisions?id=17'"
									ondblclick="location.href ='comparisions?id=18'">DateOfJoininig</button></th>
							<th><button onclick="location.href ='comparisions?id=19'"
									ondblclick="location.href ='comparisions?id=20'">Salary</button></th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Employee emp5 : minpaidListforeachDept) {
						%>
						<tr>
							<td><%=emp5.getEmployeeId()%></td>
							<td><%=emp5.getDepartment().getDepartmentCode()%>
							<td><%=emp5.getDepartment().getDepartmentName()%>
							<td><%=emp5.getFirstName()%></td>
							<td><%=emp5.getLastName()%></td>
							<td><%=emp5.getEmail()%></td>
							<td><%=emp5.getGender()%></td>
							<td><%=emp5.getDateOfBirth()%></td>
							<td><%=emp5.getDateOfJoining()%></td>
							<td><%=emp5.getSalary()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
</html>