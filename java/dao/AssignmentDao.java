package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Assignment;
import util.DatabaseUtil;

public class AssignmentDao {

    // Create an assignment
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

    // Read all assignments for a module
    public static List<Assignment> getAssignmentsByModule(int moduleID) {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignment WHERE moduleID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, moduleID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int assignmentID = resultSet.getInt("assignmentID");
                String assignmentName = resultSet.getString("assignmentName");
                String assignmentFilePath = resultSet.getString("assignmentFilePath");

                Assignment assignment = new Assignment(assignmentID, assignmentName, assignmentFilePath, moduleID);
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    // Update an assignment
    public static boolean updateAssignment(int assignmentID, String assignmentName, String assignmentFilePath, int moduleID) {
        String sql = "UPDATE Assignment SET assignmentName = ?, assignmentFilePath = ?, moduleID = ? WHERE assignmentID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, assignmentName);
            preparedStatement.setString(2, assignmentFilePath);
            preparedStatement.setInt(3, moduleID);
            preparedStatement.setInt(4, assignmentID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an assignment
    public static boolean deleteAssignment(int assignmentID) {
        String sql = "DELETE FROM Assignment WHERE assignmentID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, assignmentID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
