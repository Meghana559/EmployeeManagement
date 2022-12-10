<%@page import="java.util.List"%>
<%@page import="com.planon.mvc.model.Department"%>
<%@page import="java.util.ArrayList,java.io.PrintWriter"%>
<%@page import="com.planon.mvc.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<style>
.container {
	margin-top: 2%;
	display: flex;
}

.empform {
	background-image: url('images/ofc3.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	font-family: Lucida Sans;
	border-radius: 25px;
	border: 2px solid DarkBlue;
	font-family: Lucida Sans;
	padding: 25px;
}

input:hover {
	border: 3px solid PaleVioletRed;
}

caption {
	text-align: center;
}

.side2, .side, .listtag, .listtag2 {
	display: flex;
	justify-content: space-between;
	margin-top: 2%;
}

select {
	height: 25px;
	width: 200px;
	border-radius: 15px;
}

input {
	height: 25px;
	width: 200px;
	border-radius: 15px;
	align: "left";
	padding: 15px;
}

#first {
	margin-left: -55px;
}

#emd {
	margin-left: -50px;
}

#doj {
	margin-left: -23px;
}

#dob {
	margin-left: -40px;
}

#dateb2 {
	margin-left: 1px;
}

#datej {
	margin-left: -22px;
}

#datebi {
	margin-left: -38px;
}

#last {
	margin-right: 50px;
}

#maleCheck {
	margin-top: 4px;
	margin-left: -55px;
	display: inline-block;
}

#femaleCheck {
	margin-top: 4px;
	margin-left: -25px;
	display: inline-block;
}

#mid, #fid {
	margin-top: 6px;
	display: inline-block;
	margin-left: -80px;
}

#lastalign {
	margin-left: -60px
}

#salaryalign {
	margin-left: -100px
}

label {
	outline: pink;
}

#fname {
	marginleft: -5px;
}

.listtag {
	padding-bottom: 7px;
}
</style>
<script>
	function validate() {
		var fname = document.emp_form.fname;
		var lname = document.emp_form.lname;
		var email = document.emp_form.email;
		var gender = document.emp_form.gender;

		if (fname.value.length <= 1) {
			alert("Name is required");
			fname.focus();
			return false;
		}
		if (lname.value.length <= 1) {
			alert("Last Name is required");
			lname.focus();
			return false;
		}
		if (email.value.length <= 11) {
			alert("Email Id is required");
			email.focus();
			return false;
		}

	}
