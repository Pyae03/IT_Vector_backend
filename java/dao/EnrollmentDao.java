package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

import models.Enrollment;
import util.DatabaseUtil;

public class EnrollmentDao {
    
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM enrollment");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int enrollmentID = resultSet.getInt("enrollmentID");
                String studentID = resultSet.getString("studentID");
                int courseID = resultSet.getInt("courseID");
                Timestamp enrollmentDate = resultSet.getTimestamp("enrollmentDate");

                Enrollment enrollment = new Enrollment(enrollmentID, studentID, courseID, enrollmentDate);
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions here
        }

        return enrollments;
    }
    
    public boolean createEnrollment(Enrollment enrollment) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO enrollment (studentID, courseID) VALUES (?, ?)"
             )) {

            preparedStatement.setString(1, enrollment.getStudentID());
            preparedStatement.setInt(2, enrollment.getCourseID());
            

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
    }

    
    public boolean deleteEnrollment(int enrollmentID) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM enrollment WHERE enrollmentID = ?"
             )) {

            preparedStatement.setInt(1, enrollmentID);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
    }
}

