package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CourseModuleMaterial;
import util.DatabaseUtil;

public class CourseModuleMaterialDao {
    // Use DatabaseUtil.getConnection() directly
    

    // Rest of your code remains the same...
    
    // Add a new course module material
    public static boolean addMaterial(CourseModuleMaterial material) {
        String sql = "INSERT INTO CourseModuleMaterial (moduleID, materialName, materialDescription, filePath) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, material.getModuleID());
            statement.setString(2, material.getMaterialName());
            statement.setString(3, material.getMaterialDescription());
            statement.setString(4, material.getFilePath());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all materials for a specific module
    public List<CourseModuleMaterial> getMaterialsByModule(int moduleID) {
        List<CourseModuleMaterial> materials = new ArrayList<>();
        String sql = "SELECT * FROM CourseModuleMaterial WHERE moduleID = ?";

        Connection connection = DatabaseUtil.getConnection();
        try (
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, moduleID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int materialID = resultSet.getInt("materialID");
                String materialName = resultSet.getString("materialName");
                String materialDescription = resultSet.getString("materialDescription");
                String filePath = resultSet.getString("filePath");

                CourseModuleMaterial material = new CourseModuleMaterial(materialID, moduleID, materialName, materialDescription, filePath);
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    // Other methods (update, delete, etc.) can be added as needed
}
