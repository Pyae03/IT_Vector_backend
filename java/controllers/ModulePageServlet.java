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

@WebServlet("/ModulePageServlet")
public class ModulePageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseIdStr = request.getParameter("courseId");

        if (courseIdStr != null) {
            try {

                int courseId = Integer.parseInt(courseIdStr);


                HttpSession session = request.getSession();
                session.setAttribute("courseID", courseId);
                //session.setMaxInactiveInterval(60 * 60 * 60);
                
                // Get course modules for the specified courseId
                CourseModuleDao courseModuleDao = new CourseModuleDao();
                List<CourseModule> modules = courseModuleDao.getModulesByCourse(courseId);

                request.setAttribute("modules", modules);

                // ASSIGNMENT
               AssignmentDao assignmentDao = new AssignmentDao();
               List<Assignment> assignments = assignmentDao.getAllAssignments();
                session.setAttribute("assignments", assignments);
                
                //QUIZ
                QuizDao quizDao = new QuizDao();
                List<Quiz> quizes = quizDao.getAllQuizzes();
                session.setAttribute("quizes", quizes);

                request.getRequestDispatcher("admin-pages/admin-course-module.jsp").forward(request, response);
            } catch (NumberFormatException | SQLException e) {
                // Handle the case where courseId is not a valid integer
                response.sendRedirect("error-page.jsp"); // Redirect to an error page
            }
        } else {
            // Handle the case where courseId parameter is missing
            response.sendRedirect("error-page.jsp"); // Redirect to an error page
        }
    }
    
    
   
}
