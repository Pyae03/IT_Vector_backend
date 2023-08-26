package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import models.User;

@WebServlet("/controllers/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String DOB = request.getParameter("date-of-brith");
        
        
        
        Date dateOfBrith = null;
		try {
			dateOfBrith = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
			User newUser = new User(username, password, email, gender, dateOfBrith);
			boolean userCreated;
			
			userCreated = UserDao.createUser(newUser);
			System.out.println("userCreated: " + userCreated);
			if (userCreated) {
				// Redirect to a registration success page
				response.sendRedirect("user-list.jsp");
			} else {
				// Redirect to a registration error page
				response.sendRedirect("/user-auth/login.jsp");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        
    }
}
