package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import models.QuizOption;
import models.QuizQuestion;
import util.DatabaseUtil;

public class QuizDao {


	// Query questions and options
    public static Map<Integer, QuizQuestion> getQuestionsWithOptions() throws SQLException {

    	try(Connection connection = DatabaseUtil.getConnection()){

    		Map<Integer, QuizQuestion> questionsWithOptions = new HashMap<>();

            String query = "SELECT qq.*, qo.* " +
                    "FROM QuizQuestion qq " +
                    "LEFT JOIN QuizOption qo ON qq.questionID = qo.questionID";

            System.out.println("connection estiblished");
            System.out.println("connection: "  +connection);
            	PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int questionID = resultSet.getInt("qq.questionID");
                    int quizID = resultSet.getInt("qq.quizID");
                    String questionText = resultSet.getString("qq.questionText");
                    String categoryName = resultSet.getString("qq.categoryName");
                    int quizOptionID = resultSet.getInt("qo.quizOptionID");
                    String optionText = resultSet.getString("qo.optionText");
                    boolean isCorrect = resultSet.getBoolean("qo.isCorrect");

                    System.out.println();
                    // Check if the question already exists in the map
                    if (!questionsWithOptions.containsKey(questionID)) {
                        QuizQuestion question = new QuizQuestion(questionID, quizID, questionText, categoryName);
                        questionsWithOptions.put(questionID, question);
                    }

                    // Add the option to the corresponding question if the same question until next quesiton
                    QuizQuestion question = questionsWithOptions.get(questionID);
                    question.addOption(new QuizOption(quizOptionID, questionID, optionText, isCorrect));

            // use hashmap
            // to keep the question and option together

//            return questionsWithOptions;
            }
         return questionsWithOptions;
    	}
    }
}
