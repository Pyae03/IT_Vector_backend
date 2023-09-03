package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.CourseModule;
import dao.CourseModuleDao;

@WebServlet("/AddModuleServlet")
public class AddModuleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve module information from the POST request
        //String courseIDStr = request.getParameter("courseID");
        String moduleTitle = request.getParameter("module-title");
        System.out.println("course enter: " + moduleTitle);
        
        if(moduleTitle != null) {
        	
        	HttpSession session = request.getSession();
        	System.out.println("not null: " );
        	System.out.println("course id: " + session.getAttribute("courseID"));
        	int courseID = (int) session.getAttribute("courseID");
        	
        	
        	CourseModuleDao moduleDao = new CourseModuleDao(); 
        	moduleDao.createModule(courseID, moduleTitle);
        	
        	String url = "ModulePageServlet?courseId=" + courseID;
        	response.sendRedirect(url);
        } else {
        	response.sendRedirect("admin-pages/admin-courses.html");
        }
        
    }
}
