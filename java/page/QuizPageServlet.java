package page;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDao;
import dao.CourseModuleDao;
import models.Category;
import models.CourseModule;

@WebServlet("/QuizPageServlet")
public class QuizPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the courseId parameter from the URL
        String courseIdStr = request.getParameter("courseId");
        System.out.println("assignemt course id: " + courseIdStr);
        if (courseIdStr != null) {
            try {

                int courseId = Integer.parseInt(courseIdStr);


                HttpSession session = request.getSession();
                session.setAttribute("courseID", courseId);
                //session.setMaxInactiveInterval(60 * 60 * 60);
                
                // Get all categories
                //CategoryDao categoryDao = new CategoryDao();
                List<Category> categories = CategoryDao.getAllCategories();
                for(Category category: categories) {
                	System.out.println("Category: "  + category.getCategoryName());
                }
                
                session.setAttribute("categories", categories);
                List<Category> sessionCategories = (List<Category>) session.getAttribute("categories");
                
                for(Category category: sessionCategories) {
                	System.out.println("Category: "  + category.getCategoryName());
                }
                
                // Get course modules for the specified courseId
                CourseModuleDao courseModuleDao = new CourseModuleDao();
                List<CourseModule> modules = courseModuleDao.getModulesByCourseForAssignment(courseId);


                request.setAttribute("modules", modules);
                //session.setAttribute("moduleID", mo);
                // Redirect to the module page
                request.getRequestDispatcher("teacher-pages/teacher-create-task.jsp").forward(request, response);
            } catch (NumberFormatException e) {

                response.sendRedirect("error-page.jsp"); // Redirect to an error page
            }
        } else {

            response.sendRedirect("error-page.jsp"); // Redirect to an error page
        }
    }
    
    
   
}
