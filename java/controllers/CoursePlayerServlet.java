package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AssignmentDao;
import dao.CourseModuleDao;
import dao.CourseModuleMaterialDao;
import dao.QuizDao;
import models.Assignment;
import models.CourseModule;
import models.CourseModuleMaterial;
import models.Quiz;
import models.User;

@WebServlet("/CoursePlayerServlet")
public class CoursePlayerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	response.setContentType("video/mp4");
        String materialID = request.getParameter("materialID");
        
        System.out.println("materialID" + materialID);
        
        CourseModuleMaterialDao materialDao = new CourseModuleMaterialDao();

        // side bar (right) ---------------------------------------------------------
        HttpSession session = request.getSession();
        int courseId = (int) session.getAttribute("courseID");
        
        
			CourseModuleMaterial currentMaterial;
			try {
				currentMaterial = CourseModuleMaterialDao.getMaterialByID(Integer.parseInt(materialID));
				request.setAttribute("currentMaterial", currentMaterial);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        //session.setAttribute("courseID", courseId);
        //session.setMaxInactiveInterval(60 * 60 * 60);
        
        //User currentUser  = (User) session.getAttribute("user");
        //	System.out.println("current User:" + currentUser);
        //	System.out.println("username: " + currentUser.getUsername());
        //	System.out.println("userRole: " + currentUser.getUserRole());
        
        
        // Get course modules for the specified courseId
        CourseModuleDao courseModuleDao = new CourseModuleDao();
        List<CourseModule> modules = courseModuleDao.getModulesByCourse(courseId);

        request.setAttribute("modules", modules);

        // ASSIGNMENT
       
       List<Assignment> assignments = AssignmentDao.getAllAssignments();
        session.setAttribute("assignments", assignments);
        
        //QUIZ
        
        List<Quiz> quizes = null;
		try {
			quizes = QuizDao.getAllQuizzes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        session.setAttribute("quizes", quizes);
        
        
        //-----------------------------------------------------------------------
        try {
            // Query for the specific course material based on materialID
            CourseModuleMaterial material = materialDao.getMaterialByID(Integer.parseInt(materialID));



            
            request.setAttribute("courseMaterial", material);
            
            // video file path
            System.out.println("vidoe file path: " + material.getFilePath());
            String filePath = material.getFilePath();
            String serverFilePath = filePath.substring(16, filePath.length());
            System.out.println("actual file path: " + serverFilePath);
            
            request.setAttribute("videoFilePath", "http://localhost:8081/" + serverFilePath);
            
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin-pages/student-course-player.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving course material.");
        }
    }
}

