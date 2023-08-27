package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Course;
import util.DatabaseUtil;

public class CourseDao {
    private Connection connection;

//    public CourseDao(Connection connection) {
//        this.connection = connection;
//    }

    // Insert a new course
    public boolean addCourse(Course course) {
		return false;
        
    }

    // Update an existing course
    public boolean updateCourse(Course course) {
		return false;
        
    }

    // Delete a course by its ID
    public boolean deleteCourse(int courseId) {
		return false;
        
    }

    // Retrieve a course by its ID
    public Course getCourseById(int courseId) {
		return null;
        
    }

    // Retrieve all courses
    public static List<Course> getAllCourses() {
    	
    	List<Course> courses = null;
		try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Course");
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Course course = new Course();
                    course.setCourseId(resultSet.getInt("courseID"));
                    course.setCourseName(resultSet.getString("courseName"));
                    course.setCourseCode(resultSet.getString("courseCode"));
                    course.setFilePath(resultSet.getString("filePath"));
                    course.setTeacher(resultSet.getString("teacher"));
                    course.setStartDate(resultSet.getDate("startDate"));
                    course.setEndDate(resultSet.getDate("endDate"));

                    courses.add(course);
                }
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
			System.out.println("all courses: " + courses);
            return courses;
    }
}

