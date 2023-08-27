package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizDao;
import models.QuizOption;
import models.QuizQuestion;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {


            // Display the retrieved data
            Map<Integer, QuizQuestion> questionsWithOptions = QuizDao.getQuestionsWithOptions();

            // Display the retrieved data along with radio buttons and a submit button
            out.println("<html><head><title>Quiz Questions</title></head><body>");

            out.println("<form method=\"post\" action=\"SubmitQuizServlet\">");

            for (Map.Entry<Integer, QuizQuestion> entry : questionsWithOptions.entrySet()) {
                int questionID = entry.getKey();
                QuizQuestion question = entry.getValue();

                out.println("<h2>Question ID: " + questionID + "</h2>");
                out.println("<p>Question Text: " + question.getQuestionText() + "</p>");
                out.println("<p>Category: " + question.getCategoryName() + "</p>");

                out.println("<p>Options:</p><ul>");
                for (QuizOption option : question.getOptions()) {
                    out.println("<li>");
                    out.println("<input type=\"radio\" name=\"question_" + questionID + "\" value=\"" + option.getOptionText()+ "\">");
                    out.println(option.getOptionText());
                    out.println("</li>");
                }
                out.println("</ul>");

                out.println("<hr>"); // Add a separator between questions
            }

            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");

            out.println("</body></html>");


            // Collecting quiz
            //List<QuizQuestion> quizzes = question;
            //request.setAttribute("quizzes", quizzes);

            //RequestDispatcher dispatcher = request.getRequestDispatcher("/quiz-list.jsp");
            //dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Query questions and options
//    private Map<Integer, QuizQuestion> getQuestionsWithOptions(Connection connection) throws SQLException {
//        Map<Integer, QuizQuestion> questionsWithOptions = new HashMap<>();
//
//        String query = "SELECT qq.*, qo.* " +
//                "FROM QuizQuestion qq " +
//                "LEFT JOIN QuizOption qo ON qq.questionID = qo.questionID";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            while (resultSet.next()) {
//                int questionID = resultSet.getInt("qq.questionID");
//                int quizID = resultSet.getInt("qq.quizID");
//                String questionText = resultSet.getString("qq.questionText");
//                String categoryName = resultSet.getString("qq.categoryName");
//                int quizOptionID = resultSet.getInt("qo.quizOptionID");
//                String optionText = resultSet.getString("qo.optionText");
//                boolean isCorrect = resultSet.getBoolean("qo.isCorrect");
//
//                // Check if the question already exists in the map
//                if (!questionsWithOptions.containsKey(questionID)) {
//                    QuizQuestion question = new QuizQuestion(questionID, quizID, questionText, categoryName);
//                    questionsWithOptions.put(questionID, question);
//                }
//
//                // Add the option to the corresponding question
//                QuizQuestion question = questionsWithOptions.get(questionID);
//                question.addOption(new QuizOption(quizOptionID, questionID, optionText, isCorrect));
//            }
//        }
//
//        return questionsWithOptions;
//    }
}
