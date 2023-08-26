package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Util.DatabaseUtil;
import models.User;

public class UserDao {
    

    // SQL queries for CRUD operations

	public static boolean createUser(User user) {
	    try (Connection connection = DatabaseUtil.getConnection()) {
	        String sql = "INSERT INTO User (UserID, Username, Password, Email, DateOfBirth, Gender, UserRole) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, UUID.randomUUID().toString().substring(0, 16)); // Generate a UUID
	        preparedStatement.setString(2, user.getUsername());
	        preparedStatement.setString(3, user.getPassword());
	        preparedStatement.setString(4, user.getEmail());
	        
	        // Ensure you're using java.sql.Date, not casting
	        preparedStatement.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime())); 
	        
	        preparedStatement.setString(6, user.getGender());
	        preparedStatement.setString(7, "Student"); // You can set the role here
	        
	        int rowsAffected = preparedStatement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("User created successfully. Rows affected: " + rowsAffected);
	            return true;
	        } else {
	            System.out.println("User creation failed.");
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    public User getUserById(int id) {
		return null;
        // Implement user retrieval by ID
    }

 // Method to retrieve all users from the database
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getString("userID"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                user.setGender(resultSet.getString("gender"));
                user.setBio(resultSet.getString("bio"));
                user.setUserRole(resultSet.getString("userRole"));
                user.setRegistrationDate(resultSet.getTimestamp("registrationDate"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUser(User user) {
		return false;
        // Implement user update
    }

    public boolean deleteUser(int id) {
		return false;
        // Implement user deletion
    }

	
}
