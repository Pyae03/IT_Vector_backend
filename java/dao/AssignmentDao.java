package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DatabaseUtil;

public class AssignmentDao {

	public static boolean createAssignment(String assignmentName, String assignmentFilePath, int moduleID) {
        String sql = "INSERT INTO Assignment (assignmentName, assignmentFilePath, moduleID) VALUES (?, ?, ?)";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, assignmentName);
            preparedStatement.setString(2, assignmentFilePath);
            preparedStatement.setInt(3, moduleID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
}
