<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management Application</title>
<style>
a {
	text-decoration: none;
}

h2 {
	text-align: center;
	padding: 100px 0;
	font-style: Lucida Sans;
	color: Black;
}

h1 {
	margin-top: 20px;
	text-align: center;
	font-size: 4em;
}

.wordart {
	font-family: Lucida Handwriting;
	font-weight: bold;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.wordart.rainbow {
	transform: scale(1, 1.5);
	-webkit-transform: scale(1, 1.5);
	-moz-transform: scale(1, 1.5);
	-o-transform: scale(1, 1.5);
	-ms-transform: scale(1, 1.5);
	margin-top: 2%;
}

.wordart.rainbow .text {
	background: red;
	background: -webkit-linear-gradient(left, #b306a9, #ef2667, #f42e2c, #ffa509,
		#fdfc00, #55ac2f, #0b13fd, #a804af);
	background: -o-linear-gradient(left, #b306a9, #ef2667, #f42e2c, #ffa509, #fdfc00,
		#55ac2f, #0b13fd, #a804af);
	background: -moz-linear-gradient(left, #b306a9, #ef2667, #f42e2c, #ffa509, #fdfc00,
		#55ac2f, #0b13fd, #a804af);
	background: linear-gradient(to right, #b306a9, #ef2667, #f42e2c, #ffa509, #fdfc00,
		#55ac2f, #0b13fd, #a804af);
	background-clip: text;
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
	text-align: center;
}

.flip-container {
	perspective: 1000;
}

.flip-container:hover .flipper, .flip-container.hover .flipper {
	transform: rotateY(180deg);
}

.flip-container1 {
	perspective: 1000;
}

.flip-container1:hover .flipper1, .flip-container1.hover .flipper1 {
	transform: rotateY(180deg);
}

.flip-container, .front, .back {
	width: 300px;
	height: 300px;
	border-radius: 85px;
	margin-left: 170px;
}

.flip-container1, .front1, .back1 {
	width: 300px;
	height: 300px;
	border-radius: 85px;
	margin-top: 2%;
	margin-left: 370px;
}

.flipper {
	transition: 0.6s;
	transform-style: preserve-3d;
}

.flipper1 {
	transition: 0.6s;
	transform-style: preserve-3d;
}

.front, .back {
	backface-visibility: hidden;
	box-shadow: -5px 5px 5px #aaa;
}

.front1, .back1 {
	backface-visibility: hidden;
	box-shadow: -5px 5px 5px #aaa;
}

.empImage {
	width: 355px;
	height: 350px;
	border-radius: 75px;
}

.deptImage {
	width: 355px;
	height: 350px;
	border-radius: 75px;
}

.front {
	/*z-index: 2;*/
	background-image: url('images/employee.png');
	background-size: cover;
	background-repeat: no-repeat;
	transform: rotateY(0deg);
	padding: 5px;
	margin-top: 150px;
	margin-left: 100px;
	width: 349px;
	height: 350px;
	border-radius: 75px;
	border: 7px solid Indigo;
}

.back {
	transform: rotateY(180deg);
	background-image: url('images/bg1.PNG');
	background-size: cover;
	background-repeat: no-repeat;
	margin-top: -400px;
	margin-left: -150px;
	width: 350px;
	height: 350px;
	border-radius: 85px;
	border: 7px solid Indigo;
}

.front1 {
	/*z-index: 2;*/
	background-image: url('images/deptimg3.png');
	background-size: cover;
	background-repeat: no-repeat;
	transform: rotateY(0deg);
	width: 360px;
	height: 350px;
	margin-top: -350px;
	margin-left: 900px;
	border-radius: 75px;
	border: 7px solid Indigo;
	border-radius: 75px;
}

.back1 {
	transform: rotateY(180deg);
	background-image: url('images/deptimg4.png');
	background-size: cover;
	background-repeat: no-repeat;
	margin-top: -350px;
	margin-left: -1000px;
	width: 350px;
	height: 350px;
	border-radius: 85px;
	border: 7px solid Indigo;
}

body {
	background-image: url('images/1.webp');
	background-position: center;
	/*background-size: cover;
	background-repeat: no-repeat;*/
	background-position: top right 5px;
	width: auto;
	height: auto;
}
</style>
</head>
<body>
	<div class="wordart rainbow">
		<h1>
			<span class="text"> Employee Management Application</span>
		</h1>
	</div>
	<div class="flip-container">
		<div class="flipper">
			<div class="front">
				<img src="images/employee.png" alt="emp" class="empImage">
			</div>
			<div class="back">
				<h2>
					<a href="emplist" style="color: Purple">Click here to View
						Employees</a>
				</h2>
			</div>
		</div>
	</div>

	<div class="flip-container1">
		<div class="flipper1">
			<div class="front1">
				<img src="images/deptimg3.png" alt="dept" class="deptImage">
			</div>
			<div class="back1">
				<h2>
					<a href="deptlist" style="color: MidnightBlue">Click here to
						View Departments</a>
				</h2>
			</div>
		</div>
	</div>


</body>
</html>