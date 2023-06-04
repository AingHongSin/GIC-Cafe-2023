package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductSizeManagement extends Management<ProductSize> {
    @Override
    protected ProductSize mapRowToModel(ResultSet rs) throws SQLException {
        int id = rs.getInt("product_size_id");
        Size size = getSizeById(rs.getInt("size_id"));
        Product product = getProductById(rs.getInt("product_id"));

        return new ProductSize(id, size, product);
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, ProductSize productSize) throws SQLException {
        stmt.setInt(1, productSize.getSize().getId());
        stmt.setInt(2, productSize.getProduct().getId());

        if (!isAddOperation) {
            stmt.setInt(3, productSize.getId());
        }
    }

    public int addProductSize(ProductSize productSize) {
        String query = "INSERT INTO product_size (size_id, product_id) VALUES (?, ?)";
        return super.add(productSize, query);
    }

    public void updateProductSize(ProductSize productSize) {
        String query = "UPDATE product_size SET size_id = ?, product_id = ? WHERE product_size_id = ?";
        super.update(productSize, query);
    }

    public List<ProductSize> getAllProductSizes() {
        String query = "SELECT * FROM product_size";
        return super.getAll(query);
    }

    public ProductSize getProductSizeById(int id) {
        String query = "SELECT * FROM product_size WHERE  product_size_id = ?";
        return getById(id, query);
    }

    public void deleteProductSize(int id) {
        String query = "DELETE FROM product_size WHERE product_size_id = ?";
        super.delete(id, query);
    }

    private Size getSizeById(int id) {
        SizeManagement sizeManagement = new SizeManagement();
        return sizeManagement.getSizeById(id);
    }

    private Product getProductById(int id) {
        ProductManagement productManagement = new ProductManagement();
        return productManagement.getProductById(id);
    }

    public List<ProductSize> getProductSizesByProductId(int productId) {
        String query = "SELECT * FROM product_size WHERE product_id = ?";
        return query("Keyword", query, productId);
    }
}
