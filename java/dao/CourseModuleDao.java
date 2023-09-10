package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CourseModule;
import models.CourseModuleMaterial;
import util.DatabaseUtil;

public class CourseModuleDao {

    // Create a new module
    public boolean createModule(int courseID, String moduleTitle) {
        String sql = "INSERT INTO CourseModule (courseID, moduleTitle) VALUES (?, ?)";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, moduleTitle);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read modules for a specific course
    public List<CourseModule> getModulesByCourse(int courseID) {
        List<CourseModule> modules = new ArrayList<>();
        String sql = "SELECT * FROM CourseModule WHERE courseID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int moduleID = resultSet.getInt("moduleID");
                String moduleTitle = resultSet.getString("moduleTitle");
                
                // query from courseMoudleMaterial
                // all moduleMaterials will be assign to specific module
                CourseModuleMaterialDao courseModuleMaterial_dao = new CourseModuleMaterialDao();
                List<CourseModuleMaterial> allMaterials = courseModuleMaterial_dao.getMaterialsByModule(moduleID);
                
                CourseModule courseModule = new CourseModule(moduleID, courseID, moduleTitle);
                courseModule.setCourseModuleMaterialList(allMaterials);
               
                modules.add(courseModule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    // Update module title
    public boolean updateModuleTitle(int moduleID, String newTitle) {
        String sql = "UPDATE CourseModule SET moduleTitle = ? WHERE moduleID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, newTitle);
            preparedStatement.setInt(2, moduleID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a module
    public static boolean deleteModule(int moduleID) {
        String sql = "DELETE FROM CourseModule WHERE moduleID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, moduleID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // for assignment teacher
    public List<CourseModule> getModulesByCourseForAssignment(int courseID) {
        List<CourseModule> modules = new ArrayList<>();
        String sql = "SELECT * FROM CourseModule WHERE courseID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int moduleID = resultSet.getInt("moduleID");
                String moduleTitle = resultSet.getString("moduleTitle");
                
                CourseModule courseModule = new CourseModule(moduleID, courseID, moduleTitle);
                
                System.out.println("module: " + courseModule.toString());
                modules.add(courseModule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }
    
    public static CourseModule getModuleByID(int moduleID) {
        CourseModule module = null;
        String sql = "SELECT * FROM CourseModule WHERE moduleID = ?";
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, moduleID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int courseID = resultSet.getInt("courseID");
                String moduleTitle = resultSet.getString("moduleTitle");
                
                module = new CourseModule(moduleID, courseID, moduleTitle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return module;
    }
    
}
