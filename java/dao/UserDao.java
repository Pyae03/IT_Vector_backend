package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Util.DatabaseUtil;
import models.User;

public class UserDao {
    

    // SQL queries for CRUD operations

    public User createUser(User user) {
		return user;
        // Implement user creation
    }

    public User getUserById(int id) {
		return null;
        // Implement user retrieval by ID
    }

 // Method to retrieve all users from the database
    public List<User> getAllUsers() {
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