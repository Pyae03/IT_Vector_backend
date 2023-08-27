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
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "list":
                listUsers(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "create": 
            	
            
				try {
					System.out.println("creating...");
					createUser(request, response);
				} catch (ServletException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
            	break;
            default:
                listUsers(request, response);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = UserDao.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDao.getUserById(id);
        request.setAttribute("users", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement updating user details
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement user deletion
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
