<%@page import="com.planon.mvc.model.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EmployeeSalaryQuery</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
hr {
	border: 1px solid green;
}

h2 {
	font-family: Chalkduster;
	color: tomato;
	font-style: oblique;
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
</style>
</head>
<body>

	<div class="row">
		<div class="container">
			<h2 class="text-center">List of Employees</h2><hr>
			<button onclick="location.href ='index.jsp'" class="btn1">
				<i class="fa fa-home"></i>HOME
			</button>
			<br><br>
			<%
				ArrayList<Employee> employeeList= (ArrayList<Employee>) request.getAttribute("listEmployee");
				%>
				 <table class="table table-bordered">
					<thead>
						<tr>
							<th>Employee ID</th>
							<th>Department Code</th>
							<th>Department Name</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Gender</th>
							<th>DateOfBirth</th>
							<th>DateOfJoininig</th>
							<th>Salary</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Employee emp2 : employeeList) {
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
	</div>
</body>
</html>