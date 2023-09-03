package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseModuleDao;
import models.CourseModule;

@WebServlet("/AssignmentPageServlet")
public class AssignmentPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the courseId parameter from the URL
        String courseIdStr = request.getParameter("courseId");
        System.out.println("assignemt course id: " + courseIdStr);
        if (courseIdStr != null) {
            try {
                // Parse the courseId as needed
                int courseId = Integer.parseInt(courseIdStr);

                // Store courseId in the user's session
                HttpSession session = request.getSession();
                session.setAttribute("courseID", courseId);
                //session.setMaxInactiveInterval(60 * 60 * 60);
                
                // Get course modules for the specified courseId
                CourseModuleDao courseModuleDao = new CourseModuleDao();
                List<CourseModule> modules = courseModuleDao.getModulesByCourseForAssignment(courseId);

                // Store the modules in a request attribute for use in module-page.jsp
                request.setAttribute("modules", modules);
                //session.setAttribute("moduleID", mo);
                // Redirect to the module page
                request.getRequestDispatcher("teacher-pages/teacher-create-task.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where courseId is not a valid integer
                response.sendRedirect("error-page.jsp"); // Redirect to an error page
            }
        } else {
            // Handle the case where courseId parameter is missing
            response.sendRedirect("error-page.jsp"); // Redirect to an error page
        }
    }
    
    
   
}
