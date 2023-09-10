package page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AssignmentDao;

import models.Assignment;


@WebServlet("/AssignmentPageServlet")
public class AssignmentPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    		int assignmentID = Integer.parseInt( request.getParameter("assignmentID"));
    		//int moduleID = Integer.parseInt(request.getParameter("moduleID"));
            try {

                HttpSession session = request.getSession();
                
                
                Assignment assignment = AssignmentDao.getAssignmentByID(assignmentID);
                session.setAttribute("assignment", assignment);
                
                
                request.getRequestDispatcher("student-pages/student-assignment.jsp").forward(request, response);
            } catch (NumberFormatException e) {

                response.sendRedirect("error-page.jsp"); // Redirect to an error page
            }
        
    }
    
    
   
}
