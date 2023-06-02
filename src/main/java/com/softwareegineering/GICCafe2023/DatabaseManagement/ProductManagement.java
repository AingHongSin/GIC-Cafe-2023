package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement extends Management<Product> {

    @Override
    protected Product mapRowToModel(ResultSet rs) throws SQLException {
        int productId = rs.getInt("product_id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String type = rs.getString("type");
        int sizeId = rs.getInt("size_id");
        int categoryId = rs.getInt("category_id");
        String image_url = rs.getString("image_url");
        Date lastOrder = rs.getDate("last_order");
        int orderCount = rs.getInt("order_count");
    
        // Retrieve the corresponding Size and Category objects
        Size size = getSizeById(sizeId);
        Category category = getCategoryById(categoryId);
    
        return new Product(productId, name, description, type, size, category, image_url, lastOrder, orderCount);
    }
    

    @Override
    protected void setStatementParams(PreparedStatement stmt, Product product) throws SQLException {
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setString(3, product.getType());
        stmt.setInt(4, product.getSize().getId());
        stmt.setInt(5, product.getCategory().getId());
        stmt.setString(6, product.getImage_url());
        stmt.setDate(7, new java.sql.Date(product.getLastOrder().getTime()));
        stmt.setInt(8, product.getOrderCount());
    }

    private Size getSizeById(int sizeId) {
        SizeManagement sizeManagement = new SizeManagement();
        return sizeManagement.getSizeById(sizeId);
    }

    private Category getCategoryById(int categoryId) {
        CategoryManagemet categoryManagement = new CategoryManagemet();
        return categoryManagement.getCategoryById(categoryId);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM product WHERE category_id = ?";
        
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Product product = mapRowToModel(rs);
                productList.add(product);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productList;
    }
    

    public int addProduct(Product product) {
        String query = "INSERT INTO product (name, description, type, size_id, category_id, image_url, last_order, order_count) " +
               "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        return add(product, query);
    }

    public void updateProduct(Product product) {
        String query = "UPDATE product SET name = ?, description = ?, type = ?, size_id = ?, category_id = ?, image_url = ?, last_order = ?, order_count = ? WHERE product_id = ?";

        update(product, query);
    }

    public List<Product> getAllProducts() {
        String query = "SELECT * FROM product";
        return getAll(query);
    }

    public Product getProductById(int productId) {
        String query = "SELECT * FROM product WHERE product_id = ?";
        return getById(productId, query);
    }

    public List<Product> queryProducts(String keyword) {
        String query = "SELECT * FROM product WHERE name LIKE ? OR description LIKE ?";
        return query(keyword, query);
    }

}
