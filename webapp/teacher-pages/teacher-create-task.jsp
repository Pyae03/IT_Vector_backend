<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.CourseModule" %>
<%@ page import="models.CourseModuleMaterial" %>
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
						<button class="btn-create-quiz">Add Quiz</button>
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

						<form id="quizForm">
							<div class="quiz-question">
								<label for="title">Quiz Question</label>
								<input
									type="text"
									name="title"
									required />
							</div>

							<div class="quiz-options">
								<div class="quiz-option-group">
									<!-- <input
										type="radio"
										name="quiz-question"
										required />
									<input
										type="text"
										name="option"
										required />
									<select
										name="correct-answer"
										required>
										<option value="false">Incorrect</option>
										<option value="true">Correct</option>
									</select>
									<button class="btn-delete-option">Delete</button> -->
								</div>
							</div>

							<div class="add-option">
								<button class="btn-add-option">Add Option</button>
							</div>

							<input
								type="submit"
								value="Create Quiz" />
						</form>
					</div>

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
