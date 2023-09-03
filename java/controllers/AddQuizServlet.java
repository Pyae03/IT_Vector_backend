package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuizDao;
import models.Quiz;

@WebServlet("/AddQuizServlet")
public class AddQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    		HttpSession session = request.getSession();
        try {
        	
            int courseID = (int) session.getAttribute("courseID");
            int moduleID = Integer.parseInt(request.getParameter("get-module"));
            String quizName = request.getParameter("quizName");
            String description = request.getParameter("description");

            // Create a new Quiz object
            System.out.println("courseID :" + courseID);
            System.out.println("moduleID :" + moduleID);
            System.out.println("quizName :" + quizName);
            System.out.println("description :" + description); 
            Quiz newQuiz = new Quiz(courseID, moduleID, quizName, description);

            // Add the quiz to the database
            boolean success = QuizDao.createQuiz(newQuiz);

            if(success) {
            	System.out.println("SUCCESS!---------------------");
            }
            // Redirect to a success page or display a success message
            response.sendRedirect("success.jsp?quizID=");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database-related errors
            response.sendRedirect("error.jsp");
        }
    }
}