</script>
<title>Employee-form</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<%
	Employee emp = (Employee) request.getAttribute("employee");
	%>
	<%
	ArrayList<Department> dept = (ArrayList<Department>) request.getAttribute("listDepartment");
	%>

	<div class="container col-md-5">
		<div class="empform">
			<br>
			<caption>
				<h2
					style="color: black; font-family: Comic Sans MS; text-align: center">
					<%
					if (emp != null) {
					%>
					Edit Employee
					<%
					}
					%>
					<%
					if (emp == null) {
					%>
					Add New Employee
					<%
					}
					%>
				</h2>
				<br>
			</caption>
			<%
			if (emp != null) {
			%>
			<form action="empupdate" method="post" name="emp_form"
				onSubmit="return validate()">
				<%
				}
				%>
				<%
				if (emp == null) {
				%>
				<form action="empinsert" method="post" name="emp_form"
					onSubmit="return validate()">
					<%
					}
					%>

					<%
					if (emp != null) {
					%>
					<input type="hidden" name="id" value="<%=emp.getEmployeeId()%>" />
					<%
					}
					%>
					<%
					if (emp != null) {
					%>
					<div class="listtag">
						<label for="eid">Employee Id </label> <input type="text"
							value="<%=emp.getEmployeeId()%> " name="id" id="eid"
							required="required"> <label for="eid" id="choose">Choose
							Department </label> <br> <select id="eid" name="dcode" required>
							<option value="none" selected disabled hidden>--Select
								an Option--</option>
							<%
							for (Department department : dept) {
								//System.out.println(department.getDepartmentid()+"  "+emp.getDepartment().getDepartmentid());
								if (department.getDepartmentid() != emp.getDepartment().getDepartmentid()) {
							%>
							<option value="<%=department.getDepartmentid()%>"><%=department.getDepartmentCode()%>--<%=department.getDepartmentName()%></option>

							<%
							} else {
							%>
							<option selected value="<%=department.getDepartmentid()%>"><%=department.getDepartmentCode()%>--<%=department.getDepartmentName()%></option>

							<%
							}
							}
							%>
						</select>
					</div>

					<div class="side">
						<label for="first" id="fname">First Name </label> <input
							type="text" value="<%=emp.getFirstName()%>" id="first"
							name="fname" required="required">
						<p id="lastalign">
							<label for="lname">Last Name</label>
						</p>
						<input type="text" value="<%=emp.getLastName()%>" name="lname"
							id="lname" required="required">
					</div>
					<div class="side2">
						<label for="emd">Email </label> <input type="text"
							value="<%=emp.getEmail()%>" id="emd" name="email"
							required="required">
						<p id="salaryalign">
							<label for="salary">Salary</label>
						</p>
						<input type="text" value="<%=emp.getSalary()%>" id="salary"
							name="salary" required="required">
					</div>
					<div class="listtag2">
						<label for="doj">Date <br>Of Joining
						</label> <input type="date" value="<%=emp.getDateOfJoining()%>" id="doj"
							name="doj" required="required"> <label for="dateb"
							id="dob">Date Of Birth</label> <input type="date"
							value="<%=emp.getDateOfBirth()%>" id="dateb" name="dob"
							required="required">
					</div>
					<fieldset>
						<label for="gender">Gender</label><label for="maleCheck"><input
							type="radio" id="maleCheck" value="Male" name="gender"
							<%if (emp.getGender().equals("Male")) {%> checked <%}%>>
							<p id="mid">Male</p></label> <label for="femaleCheck"> <input
							type="radio" id="femaleCheck" value="Female" name="gender"
							<%if (emp.getGender().equals("Female")) {%> checked <%}%>>
							<p id="fid">Female</p></label>
					</fieldset>
					<%
					}
					%>
					<%
					if (emp == null) {
					%>


					<div class="listtag">
						<label for="eid">Employee Id </label> &nbsp;<input type="text"
							id="eid" name="id" required="required"> &nbsp;<label
							for="choose" id="choose">Choose Department</label><br> <select
							name="dcode" id="choose" required>
							<option value="none" selected disabled hidden>--Select
								an Option--</option>
							<%
							for (Department department : dept) {
							%>
							<option value="<%=department.getDepartmentid()%>"><%=department.getDepartmentCode()%>--<%=department.getDepartmentName()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="side">
						<label for="first"">First Name</label><input type="text"
							name="fname" id="first" required="required">
						<p id="lastalign">
							<label for="lname">Last Name</label>
						</p>
						<input type="text" name="lname" id="lname" required="required">
					</div>
					<div class="side2">
						<label for="emd">Email </label><input type="text" name="email"
							id="emd" required="required">
						<p id="salaryalign">
							<label for="Empsalary">Salary</label>
						</p>
						<input type="text" name="salary" id="Empsalary"
							required="required">
					</div>
					<div class="listtag2">
						<label for="datej">Date <br>Of Joining
						</label><input type="date" name="doj" id="datej" required="required">
						<label for="dateb2" id="datebi">Date Of Birth </label><input
							type="date" name="dob" id="dateb2" required="required">
					</div>
					<fieldset>
						<label for="gender">Gender</label><label for="maleCheck"><input
							type="radio" value="Male" name="gender" id="maleCheck">
							<p id="mid">Male</p></label> <label for="femaleCheck"><input
							type="radio" value="Female" id="femaleCheck" name="gender">
							<p id="fid">Female</p> </label>
					</fieldset>

					<%
					}
					%>
					<div class="text-center">
						<br>
						<button type="submit" class="btn btn-success">Save</button>
						&nbsp;
						<button onClick="location.href ='emplist'" class="btn btn-warning">
							<i class="fa fa-close"></i>Cancel
						</button>
					</div>
				</form>
		</div>
	</div>


</body>
</html>