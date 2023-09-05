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

public class QuizQuestionDao {


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

    public static int createQuizQuestion(QuizQuestion question) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String insertQuestionSQL = "INSERT INTO QuizQuestion (quizID, questionText, categoryName) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuestionSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, question.getQuizID());
            preparedStatement.setString(2, question.getQuestionText());
            preparedStatement.setString(3, question.getCategoryName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int questionID = -1;
            if (generatedKeys.next()) {
                questionID = generatedKeys.getInt(1);
            }

            return questionID;
        }
    }
    
    public static QuizQuestion getLastQuizQuestion() throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM QuizQuestion ORDER BY questionID DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int questionID = resultSet.getInt("questionID");
                int quizID = resultSet.getInt("quizID");
                String questionText = resultSet.getString("questionText");
                String categoryName = resultSet.getString("categoryName");

                return new QuizQuestion(questionID, quizID, questionText, categoryName);
            }
        }
        return null; // eeturn null if no quiz questions are found
    }
    
    
    public static Map<Integer, QuizQuestion> getQuestionsWithOptionsByQuizID(int quizID) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {

            Map<Integer, QuizQuestion> questionsWithOptions = new HashMap<>();

            String query = "SELECT qq.*, qo.* " +
                    "FROM QuizQuestion qq " +
                    "LEFT JOIN QuizOption qo ON qq.questionID = qo.questionID " +
                    "WHERE qq.quizID = ?"; 

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizID); 

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int questionID = resultSet.getInt("qq.questionID");
                String questionText = resultSet.getString("qq.questionText");
                String categoryName = resultSet.getString("qq.categoryName");
                int quizOptionID = resultSet.getInt("qo.quizOptionID");
                String optionText = resultSet.getString("qo.optionText");
                boolean isCorrect = resultSet.getBoolean("qo.isCorrect");

                
                if (!questionsWithOptions.containsKey(questionID)) {
                    QuizQuestion question = new QuizQuestion(questionID, quizID, questionText, categoryName);
                    questionsWithOptions.put(questionID, question);
                }

                
                QuizQuestion question = questionsWithOptions.get(questionID);
                question.addOption(new QuizOption(quizOptionID, questionID, optionText, isCorrect));
            }

            return questionsWithOptions;
        }
    }

}
