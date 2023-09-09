package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.User;
import util.DatabaseUtil;

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

 // retrieve all users from the database
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

    public static boolean updateUser(User user) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "UPDATE user SET username = ?, " +
                         
                         "email = ?, " +
                         "dateOfBirth = ?, " + // Corrected column name
                         "gender = ? " +    // Added a space here
                         "WHERE userid = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(4, user.getGender());
            
            preparedStatement.setString(5, user.getUserID());

            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the update was successful (at least one row affected)
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; // Return false if something went wrong or no rows were affected
    }


    public static boolean deleteUser(String userId) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM User WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the delete was successful (at least one row affected)
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully. Rows affected: " + rowsAffected);
                return true;
            } else {
                System.out.println("User deletion failed.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


	public static boolean createUser(User newUser, String role) {
		try (Connection connection = DatabaseUtil.getConnection()) {
	        String sql = "INSERT INTO User (UserID, Username, Password, Email, DateOfBirth, Gender, UserRole) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, UUID.randomUUID().toString().substring(0, 16)); // Generate a UUID
	        preparedStatement.setString(2, newUser.getUsername());
	        preparedStatement.setString(3, newUser.getPassword());
	        preparedStatement.setString(4, newUser.getEmail());

	        // Ensure you're using java.sql.Date, not casting
	        preparedStatement.setDate(5, new java.sql.Date(newUser.getDateOfBirth().getTime()));

	        preparedStatement.setString(6, newUser.getGender());
	        
	        
	        	
	        preparedStatement.setString(7, role); // You can set the role here
	        

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
	
	public static User getCurrentUserWithEmail(String email) throws SQLException {
		
		User currentUser = null;
		try (Connection connection = DatabaseUtil.getConnection()) {
	        String sql = "select userID, username, email, gender, bio, userRole, registrationDate from User\r\n"
	        		+ "where email = ?;";
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, email);
	        
	        ResultSet rs = preparedStatement.executeQuery();

	        while(rs.next()) {
	        	String userID = rs.getString("userID");
	        	String username = rs.getString("username");
	        	String userEmail = rs.getString("email");
	        	//Date dateOfBirth = rs.getDate("dateOfBirth");
	        	String gender = rs.getString("gender");
	        	String bio = rs.getString("bio");
	        	String userRole = rs.getString("userRole");
	        	Date registrationDate = rs.getDate("registrationDate");
	        	
	        	
	        	currentUser = new User(userID, username, userEmail, gender, bio, userRole, registrationDate);
	        }
	        
	        return currentUser;
		}
		
		
		
	}
	
	// get total number of users
    public static int getTotalUsers() {
        int totalUsers = 0;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM User");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                totalUsers = resultSet.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalUsers;
    }
    public static int getTotalUsersWithRole(String role) {
        int totalUsers = 0;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM User WHERE userRole=\""+ role +"\"");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                totalUsers = resultSet.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalUsers;
    }
    
    public static int getTotalStudents() {
    	int totalStudents =  getTotalUsersWithRole("Student");
       return totalStudents;
    }
    public static int getTotalTeachers() {
    	int totalTeachers =  getTotalUsersWithRole("Teacher");
       return totalTeachers;
    }
    public static int getTotalAdmins() {
    	int totalAdmins =  getTotalUsersWithRole("Admin");
       return totalAdmins;
    }


    public static boolean userExists(String userID) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) FROM user WHERE userID = ?"
             )) {
            preparedStatement.setString(1, userID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        return false; 
    }

}
