package com.softwareegineering.GICCafe2023.DatabaseManagement;

import java.sql.*;
import java.util.List;


import com.softwareegineering.GICCafe2023.Model.Category;
import com.softwareegineering.GICCafe2023.Model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductManagement extends Management<Product> {

    @Override
    protected Product mapRowToModel(ResultSet rs) throws SQLException {
        int id = rs.getInt("product_id");
        String name = rs.getString("name");
        String description = rs.getString("description");

        int categoryId = rs.getInt("category_id");
        Category category = getCategoryById(categoryId);

        String imageUrl = rs.getString("image_url");
        Timestamp lastOrder = rs.getTimestamp("last_order");


        return new Product(id, name, description, category, imageUrl, lastOrder);
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Product product) throws SQLException {
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setInt(3, product.getCategory().getId());
        stmt.setString(4, product.getImageUrl());
        stmt.setTimestamp(5, product.getLastOrder());

        if (!isAddOperation) {
            stmt.setInt(6, product.getId());
        }
    }


    // MARK: PUBLIC-FUNCTION
    public int addProduct(Product product) {
        String query = "INSERT INTO product (name, description, category_id, image_url, last_order) VALUES (?, ?, ?, ?, ?)";
        return add(product, query);
    }

    public void updateProduct(Product product) {
        String query = "UPDATE product SET name=?, description=?, category_id=?, image_url=?, last_order=? WHERE product_id=?";
        update(product, query);
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM product WHERE product_id=?";
        delete(id, query);
    }

    public List<Product> getAllProducts() {
        String query = "SELECT * FROM product";
        return getAll(query);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        String query = "SELECT * FROM product WHERE category_id=?";
        return query("", query, String.valueOf(categoryId));
    }

    public Product getProductById(int id) {
        String query = "SELECT * FROM product WHERE product_id=?";
        return getById(id, query);
    }

    public List<Product> searchProducts(String keyword) {
        String query = "SELECT * FROM product WHERE name LIKE ?";
        return query(keyword, query);
    }

    public List<Product> getAllFood() {
        String query = "SELECT p.* FROM product p INNER JOIN category c ON p.category_id = c.category_id WHERE c.type = 'Food'";
        return query("Keyword", query);
    }
    
    public List<Product> getAllDrink() {
        String query = "SELECT p.* FROM product p INNER JOIN category c ON p.category_id = c.category_id WHERE c.type = 'Drink'";
        return query("Keyword", query);
    }

    // MARK: PRIVATE-FUNCTION
    private Category getCategoryById(int categoryId) {
        CategoryManagement categoryManagement = new CategoryManagement();
        return categoryManagement.getCategoryById(categoryId);
    }


}
