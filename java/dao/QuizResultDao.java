package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import models.QuizResult;
import util.DatabaseUtil;

public class QuizResultDao {
    

    

    // Insert a new quiz result record into the database
    public void insertQuizResult(QuizResult quizResult) throws SQLException {
        String sql = "INSERT INTO quizResult (quizResultID, quizID, userID, score, answerDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, UUID.randomUUID().toString().substring(0, 16));
            preparedStatement.setInt(2, quizResult.getQuizID());
            preparedStatement.setString(3, quizResult.getUserID());
            preparedStatement.setInt(4, quizResult.getScore());
            preparedStatement.setDate(5, new java.sql.Date(new Date().getTime()));

            preparedStatement.executeUpdate();
        }
    }

    // Retrieve quiz results for a specific user
    public List<QuizResult> getQuizResultsByUserID(String userID) throws SQLException {
        List<QuizResult> quizResults = new ArrayList<>();
        String sql = "SELECT quizResultID, quizID, userID, score, answerDate FROM quiz_results WHERE userID = ?";
        try (
        		Connection connection = DatabaseUtil.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String quizResultID = resultSet.getString("quizResultID");
                    int quizID = resultSet.getInt("quizID");
                    int score = resultSet.getInt("score");
                    Date answerDate = resultSet.getDate("answerDate");

                    QuizResult quizResult = new QuizResult(quizResultID, quizID, userID, score, answerDate);
                    quizResults.add(quizResult);
                }
            }
        }
        return quizResults;
    }
}
