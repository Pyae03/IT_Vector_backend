package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import util.DatabaseUtil;

public class CategoryDao {
    private static final String INSERT_CATEGORY_SQL = "INSERT INTO Category (CategoryName) VALUES (?)";
    private static final String SELECT_CATEGORY_BY_NAME_SQL = "SELECT * FROM Category WHERE CategoryName = ?";
    private static final String SELECT_ALL_CATEGORIES_SQL = "SELECT * FROM Category";
    private static final String UPDATE_CATEGORY_SQL = "UPDATE Category SET CategoryName = ? WHERE CategoryName = ?";
    private static final String DELETE_CATEGORY_SQL = "DELETE FROM Category WHERE CategoryName = ?";
    
    public void addCategory(String categoryName) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
            preparedStatement.setString(1, categoryName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryByName(String categoryName) {
        Category category = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME_SQL)) {
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new Category(resultSet.getString("CategoryName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String categoryName = resultSet.getString("CategoryName");
                categories.add(new Category(categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void updateCategory(String oldCategoryName, String newCategoryName) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL)) {
            preparedStatement.setString(1, newCategoryName);
            preparedStatement.setString(2, oldCategoryName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String categoryName) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_SQL)) {
            preparedStatement.setString(1, categoryName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


