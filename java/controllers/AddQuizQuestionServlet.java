package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizOptionDao;
import dao.QuizQuestionDao;
import models.QuizOption;
import models.QuizQuestion;

@WebServlet("/AddQuizQuestionServlet")
public class AddQuizQuestionServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String questionText = request.getParameter("questionText");
        String categoryName = request.getParameter("categoryName");
        String[] optionTexts = request.getParameterValues("optionTexts[]");
        String[] correctOptions = request.getParameterValues("correctOptions[]");
        
        int quizID = Integer.parseInt(request.getParameter("quiz"));
        // Validate the input data here

        System.out.println("questionText: " + questionText);
        System.out.println("categoryName: " + categoryName);
        System.out.println("optionTexts: " + optionTexts);
        System.out.println("correctOptins: " + correctOptions);
        System.out.println("quizID: " + quizID);
        QuizQuestion question = new QuizQuestion(quizID, questionText, categoryName);
        try {
			QuizQuestionDao.createQuizQuestion(question);
		} catch (SQLException e) {

			e.printStackTrace();
		}

        int currentQuestionID = 0;
        try {
			currentQuestionID = QuizQuestionDao.getLastQuizQuestion().getQuestionID();
		} catch (SQLException e) {

			e.printStackTrace();
		} 
        for (int i = 0; i < optionTexts.length; i++) {
            boolean isCorrect = Arrays.asList(correctOptions).contains(Integer.toString(i));
            System.out.println("questinoID: " + question.getQuestionID() + " " + optionTexts[i] + " " + isCorrect);
            QuizOption option = new QuizOption(currentQuestionID, optionTexts[i], isCorrect);
            try {
				QuizOptionDao.createQuizOption(option);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        }

        // Redirect to a success or error page
        response.sendRedirect("question_created.jsp");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
