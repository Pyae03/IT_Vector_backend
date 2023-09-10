<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.CourseModule" %>
<%@ page import="models.CourseModuleMaterial" %>
<%@ page import="dao.CourseModuleMaterialDao" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Category" %>
<%@ page import="models.Quiz" %>
<%@ page import="models.Assignment" %>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
	<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin-pages/student-course-player.style.css">
		

		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1.0" />
		<title>Document</title>
	</head>
	<body>
		<nav>
			
			<a href="student-pages/student-course.html">
				<h3>Go Back</h3>
			</a>
		</nav>
		
		<%
			CourseModuleMaterial currentMaterial = (CourseModuleMaterial) request.getAttribute("currentMaterial");
		%>
	
		<div class="container">
			<div class="video-and-content">
				<div class="video-player">
				

					<video controls src="<%= request.getAttribute("videoFilePath") %>"></video>
				</div>
				<div class="content">
					<h2>Title</h2>
					<p>
						<%= currentMaterial.getMaterialDescription() %>
					</p>
				</div>
			</div>

			
				<!-- -------------------------------------------------------- -->
	<div class="view-course-module">
        <!-- course module section -->
        <div class="course-module-group">
            
                    <!-- Loop through CourseModules and display them in the table -->
                    <%
                        List<CourseModule> modules = (List<CourseModule>) request.getAttribute("modules");
                        if (modules != null) {
                            for (CourseModule module : modules) {
                    %>
                        		
                        		
                    
		                    	<div class="course-module">
		                    	
									<h2 class="module-title" id="module-title-<%= module.getModuleID() %>">
										<%= module.getModuleTitle() %>
																			

										<%= module.getModuleID() %>
									</h2>
									
									<!-- for id for js -->
									<!--<span style="display:none"><%= module.getModuleID() %> </span>-->
									
									<!-- <div class="module-outline">  add one </div> at the end of "%>" -->
									<div class="module-outline" id="module-outline-<%= module.getModuleID() %>">
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
        </div>
			
			
		</div>
		
		
	</body>

		<script>
		
    // Add event listeners to all module titles
    
    	
	    const moduleTitles = document.querySelectorAll('.module-title');
	    moduleTitles.forEach(moduleTitle => {
	        moduleTitle.addEventListener('click', () => {
	            // Get the module ID from the clicked title's ID
	            console.log("clicked~")
	            const moduleID = moduleTitle.id.replace('module-title-', '');
	            console.log("moudleTitle: ", moduleTitle, moduleID)
	            // find the  module outline to its module
	            
	            const moduleOutline = document.getElementById("module-outline-"+ moduleID);
	            
	            //console.log("moduleoutline: ", moduleOutline, moduleID, id)
	            
	            if (moduleOutline) {
	                if (moduleOutline.style.display === 'none' || moduleOutline.style.display === '') {
	                    moduleOutline.style.display = 'block';
	                } else {
	                    moduleOutline.style.display = 'none';
	                }
	            }
	        });
	    });
    
    console.log("started: ", moduleTitles)
</script>

		
	
</html>
