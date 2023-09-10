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


  
    
    // add new course module material
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

    // get all materials for a specific module
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

    
    public static CourseModuleMaterial getMaterialByID(int materialID) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseModuleMaterial material = null;

        try {

            connection = DatabaseUtil.getConnection();

            String query = "SELECT * FROM CourseModuleMaterial WHERE materialID=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, materialID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                
                material = new CourseModuleMaterial();
                material.setMaterialID(Integer.parseInt(resultSet.getString("materialID")) );
                material.setModuleID(Integer.parseInt(resultSet.getString("moduleID")));
                material.setMaterialName(resultSet.getString("materialName"));
                material.setMaterialDescription(resultSet.getString("materialDescription"));
                material.setFilePath(resultSet.getString("filePath"));
                
            }
        } catch(Exception e) {
        	
        }

        return material;
    }
    
    //my update, delete, etc.
    
 
    public static boolean deleteMaterial(int materialID) {
        String sql = "DELETE FROM CourseModuleMaterial WHERE materialID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, materialID);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    
    
}
