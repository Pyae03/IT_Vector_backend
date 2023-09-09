<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.CourseModule" %>
<%@ page import="models.CourseModuleMaterial" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Category" %>
<%@ page import="models.Quiz" %>
<%@ page import="models.Assignment" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<link
			rel="stylesheet"
			href="${pageContext.request.contextPath}/home-page/course-detail.style.css" />
		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1.0" />
		<title>Document</title>
	</head>
	<body>
		<div class="container">
			
			<div class="course-preview">
				<video controls>
					<source
						src="video/Attack on Titan S4 OST - -Splinter Wolf-.mp4"
						type="video/mp4" />
				</video>
			</div>

			<div class="course-description">
				<article>
					<h2>Web Application Development</h2>
					<p>
						Become a fullstack web developer (and get a job) by taking this one
						course, even if you've never coded before
					</p>
	
					<p><a href="#">Pyae Hein</a>, <a href="#">Hein Pyae</a></p>
					<div class="additional-info">
						<p>
							<span
								><ion-icon style="color: white;" name="time-outline"></ion-icon></span
							>Last update 1/ 2023
						</p>
						<p>
							<span
								><ion-icon style="color: white;" name="globe-outline"></ion-icon></span
							>English
						</p>
						
						<p style="border: 1px solid var(--light-blue);
						text-align: center;
						border-radius: var(--btn-border-radius);
		padding: 1em;
		background-color: var(--dark-blue);
		width: 10%;
		font-size: 1.1em;"><a href="http://localhost:8080/IT_Vector_Ver1/EnrollmentServlet">Enroll</a></p>
					</div>
				</article>
				<h2>$300</h2>
				<a
					href=""
					class="btn-encroll"
					>Encroll</a
				>
			</div>

			<div class="what-you-will-learn">
				<h3>What you'll learn</h3>
				<article>
					
					<p><ion-icon name="checkmark-outline"></ion-icon>Be able to build Any website you want.</p>
					<p><ion-icon name="checkmark-outline"></ion-icon>Be able to build Any website you want.</p>
					<p><ion-icon name="checkmark-outline"></ion-icon>Be able to build Any website you want.</p>
					<p><ion-icon name="checkmark-outline"></ion-icon>Be able to build Any website you want.</p>
					<p><ion-icon name="checkmark-outline"></ion-icon>Be able to build Any website you want.</p>
				</article>
			</div>
	
			<div class="course-include">
				<h3>This Course Includes</h3>
				<p>
					<span
						><ion-icon name="play-circle-outline"></ion-icon></span
					>40 hours on-demand video
				</p>
				<p>
					<span
						><ion-icon name="code-slash-outline"></ion-icon></span
					>Coding Exercises
				</p>
				<p>
					<span
						><ion-icon name="document-outline"></ion-icon></span
					>Articles
				</p>
				<p><span><ion-icon name="infinite-outline"></ion-icon></span>
				Full life-time access</p>
				
				<p><span><ion-icon name="trophy-outline"></ion-icon></span>
				Certificate of Completion</p>
	
			</div>
				<main class="course-content">
					<h2>Course Content</h2>
					<div class="course-length">
						<ul>
							<li>14 modules</li>
							<li>50 lectures</li>
							<li>22h 13m total length</li>
						</ul>
					</div>
					
					
					
					     		

        <!-- course module section -->
        <div class="course-module-group">
            
                    <!-- Loop through CourseModules and display them in the table -->
                    <%
                        List<CourseModule> modules = (List<CourseModule>) request.getAttribute("modules");
                        if (modules != null) {
                            for (CourseModule module : modules) {
                    %>
                        		
                        		
                    
		                    	<div class="course-module">
		                    	
									<h2 class="module-title">
										<%= module.getModuleTitle() %>
																			

										
									</h2>
									
									<!-- for id for js -->
									<!--<span style="display:none"><%= module.getModuleID() %> </span>-->
									
									<!-- <div class="module-outline">  add one </div> at the end of "%>" -->
									<div class="module-outline hide-expand" >
										<!-- query each material here -->
										<%
				                        List<CourseModuleMaterial> moduleMaterials = module.getCourseModuleMaterialList();
				                        if (moduleMaterials != null) {
				                            for (CourseModuleMaterial material : moduleMaterials) {
				                    %>
											<div class="material">
												<a
													href="http://localhost:8080/IT_Vector_Ver1/CoursePlayerServlet?materialID=<%= material.getMaterialID() %>"
													class=""
													><span><ion-icon name="play-circle-outline"></ion-icon></span
													><%= material.getMaterialName() %></a
												>
												
											</div>
					                     <%
					                            }
					                        }
					                    %>
				                    
					                    
					                    
					                    <!-- QUIZ -->
					                    <!-- <div class="module-outline"> -->
					                    
											<%
												List<Quiz> quizes = (List<Quiz>) session.getAttribute("quizes");
					                        if (modules != null) {
					                            for (Quiz quiz : quizes) {
					                            	if (quiz.getModuleID() == module.getModuleID()) {
					                    	%>
											<div class="material">
												<a
													href="http://localhost:8080/IT_Vector_Ver1/QuizServlet?quizID=<%= quiz.getQuizID() %>"
													class=""
													><span><ion-icon name="play-circle-outline"></ion-icon></span
													><%= quiz.getQuizName() %></a
												>
												
											</div>
														
												 <%
					                            	}
					                            }
					                        }
					                    	%>
				                    	
					                    	
					                    	
					                    	 <!-- QUIZ -->
					                    	 <!-- <div class="module-outline"> -->
				                    	
											    <%
											        List<Assignment> assignments = (List<Assignment>) session.getAttribute("assignments");
											        if (assignments != null) {
											            for (Assignment assignment : assignments) {
											            	if(assignment.getModuleID() == module.getModuleID()) {
											    %>
											    <div class="material">
											        <a href="http://localhost:8080/IT_Vector_Ver1/AssignmentPageServlet?assignmentID=<%= assignment.getAssignmentID() %>"
											            class="">
											            <span><ion-icon name="play-circle-outline"></ion-icon></span>
											            <%= assignment.getAssignmentName() %>
											        </a>
											        
											    </div>
											    <%
											            	}
											            }
											        }
											    %>
										
					                    	</div>
									</div>
                    
                    
                    <%
                            }
                        }
                    %>
              	
        </div>
        
				</main>

				<div class="requirements-and-course-description">
					<div class="requirements">
						<h2>Requirements</h2>
						<ul>
							<li>Access to a computer with internet connection</li>
						</ul>
					</div>
					<div class="course-detail-description">
						<h2>Description</h2>
						<p>
							Become a Python Programmer and learn one of employer's most requested skills of 2023!

