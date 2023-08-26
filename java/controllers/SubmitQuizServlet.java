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

import Util.DatabaseUtil;
import models.QuizOption;
import models.QuizQuestion;

@WebServlet("/SubmitQuizServlet")
public class SubmitQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = DatabaseUtil.getConnection()) {
            Map<Integer, QuizQuestion> questionsWithOptions = getQuestionsWithOptions(connection);

            int score = 0; // Initialize the user's score

            out.println("<html><head><title>Quiz Results</title></head><body>");

            for (Map.Entry<Integer, QuizQuestion> entry : questionsWithOptions.entrySet()) {
                int questionID = entry.getKey();
                QuizQuestion question = entry.getValue();

                String selectedOption = request.getParameter("question_" + questionID);

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
    
    private Map<Integer, QuizQuestion> getQuestionsWithOptions(Connection connection) throws SQLException {
        Map<Integer, QuizQuestion> questionsWithOptions = new HashMap<>();

        String query = "SELECT qq.*, qo.* " +
                "FROM QuizQuestion qq " +
                "LEFT JOIN QuizOption qo ON qq.questionID = qo.questionID";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int questionID = resultSet.getInt("qq.questionID");
                int quizID = resultSet.getInt("qq.quizID");
                String questionText = resultSet.getString("qq.questionText");
                String categoryName = resultSet.getString("qq.categoryName");
                int quizOptionID = resultSet.getInt("qo.quizOptionID");
                String optionText = resultSet.getString("qo.optionText");
                boolean isCorrect = resultSet.getBoolean("qo.isCorrect");

                // Check if the question already exists in the map
                if (!questionsWithOptions.containsKey(questionID)) {
                    QuizQuestion question = new QuizQuestion(questionID, quizID, questionText, categoryName);
                    questionsWithOptions.put(questionID, question);
                }

                // Add the option to the corresponding question
                QuizQuestion question = questionsWithOptions.get(questionID);
                question.addOption(new QuizOption(quizOptionID, questionID, optionText, isCorrect));
            }
        }

        return questionsWithOptions;
    }
}
