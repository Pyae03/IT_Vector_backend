<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="models.Assignment" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1.0" />
		<link
			rel="stylesheet"
			href="${pageContext.request.contextPath}/student-pages/student-assignment.style.css" />
		<title>Assignment Submission</title>
	</head>
	<body>
	 <% Assignment assignment = (Assignment) session.getAttribute("assignment");%>
		<div class="container">
			<div class="assignment-question">
			<h3><%= assignment.getAssignmentName() %></h3>
			<a href="<%= assignment.getAssignmentFilePath() %>" download>download assignment file here</a>
		</div>

		<div class="assignment-submission">
			<h1>Assignment Submission</h1>
			<form
				action="AssignmentSubmitServlet"
				method="post"
				id="submissionForm"
				enctype="multipart/form-data">
				<label for="file">Choose a file:</label>
				<input type="text" name="assignment-id" style="display: none" value="<%= assignment.getAssignmentID() %>">
				<input
					type="file"
					id="file"
					name="file"
					accept=".txt, .pdf, .doc, .docx"
					required /><br /><br />

				<button type="submit">Submit</button>
			</form>
		</div>
			<a
			style="
				border-radius: var(--btn-border-radius);
				background-color: var(--light-blue);
				padding: .5em;
				max-width: 5em;
				color: var(--white-color);
			"
			 href="student-pages/student-todo.jsp">Go Back</a>
		</div>		
	</body>
</html>
