package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoryManagement extends Management<Category> {
    @Override
    protected Category mapRowToModel(ResultSet rs) throws SQLException {
        // Map the ResultSet to a Category object
        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setType(rs.getString("type"));
        // Set other category properties based on the columns in the ResultSet
        return category;
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Category category) throws SQLException {
        stmt.setString(1, category.getCategoryName());
        stmt.setString(2, category.getType());

        if (!isAddOperation) {
            stmt.setInt(3, category.getId());
        }
    }

    public int addCategory(Category category) {
        String query = "INSERT INTO category (category_name, type) VALUES (?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getType());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int categoryId = generatedKeys.getInt(1);
                    System.out.println("Category added successfully. ID: " + categoryId);
                    return categoryId;
                }
            }

            System.out.println("Failed to add category");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 to indicate failure
    }

    public void updateCategory(Category category) {
        String query = "UPDATE category SET category_name = ?, type = ? WHERE category_id = ?";
        update(category, query);
    }

    public List<Category> getAllCategories() {
        String query = "SELECT * FROM category";
        return getAll(query);
    }

    public Category getCategoryById(int categoryId) {
        String query = "SELECT * FROM category WHERE category_id = ?";
        return getById(categoryId, query);
    }

    public List<Category> queryCategories(String keyword) {
        String query = "SELECT * FROM category WHERE category_name LIKE ? type = ?";
        return query(keyword, query);
    }

    public void deleteCategory(int categoryId) {
        String query = "DELETE FROM category WHERE category_id = ?";

        delete(categoryId, query);
    }

    public List<Category> getAllDrinksCategory() {
        String query = "SELECT * FROM category WHERE type = 'Drink'";
        return getAll(query);
    }
    
    public List<Category> getAllFoodCatrgory() {
        String query = "SELECT * FROM category WHERE type = 'Food'";
        return getAll(query);
    }
    
}


