package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AssignmentDao;
import dao.CourseModuleMaterialDao;
import dao.QuizDao;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
 
		
		switch(type) {
		case "assignment": {
			int assignmentID = Integer.parseInt(request.getParameter("assignmentID"));
				boolean success = AssignmentDao.deleteAssignment(assignmentID);
				if(success) {
					System.out.println("ASSIGNMENT!");
					// go to another link
				}
			} break;
		case "quiz": {
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			boolean success = false;
			try {
				success = QuizDao.deleteQuiz(quizID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(success) {
				System.out.println("QUIZ!");
				// go to another link
			}
		}break;
		case "material": {
			int materialID = Integer.parseInt(request.getParameter("materialID"));
			boolean success = CourseModuleMaterialDao.deleteMaterial(materialID);
			if(success) {
				System.out.println("MATERIAL!");
				// go to another link
			}
		}break;
		
		default: System.out.println("ERROR!");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
}
