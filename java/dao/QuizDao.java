package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DatabaseUtil;
import models.Quiz;

public class QuizDao {

    // Create a new quiz
    public static boolean createQuiz(Quiz quiz) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String insertQuizSQL = "INSERT INTO Quiz (courseID, moduleID, quizName, description) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuizSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, quiz.getCourseID());
            preparedStatement.setInt(2, quiz.getModuleID());
            preparedStatement.setString(3, quiz.getQuizName());
            preparedStatement.setString(4, quiz.getDescription());
            preparedStatement.executeUpdate();

//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            int quizID = -1;
//            if (generatedKeys.next()) {
//                quizID = generatedKeys.getInt(1);
//            }

            return true;
        }
    }

    // Read all quizzes
    public static List<Quiz> getAllQuizzes() throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            String selectQuizzesSQL = "SELECT * FROM Quiz";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuizzesSQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                String quizName = resultSet.getString("quizName");
                String description = resultSet.getString("description");

                Quiz quiz = new Quiz(quizID, quizName, description);
                quizzes.add(quiz);
            }
        }
        return quizzes;
    }

    // Update an existing quiz
    public static void updateQuiz(Quiz quiz) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String updateQuizSQL = "UPDATE Quiz SET quizName = ?, description = ? WHERE quizID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuizSQL);
            preparedStatement.setString(1, quiz.getQuizName());
            preparedStatement.setString(2, quiz.getDescription());
            preparedStatement.setInt(3, quiz.getQuizID());
            preparedStatement.executeUpdate();
        }
    }

    // Delete an existing quiz by ID
    public static boolean deleteQuiz(int quizID) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String deleteQuizSQL = "DELETE FROM Quiz WHERE quizID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuizSQL);
            preparedStatement.setInt(1, quizID);
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

	
}
