<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Gender</th>
            <th>User Role</th>
            <th>Registration Date</th>
        </tr>
        <%@ page import="java.util.List" %>
        <%@ page import="models.User" %>
        
        <%
        List<User> users = (List<User>) request.getAttribute("users");
            for (User user : users) {
        %>
        <tr>
            <td><%= user.getUserID() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getDateOfBirth() %></td>
            <td><%= user.getGender() %></td>
            <td><%= user.getUserRole() %></td>
            <td><%= user.getRegistrationDate() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