This is the most comprehensive, yet straight-forward, course for the Python programming language on Udemy! Whether you have never programmed before, already know basic syntax, or want to learn about the advanced features of Python, this course is for you! In this course we will teach you Python 3.

With over 100 lectures and more than 21 hours of video this comprehensive course leaves no stone unturned! This course includes quizzes, tests, coding exercises and homework assignments as well as 3 major projects to create a Python project portfolio!

Learn how to use Python for real-world tasks, such as working with PDF Files, sending emails, reading Excel files, Scraping websites for informations, working with image files, and much more!

This course will teach you Python in a practical manner, with every lecture comes a full coding screencast and a corresponding code notebook! Learn in whatever manner is best for you!

We will start by helping you get Python installed on your computer, regardless of your operating system, whether its Linux, MacOS, or Windows, we've got you covered.
						</p>
					</div>
				</div>

				<section class="who-this-course-is-for">
					<h2>Who this course if for</h2>
					<ul>
						<li>If you want to learn to code by building fun and useful projects, then take this course.</li>
						<li>If you want to learn to code by building fun and useful projects, then take this course.</li>
					</ul>
				</section>
		
				<section class="teacher">
					<h2>Teachers</h2>
					<div class="teacher-introduction">
						<div class="teacher-profile">
							<a href="#"><h2>Teacher Name</h2></a>
							<p class="teacher-role">Developer, Project Manager</p>
						</div>						
						<img
							
							src="image/muchiro.jpg"
							alt="" />
						<p>
							Hello. I'm Kane Ezki, a coding tutor and bootcamp instructor. I have been the lead developer in several large projects across the globe and was the lead instructor at a bootcamp (that shall remain unnamed).

Today, I help teacher produce content and professional curriculum's, actionable tasks, homework and often work behind the scenes.

It's rare that you'll see me on camera because I prefer to be behind the camera. And as strange as it seems, I don't partake in too much social media (I prefer to live my life positively).

I've been writing HTML, CSS and JavaScript since 2001. And I've been writing Python since 2012. I've created websites for large organizations, and have almost completely automated my entire life with Python.

Thanks for checking out my Udemy profile!
						</p>
					</div>
				</section>
			</div>
		</div>

		

		
	<style>
		.hide-expand{
			display: none;
		}
	</style>
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	
	<script defer>
	
		
		var moduleOutline = document.querySelector(".module-outline");
		var courseModule = document.querySelectorAll(".course-module");
		
		
		
		console.log("hi");
		cousreModule.forEach(cm => console.log(cm))
	
	

	</script>

	
	</body>
</html>
