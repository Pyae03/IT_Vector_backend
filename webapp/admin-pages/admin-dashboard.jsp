<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.UserDao" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin-pages/admin-dashboard.style.css">
		<link
			rel="stylesheet"
			href="${pageContext.request.contextPath}/admin-pages/admin-nav-bar.style.css" />

		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1.0" />
		<title>Admin</title>
	</head>
	<body>
		<nav>	
			<div class="logo">
				<img
					src="image/logo.svg"
					alt="" />
				<h2>IT_Vector</h2>
			</div>

			<div class="view-options">
				<a href="admin-dashboard.jsp" class="active">DashBoard</a>
				<a
					href="admin-courses.html"
					class=""
					>Courses</a
				>
				<a href="admin-event.html">Event</a>
				<a
					href="admin-database.html"
					class=""
					>User Info</a
				>
			</div>

			<div class="user-tool">
				<div class="search-bar">
					<input
						type="search"
						name="search" />
					<input
						type="submit"
						value="search" />
				</div>
				<div class="setting">
					<img
						src="image/setting.svg"
						alt="" />
				</div>
				<div class="user-profile">
					<img
						src="image/user-default.svg"
						alt="" />
				</div>
			</div>
		</nav>

		<!-- all view options -->
		<div class="dashboard">
			<div class="dashboard-info">
				<div class="dashboard-title">
					<img src="image/dashboard.svg" alt="dashboard">
					<h2>Dashboard</h2>
	
				</div>
				<div class="card-group">
					<div class="card">
						<img src="image/student.svg" alt="">
						<h3>Total Students</h3>
						<h3><%= UserDao.getTotalStudents() %></h3>
					</div>
					<div class="card">
						<img src="image/teacher.svg" alt="">
						<h3>Total Teachers</h3>
						<h3><%= UserDao.getTotalTeachers() %></h3>
					</div>
					<div class="card">
						<img src="image/course.svg" alt="">
						<h3>Total Courses</h3>
						<h3>1</h3>
					</div>
					<div class="card">
						<img src="image/user-default.svg" alt="">
						<a href="admin-database.html"><h3>Total Users</h3></a>
						<h3><%= UserDao.getTotalUsers() %></h3>
					</div>
					<div class="card">
						<img src="image/certificate.svg" alt="">
						<h3>Total Certificate Earned</h3>
						<h3>3</h3>
					</div>
				</div>


				<div class="btn-features">
					<button class="btn-create-new-user">Create New User</button>
				</div>


				<div class="user-creation-form hidden">
					<form action="UserController">
						<label for="username">Username</label>
						<input type="text" id="username" name="username" required>
						
						<label for="password">Password</label>
						<input type="text" id="password" name="password" required>
						
						<label for="email">Email</label>
						<input type="email" name="email" required>

						<label for="gender" >Gender</label>
						<select name="gender" id="gender" required>
							<option value="Male">Male</option>
							<option value="Female">Female</option>
						</select>

						<label for="date-of-birth">Date of Birth</label>
						<input type="date" id="date-of-birth" name="date-of-birth">

						<label for="course">Select Role</label>
						<select name="role" id="role">
							<option value="Student">Student</option>
							<option value="Teacher">Teacher</option>
							<option value="Admin">Admin</option>
						</select>

						<div class="btn-confirm">
							<input type="submit">
							<input type="reset">
						</div>
					</form>
				</div>
			</div>




		</div>
		</div>

		<script src="${pageContext.request.contextPath}/admin-pages/admin-dashboard.script.js"></script>
		
	</body>
</html>
