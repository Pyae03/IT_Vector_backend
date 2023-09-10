<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.Quiz" %>
<%@ page import="models.Assignment" %>
<%@ page import="models.CourseModule" %>
<%@ page import="models.Quiz" %>
<%@ page import="dao.AssignmentDao" %>
<%@ page import="dao.CourseModuleDao" %>
<%@ page import="dao.QuizDao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<script
			type="module"
			src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
		<script
			nomodule
			src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
		<link
			rel="stylesheet"
			href="${pageContext.request.contextPath}/student-pages/student-todo.style.css" />
		<link
			rel="stylesheet"
			href="${pageContext.request.contextPath}/student-pages/teacher-nav-bar.style.css" />
		<link
			rel="stylesheet"
			href="${pageContext.request.contextPath}/student-pages/user-side-bar.style.css" />

		<link
			rel="preconnect"
			href="https://fonts.googleapis.com" />
		<link
			rel="preconnect"
			href="https://fonts.gstatic.com"
			crossorigin />
		<link
			href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400&family=Nunito:wght@200;300;400&display=swap"
			rel="stylesheet" />
		<link
			rel="preconnect"
			href="https://fonts.googleapis.com" />
		<link
			rel="preconnect"
			href="https://fonts.gstatic.com"
			crossorigin />
		<link
			href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400&display=swap"
			rel="stylesheet" />

		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1.0" />
		<title>IT_Vector | Student</title>
	</head>
	<body>
		<div class="container">
			<nav>
				<div class="logo">
					<img
						src="image/logo.svg"
						alt="" />
					<h2>IT_Vector</h2>
				</div>

				<div class="notification">
					<img
						src="image/notification.svg"
						alt="" />
				</div>
				<div class="user-profile">
					<img
						src="image/user-default.svg"
						alt="" />
				</div>
			</nav>
			<div class="wrapper">
				<div class="side-bar">
					<!-- this is side bar -->
       
		            <div class="outer-1">
		                <a href="student-dashboard.html">
		                    <div class="element">
		                        <img
		                            src="image/dashboard.svg"
		                            alt="" />
		                        <p>Dashboard</p>
		                    </div>
		                </a>
		            </div>
		            <div class="outer-2">
		                <a href="student-course.html">
		                    <div class="element">
		                        <img
		                            src="image/course.svg"
		                            alt="" />
		                        <p>Courses</p>
		                    </div>
		                </a>
		            </div>
		            <div class="outer-3 active selected">
		                <a href="student-todo.jsp">
		                    <div class="element">
		                        <img
		                            src="image/todo.svg"
		                            alt="" />
		                        <p>To do</p>
		                    </div>
		                </a>
		            </div>
		            <div class="outer-4">
		                <div class="element">
		                    <img
		                        src="images/setting.svg"
		                        alt="" />
		                    <p>Setting</p>
		                </div>
		            </div>
				</div>

				<main>
					<div class="todo-list">
						<div class="todo-assignment-group">
							<h3>Assignment List</h3>
							
							 <%
							        List<Assignment> assignments = AssignmentDao.getAllAssignments();
							        if (assignments != null) {
							            for (Assignment assignment : assignments) {
							            	CourseModule courseModule = CourseModuleDao.getModuleByID(assignment.getModuleID());											            	
						    %>

							<a href="http://localhost:8080/IT_Vector_Ver1/AssignmentPageServlet?assignmentID=<%= assignment.getAssignmentID() %>"><div class="todo-assignment">
								<span><ion-icon name="bookmark-outline"></ion-icon></span>
								<p><%= assignment.getAssignmentName() %></p>
								<p><%= courseModule.getModuleTitle() %></p>
							</div>
							</a>
							<%
						            	
						            }
						        }
						    %>
						</div>
						<div class="todo-quiz-group">
							<h3>Quiz List</h3>
							
							<%
									List<Quiz> quizes = QuizDao.getAllQuizzes();
		                        if (quizes != null) {
		                            for (Quiz quiz : quizes) {
		                            	CourseModule courseModule = CourseModuleDao.getModuleByID(quiz.getModuleID());	     	
	                    	%>
							<a href="http://localhost:8080/IT_Vector_Ver1/QuizServlet?quizID=<%= quiz.getQuizID() %>">
								<div class="todo-quiz">
									<span><ion-icon name="help-outline"></ion-icon></span>
									<p><%= quiz.getQuizName() %></p>
								<p><%= courseModule.getModuleTitle() %></p>
								</div>
							</a>
							<%
						            	
						            }
						        }
						    %>
						</div>
					</div>
				</main>
			</div>
		</div>

		<!-- <script src="teacher-create-task.script.js"></script> -->
		<script src="teacher-create-task.script.js"></script>
	</body>
</html>
