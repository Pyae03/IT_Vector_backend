package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.AssignmentSubmission;
import util.DatabaseUtil;

public class AssignmentSubmissionDao {
    public static boolean addAssignmentSubmission(AssignmentSubmission submission) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO AssignmentSubmission (assignmentID, userID, filePath) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, submission.getAssignmentID());
            statement.setString(2, submission.getUserID());
            statement.setString(3, submission.getFilePath());
            statement.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<AssignmentSubmission> getSubmissionsByUserID(String userID) {
        List<AssignmentSubmission> submissions = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM AssignmentSubmission WHERE userID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                AssignmentSubmission submission = new AssignmentSubmission();
                submission.setSubmissionID(resultSet.getInt("submissionID"));
                submission.setAssignmentID(resultSet.getInt("assignmentID"));
                submission.setUserID(resultSet.getString("userID"));
                submission.setSubmissionDate(resultSet.getTimestamp("submissionDate"));
                submission.setFilePath(resultSet.getString("filePath"));
                submissions.add(submission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submissions;
    }


}
