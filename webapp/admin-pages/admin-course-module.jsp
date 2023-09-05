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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin-pages/admin-course-module.style.css">

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
</head>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="${pageContext.request.contextPath}/admin-pages/admin-course-module.script.js"></script>

<body>
    <div class="container">
        <nav>
            <div class="back">
                <button><a href="">Back</a></button>
            </div>
            <div class="btn-function">
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
										
										
										<span onclick="createMaterialForm(<%= module.getModuleID() %>)"  class="add-course-module"
											><ion-icon name="add-outline"></ion-icon
										></span>
				
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
									
                    
                    
                    <%
                            }
                        }
                    %>
              	
        </div>
    </div>
    
    <script>
 // forms

    const module_creation_form = document.querySelector(".module-creation-form");
 	const add_course_module = document.querySelector(".add-course-module")
    add_course_module.addEventListener("click", () => {
    	console.log("clicked");
    	module_creation_form.classList.toggle("hidden");
    });
    function createMaterialForm(moduleID) {
    	console.log("created!")
        // Create the main container for the form
        var formContainer = document.createElement("div");
        formContainer.classList.add("module-material-creation-form");

        // Create the form element
        var form = document.createElement("form");
        form.action = "AddMaterialServlet";
        form.method = "post";
        form.enctype = "multipart/form-data";

        // Create the hidden input for module ID
        var moduleIDInput = document.createElement("input");
        moduleIDInput.type = "hidden";
        moduleIDInput.name = "module-id";
        moduleIDInput.value = moduleID;

        // Create the Material Name input
        var materialNameLabel = document.createElement("label");
        materialNameLabel.textContent = "Material Name";
        var materialNameInput = document.createElement("input");
        materialNameInput.type = "text";
        materialNameInput.name = "material-name";

        // Create the Description textarea
        var descriptionLabel = document.createElement("label");
        descriptionLabel.textContent = "Description";
        var descriptionTextarea = document.createElement("textarea");
        descriptionTextarea.name = "material-description";
        descriptionTextarea.cols = "30";
        descriptionTextarea.rows = "10";

        // Create the Lecture Video File input
        var lectureLabel = document.createElement("label");
        lectureLabel.textContent = "Lecture Video File";
        var lectureFileInput = document.createElement("input");
        lectureFileInput.type = "file";
        lectureFileInput.name = "lecture-file";

        // Create the buttons container
        var btnGroup = document.createElement("div");
        btnGroup.classList.add("btn-group");

        // Create the Add Material button
        var submitButton = document.createElement("input");
        submitButton.type = "submit";
        submitButton.value = "Add Material";

        // Create the Cancel button
        var cancelButton = document.createElement("button");
        cancelButton.type = "button";
        cancelButton.textContent = "Cancel";
        cancelButton.className = "btn-cancel";
        cancelButton.onclick = () => destroyMaterialForm(); // Assuming cancel is a defined function

        // Append all elements to the form
        form.appendChild(moduleIDInput);
        form.appendChild(materialNameLabel);
        form.appendChild(materialNameInput);
        form.appendChild(descriptionLabel);
        form.appendChild(descriptionTextarea);
        form.appendChild(lectureLabel);
        form.appendChild(lectureFileInput);
        btnGroup.appendChild(submitButton);
        btnGroup.appendChild(cancelButton);
        form.appendChild(btnGroup);

        // Append the form to the main container
        formContainer.appendChild(form);

        // Append the main container to the desired parent element
        var parentElement = document.querySelector(".course-module-group"); // Change this selector as needed
        parentElement.appendChild(formContainer)
        
    	//course_group.insertBefore(formContainer)    
    }


    function destroyMaterialForm(){
     	const formContainer = document.querySelector(".module-material-creation-form")
     	formContainer.remove()
    }
    </script>
    <!-- <script src="${pageContext.request.contextPath}/admin-pages/admin-course-module.script.js"></script>-->
</body>




</html>
