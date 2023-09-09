package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EnrollmentDao;
import dao.UserDao;
import models.Enrollment;
import models.User;

import java.io.IOException;

@WebServlet("/EnrollmentServlet")
public class EnrollmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the user ID from the session
        HttpSession session = request.getSession();
        
        System.out.println("enrollment userid: "+ session.getAttribute("userID"));
        if(session != null && session.getAttribute("user") != null) {
        	
        	User currentLoginUser = (User) session.getAttribute("user");
        	
        	String userID = currentLoginUser.getUserID();

            
            boolean userExists = UserDao.userExists(userID); 

            if (!userExists) {

                response.sendRedirect("register.jsp"); 
            } else {
            	System.out.println("courseID enrollment: "  + session.getAttribute("courseID"));

                int courseID = (int) session.getAttribute("courseID"); 


                
                Enrollment enrollment = new Enrollment();
                enrollment.setStudentID(userID);
                enrollment.setCourseID(courseID);

                

                
                EnrollmentDao enrollmentDao = new EnrollmentDao(); 
                boolean enrollmentSuccess = enrollmentDao.createEnrollment(enrollment); 

                if (enrollmentSuccess) {
                    
                    response.sendRedirect("enrollment-success.jsp"); 
                } else {
                    
                    response.sendRedirect("enrollment-error.jsp"); 
                }

            }
        } else {
        	response.sendRedirect("register.jsp"); 
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
