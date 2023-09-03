<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.CourseModule" %>
<%@ page import="models.CourseModuleMaterial" %>
<%@ page import="models.Category" %>
<%@ page import="models.Quiz" %>
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
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/teacher-pages/teacher-create-task.style.css">
		<link
			rel="stylesheet"
			href="${pageContext.request.contextPath}/teacher-nav-bar.style.css" />
		<link
			rel="stylesheet"
			href="user-side-bar.style.css" />

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
					<div class="outer-2">
						<a href="teacher-chat.html">
							<div class="element">
								<img
									src="image/course.svg"
									alt="" />
								<p>Chat</p>
							</div>
						</a>
					</div>

					<div class="outer-4 active">
						<a href="teacher-create-task.html">
							<div class="element">
								<img
									src="image/upload-file.svg"
									alt="" />
								<p>Create Tasks</p>
							</div>
						</a>
					</div>
					<div class="outer-5">
						<a href="#">
							<div class="element">
								<img
									src="image/setting.svg"
									alt="" />
								<p>Setting</p>
							</div>
						</a>
					</div>
				</div>

				<main>
					<div class="create-tasks">
						<button class="btn-create-assignment">Create Assignment</button>
						<button class="btn-create-quiz">Add Quiz Question</button>
						<button class="btn-quiz">Create Quiz </button>
						<!-- <button class="btn-create-homework">Create Homework</button> -->
					</div>

					<div class="assignment-wrapper">
						<nav>
							<span class="close-button"
								><ion-icon name="close-outline"></ion-icon
							></span>
							<h2>Assignment</h2>
						</nav>

						<form action="AssignmentFileUploadServlet" method="post" enctype="multipart/form-data">
							<div class="assignment-title">
								<label for="title">Title</label>
								<input
									type="text"
									name="assignmentName" />
							</div>

							<div class="description">
								<label for="instructions">Instructions (optional) </label>
								<textarea
									name="description"
									id=""
									cols="30"
									rows="10"></textarea>
							</div>

							<div class="file-upload">
								<label for="attach">Attach</label>
								<input type="file" name="file" />
							</div>

							<div class="assignment-side-bar">
								<h3>Course Title: <span>Web Application Development</span></h3>
								<label for="end-date">End Date</label>
								<input
									type="date"
									name="end-date" />
									
									
									
				<select name="get-module" id="">
				<!-- ASSIGNMENT -->
								<%
                        List<CourseModule> modules = (List<CourseModule>) request.getAttribute("modules");
                        if (modules != null) {
                            for (CourseModule module : modules) {
                    	%>
									<option value="<%= module.getModuleID() %>"><%= module.getModuleTitle() %></option>
									
						 <%
                            }
                        }
                    	%>
				</select>

								<input
									type="submit"
									value="Submit" />
							</div>
						</form>
					</div>
					<div class="quiz-wrapper">
						<nav>
							<span class="close-button-quiz"
								><ion-icon name="close-outline"></ion-icon
							></span>
							<h2>Quiz</h2>
						</nav>

						<form id="quizForm" action="AddQuizQuestionServlet" method="post" >
							<div class="quiz-question">
								<label for="title">Quiz Question</label>
								<input
									type="text"
									name="questionText"
									required />
							</div>

							<div class="quiz-options">
								<div class="quiz-option-group">
									<!-- radio button group here-->
								</div>
							</div>

							<div class="add-option">
								<button class="btn-add-option">Add Option</button>
							</div>

							<select name="categoryName" id="">	
								
							<%
								List<Category> categories = (List<Category>) session.getAttribute("categories");
	                        if (modules != null) {
	                            for (Category category : categories) {
	                    	%>
										<option  value="<%= category.getCategoryName() %>"><%= category.getCategoryName() %></option>
										
							 <%
                            }
                        }
                    	%>
                    	
                    	</select>
                    	
                    	<!-- QUIZ QUESTION -->
                    	
                    	<select name="quiz" id="">		
							<%
								List<Quiz> quizes = (List<Quiz>) session.getAttribute("quizes");
	                        if (modules != null) {
	                            for (Quiz quiz : quizes) {
	                    	%>
										<option  value="<%= quiz.getQuizID() %>"><%= quiz.getQuizName() %></option>
										
							 <%
                            }
                        }
                    	%>
                    	</select>
                    	
							<input
								type="submit"
								value="Create Quiz" />
						</form>
					</div>


				<!-- QUIZ -->
					<div class="main-quiz-wrapper">
						<nav>
							<span class="close-button-main-quiz"
								><ion-icon name="close-outline"></ion-icon
							></span>
							<h2>Quiz</h2>
						</nav>

						<form action="AddQuizServlet" method="post">
						
							<!--<input type="text" name="courseID" style="display: none"> -->
							
							<div class="assignment-title">
								<label for="title">Quiz Name</label>
								<input
									type="text"
									name="quizName" />
							</div>

							<div class="description">
								<label for="instructions">Instructions (optional) </label>
								<textarea
									name="description"
									id=""
									cols="30"
									rows="10"></textarea>
							</div>

							<div class="assignment-side-bar">
								<h3>Course Title: <span>Web Application Development</span></h3>
								<label for="end-date">Time Limit</label>
								<input
									type="number"
									name="timeLimit" />

						<select name="get-module" id="">
						<!-- Quiz -->
								<%
	                        List<CourseModule> modules1 = (List<CourseModule>) request.getAttribute("modules");
	                        if (modules1 != null) {
	                            for (CourseModule module1 : modules1) {
	                    	%>
										<option value="<%= module1.getModuleID() %>"><%= module1.getModuleTitle() %></option>
										
							 <%
	                            }
	                        }
	                    	%>
						</select>

								<input
									type="submit"
									value="Submit" />
							</div>
						</form>
					</div>


				<!-- TODO LIST -->
					<div class="todo-list">
						<div class="todo-assignment-group">
							<h3>Assignment List</h3>
							<div class="todo-assignment">
								<span><ion-icon name="bookmark-outline"></ion-icon></span>
								<p>assignment title</p>
							</div>
							<div class="todo-assignment">
								<span><ion-icon name="bookmark-outline"></ion-icon></span>
								<p>assignment title</p>
							</div>
						</div>
						<div class="todo-quiz-group">
							<h3>Quiz List</h3>
							<div class="todo-quiz">
								<span><ion-icon name="help-outline"></ion-icon></span>
								<p>quiz title</p>
							</div>
							<div class="todo-quiz">
								<span><ion-icon name="help-outline"></ion-icon></span>
								<p>quiz title</p>
							</div>
						</div>
					</div>
				</main>
			</div>
		</div>

		<!-- <script src="teacher-create-task.script.js"></script> -->
		<script src="${pageContext.request.contextPath}/teacher-pages/teacher-create-task.script.js"></script>
	</body>
</html>
