<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz List</title>
</head>
<body>
    <h1>Quiz List</h1>
    
    <table border="1">
        <tr>
            <th>Quiz ID</th>
            <th>Quiz Name</th>
            <th>Description</th>
        </tr>
         <%@ page import="java.util.List" %>
        <%@ page import="models.Quiz" %>
        <%
            // Assuming 'quizzes' is a List<Quiz> retrieved from your servlet
            List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
            if (quizzes != null) {
                for (Quiz quiz : quizzes) {
        %>
        <tr>
            <td><%= quiz.getQuizID() %></td>
            <td><%= quiz.getQuizName() %></td>
            <td><%= quiz.getDescription() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
