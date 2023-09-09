package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AssignmentDao;
import dao.CourseModuleDao;
import dao.QuizDao;
import models.Assignment;
import models.CourseModule;
import models.Quiz;
import models.User;

@WebServlet("/CourseDetailPageServlet")
public class CourseDetailPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseIdStr = request.getParameter("courseId");

        System.out.println("courseid corseDeail: " + courseIdStr);
        if (courseIdStr != null) {
            try {

                int courseId = Integer.parseInt(courseIdStr);

                

                HttpSession session = request.getSession();
                session.setAttribute("courseID", courseId);
                
                
                
                // Get course modules for the specified courseId
                CourseModuleDao courseModuleDao = new CourseModuleDao();
                List<CourseModule> modules = courseModuleDao.getModulesByCourse(courseId);

                request.setAttribute("modules", modules);

                // ASSIGNMENT
               
               List<Assignment> assignments = AssignmentDao.getAllAssignments();
                session.setAttribute("assignments", assignments);
                
                //QUIZ
                
                List<Quiz> quizes = QuizDao.getAllQuizzes();
                
                session.setAttribute("quizes", quizes);

                request.getRequestDispatcher("home-page/course-detail.jsp").forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                
                response.sendRedirect("error-page.jsp"); // Redirect to an error page
            }
        } else {

            response.sendRedirect("error-page.jsp"); // Redirect to an error page
        }
    }
    
    
   
}
