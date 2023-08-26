package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String role = "";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        
        boolean isValidUser = validateUser(email, password);

        if (isValidUser) {
        	
        	switch(role) {
        	case "Student": response.sendRedirect("student-pages/student-dashboard.html"); break;
        	case "Teacher": response.sendRedirect("student-dashboard.jsp"); break;
        	case "Admin": response.sendRedirect("student-dashboard.jsp"); break;        	}
            // Redirect 
            //response.sendRedirect("dashboard.jsp");
        } else {
            // Display an error 
            response.sendRedirect("login.jsp?error=1");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private boolean validateUser(String email, String password) {
    	
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT UserID, UserRole FROM User WHERE Email = ? AND Password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            // get role
            
            if(resultSet.next()) {
            	role = resultSet.getString("UserRole");
            	System.out.println("role: " + role);
            	return true;
            }
            return false;
            	
            	//System.out.println("role: " + resultSet.getString("UserRole"));
            
            //return resultSet.next(); // If a row is found, the user is valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private String getRole() {
    	return "";
    }
}
