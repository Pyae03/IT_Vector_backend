package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuizQuestionDao;
import dao.QuizResultDao;
import models.QuizOption;
import models.QuizQuestion;
import models.QuizResult;
import models.User;
import util.DatabaseUtil;

@WebServlet("/SubmitQuizServlet")
public class SubmitQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        
        int quizID = (int) session.getAttribute("quizID");
        try (Connection connection = DatabaseUtil.getConnection()) {
            Map<Integer, QuizQuestion> questionsWithOptions = QuizQuestionDao.getQuestionsWithOptionsByQuizID(quizID);

            int score = 0; // Initialize the user's score
             
            out.println("<html><head><title>Quiz Results</title></head><body>");

            for (Map.Entry<Integer, QuizQuestion> entry : questionsWithOptions.entrySet()) {
            	
            	System.out.println("entry: " + entry);
                int questionID = entry.getKey();
                QuizQuestion question = entry.getValue();

                String selectedOption = request.getParameter("question_" + questionID);
                
                

                //if(quizID == null) {
                	quizID = question.getQuizID();
                //}
                
                
                out.println("<h2>Question ID: " + questionID + "</h2>");
                out.println("<p>Question Text: " + question.getQuestionText() + "</p>");
                out.println("<p>Category: " + question.getCategoryName() + "</p>");

                out.println("<p>Selected Option: " + selectedOption + "</p>");

                QuizOption correctOption = getCorrectOption(question);

                if (selectedOption != null && selectedOption.equals(String.valueOf(correctOption.getOptionText()))) {
                    out.println("<p>Your answer is correct!</p>");
                    score++;
                } else {
                    out.println("<p>Correct answer: " + correctOption.getOptionText() + "</p>");
                }

                out.println("<hr>"); // Add a separator between questions
            }

            out.println("<p>Your Total Score: " + score + "</p>");
            
            //HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");
            System.out.println("current user: " + currentUser.getUserID() + currentUser.getUsername());            
            QuizResult quizResult = new QuizResult(quizID, currentUser.getUserID(), score);
            QuizResultDao qrDao = new QuizResultDao();
            qrDao.insertQuizResult(quizResult);
            // resultQuiz table id
            // current user id
            // current quiz id
            // score
            // fix current user is null

            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to get the correct option for a question
    private QuizOption getCorrectOption(QuizQuestion question) {
        for (QuizOption option : question.getOptions()) {
            if (option.isCorrect()) {
                return option;
            }
        }
        return null;
    }

    
}
