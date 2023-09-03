package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DatabaseUtil;
import models.QuizOption;

public class QuizOptionDao {

    // Create a new quiz option
    public static void createQuizOption(QuizOption option) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String insertOptionSQL = "INSERT INTO QuizOption (questionID, optionText, isCorrect) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertOptionSQL);
            preparedStatement.setInt(1, option.getQuestionID());
            preparedStatement.setString(2, option.getOptionText());
            preparedStatement.setBoolean(3, option.isCorrect());
            preparedStatement.executeUpdate();
        }
    }

    // Read all quiz options for a specific question
    public static List<QuizOption> getAllOptionsByQuestionID(int questionID) throws SQLException {
        List<QuizOption> options = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            String selectOptionsSQL = "SELECT * FROM QuizOption WHERE questionID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectOptionsSQL);
            preparedStatement.setInt(1, questionID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int quizOptionID = resultSet.getInt("quizOptionID");
                String optionText = resultSet.getString("optionText");
                boolean isCorrect = resultSet.getBoolean("isCorrect");

                QuizOption option = new QuizOption(quizOptionID, questionID, optionText, isCorrect);
                options.add(option);
            }
        }
        return options;
    }

    // Update an existing quiz option
    public static void updateQuizOption(QuizOption option) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String updateOptionSQL = "UPDATE QuizOption SET optionText = ?, isCorrect = ? WHERE quizOptionID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateOptionSQL);
            preparedStatement.setString(1, option.getOptionText());
            preparedStatement.setBoolean(2, option.isCorrect());
            preparedStatement.setInt(3, option.getOptionID());
            preparedStatement.executeUpdate();
        }
    }

    // Delete an existing quiz option by ID
    public static void deleteQuizOption(int quizOptionID) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String deleteOptionSQL = "DELETE FROM QuizOption WHERE quizOptionID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteOptionSQL);
            preparedStatement.setInt(1, quizOptionID);
            preparedStatement.executeUpdate();
        }
    }
}
