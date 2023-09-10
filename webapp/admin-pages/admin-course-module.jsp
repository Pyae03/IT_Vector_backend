<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.CourseModule" %>
<%@ page import="models.CourseModuleMaterial" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Category" %>
<%@ page import="models.Quiz" %>
<%@ page import="models.Assignment" %>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin-pages/admin-course-module.style.css">

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
</head>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>


<body>
    <div class="container">
        <nav>
            <div class="back">
            <%	User currentUser = (User) session.getAttribute("user");
            	String url = "admin-pages/admin-courses.html";
            	if(currentUser.getUserRole() == "Teacher") {
            		url = "teacher-pages/teacher-course.html";
            
               	    }
            %>
                <button><a href="<%= url %>">Go Back</a></button>
            		<%= currentUser.getEmail() %>">
            </div>
            <div class="btn-function">
				<button class="add-quiz-question">Add Quiz Question</button>
                <button class="add-course-module">Add Module</button>
            </div>
        </nav>

        <!-- module creation form -->
        <div class="module-creation-form hidden">
            <form action="AddModuleServlet" method="POST" >
                <label for="m-title">Module Title</label>
                <input type="text" name="module-title" required />
                <input type="submit" value="Add New Module" />
            </form>
        </div>
        
        

<!--  here repaste form meterial -->

                        		
     		<!-- -------------------------------------------------------- -->

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
																			

										<span  class="add-course-module-material"
											><ion-icon name="add-outline" id="<%= module.getModuleID() %>"></ion-icon
										>
											<div id="<%= module.getModuleID() %>" class="add-task hidden">
												<p class="add-material" onclick="createMaterialForm(<%= module.getModuleID() %>)">
													<ion-icon name="bookmark-outline"></ion-icon>Material
												</p>
												<p class="add-assignment" onclick="assignmentCreationForm(<%= module.getModuleID() %>)">
													<ion-icon name="document-outline"></ion-icon>Assignment
												</p>
												<p class="add-quiz" onclick="quizCreationForm(<%= module.getModuleID() %>)">
													<ion-icon name="help-outline"></ion-icon>Quiz
												</p>
											</div>
										</span>
				
										<span  class="delete-course-module">
											<ion-icon name="trash-outline"></ion-icon>
										</span>
									</h2>
									
									<!-- for id for js -->
									<!--<span style="display:none"><%= module.getModuleID() %> </span>-->
									
									<!-- <div class="module-outline">  add one </div> at the end of "%>" -->
									<div class="module-outline">
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
												<a href="DeleteServlet?materialID=<%= material.getMaterialID() %>&type=material">
												<span class="delete-course-module-material"
													><ion-icon name="trash-outline"></ion-icon
												></span>
												</a>
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
												<a href="DeleteServlet?quizID=<%= quiz.getQuizID() %>&type=quiz">
													<span class="delete-course-module-material"
														><ion-icon name="trash-outline"></ion-icon
													></span>
												</a>
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
											        <a href="DeleteServlet?assignmentID=<%= assignment.getAssignmentID() %>&type=assignment">
												        <span class="delete-course-module-material">
												            <ion-icon name="trash-outline"></ion-icon>
												        </span>
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
    
  
<script src="${pageContext.request.contextPath}/admin-pages/admin-course-module.script.js"></script>
</body>




</html>
