package controllers;

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
import javax.servlet.http.HttpSession;

import dao.UserDao;
import models.User;
import util.DatabaseUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String role = "";

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        boolean isValidUser = validateUser(email, password);

        if (isValidUser) {

        	// for session
        	HttpSession session = request.getSession(true);

        	User currentLoginUser;
			try {
				currentLoginUser = UserDao.getCurrentUserWithEmail(email);
				System.out.println("current: " + currentLoginUser.getUserID());
				session.setAttribute("user", currentLoginUser);
				session.setAttribute("isLoggedIn", true);
				
				int sessionTimeOut = 7 * 24 * 60 * 60;
				session.setMaxInactiveInterval(sessionTimeOut);
				
				System.out.println("userID: " + currentLoginUser.getUserID());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	


            // for role Student, Teacher, Admin
        	switch(role) {
        	//case "Student": response.sendRedirect("student-pages/student-dashboard.html"); break;
        	case "Student": response.sendRedirect("student-pages/student-dashboard.html"); break;
        	case "Teacher": response.sendRedirect("teacher-pages/teacher-course.html"); break;
        	case "Admin": response.sendRedirect("admin-pages/admin-dashboard.jsp"); break;        	}
            // Redirect
            //response.sendRedirect("dashboard.jsp");
        } else {
            // Display an error
            response.sendRedirect("login.jsp?error=1");
        }
    }
    @Override
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

     
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getRole() {
    	return "";
    }
}
