package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import models.User;

@SuppressWarnings("serial")
@WebServlet("/admin-pages/UserController")
public class UserController extends HttpServlet {
    private UserDao userDao;

    @Override
	public void init() {
        userDao = new UserDao();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email"); // must be unique
    	String gender = request.getParameter("gender");
    	String role = request.getParameter("role");
    	String DOB = request.getParameter("date-of-birth");
    	 
    	System.out.println(username + password + email +  gender + DOB);
     
    	Date dateOfBrith = null;
		try {
			dateOfBrith = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		User newUser = new User(username, password, email, gender, dateOfBrith);
		
		boolean userCreated = UserDao.createUser(newUser, role);
		
		if(userCreated) {
			response.sendRedirect("admin-database.html");
		} else {
			response.sendRedirect("admin-dashboard.jsp");
		}
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = UserDao.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    } 
    // for admin
    private void createUser(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException, ParseException {
    	
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email"); // must be unique
    	String gender = request.getParameter("gender");
    	String role = request.getParameter("role");
    	String DOB = request.getParameter("date-of-birth"); 
    	System.out.println(username + password + email +  gender + DOB);
     
    	Date dateOfBrith = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
    	
		User newUser = new User(username, password, email, gender, dateOfBrith);
		
		boolean userCreated = UserDao.createUser(newUser, role);
		
		if(userCreated) {
			response.sendRedirect("admin-database.html");
		} else {
			response.sendRedirect("admin-dashboard.html");
		}
    	
    }
}
