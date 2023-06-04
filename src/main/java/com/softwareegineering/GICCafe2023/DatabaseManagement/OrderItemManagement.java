package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderItemManagement extends Management<OrderItem> {
    @Override
    protected OrderItem mapRowToModel(ResultSet rs) throws SQLException {
        // Map the ResultSet to an OrderItem object
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getInt("order_item_id"));
        
        // Retrieve the order using the order_id from the result set
        int orderId = rs.getInt("order_id");
        Order order = getOrderById(orderId); // Implement this method to fetch the order from the database
        orderItem.setOrder(order);
        
        // Retrieve the product size using the product_size_id from the result set
        int productSizeId = rs.getInt("product_size_id");
        ProductSize productSize = getProductSizeById(productSizeId); // Implement this method to fetch the product size from the database
        orderItem.setProductSize(productSize);
        
        orderItem.setQuantity(rs.getInt("quantity"));
        
        // Set other order item properties based on the columns in the ResultSet
        return orderItem;
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, OrderItem orderItem) throws SQLException {
        // Set the statement parameters based on the order item properties
        stmt.setInt(1, orderItem.getOrder().getId());
        stmt.setInt(2, orderItem.getProductSize().getId());
        stmt.setInt(3, orderItem.getQuantity());

        if (!isAddOperation) {
            stmt.setInt(4, orderItem.getId());
        }
    }

    public int addOrderItem(OrderItem orderItem) {
        String query = "INSERT INTO order_item (order_id, product_size_id, quantity) VALUES (?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, orderItem.getOrder().getId());
            stmt.setInt(2, orderItem.getProductSize().getId());
            stmt.setInt(3, orderItem.getQuantity());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderItemId = generatedKeys.getInt(1);
                    System.out.println("Order item added successfully. ID: " + orderItemId);
                    return orderItemId;
                }
            }

            System.out.println("Failed to add order item");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 to indicate failure
    }

    public void updateOrderItem(OrderItem orderItem) {
        String query = "UPDATE order_item SET order_id = ?, product_size_id = ?, quantity = ? WHERE order_item_id = ?";
        update(orderItem, query);
    }

    public List<OrderItem> getAllOrderItems() {
        String query = "SELECT * FROM order_item";
        return getAll(query);
    }

    public OrderItem getOrderItemById(int orderItemId) {
        String query = "SELECT * FROM order_item WHERE order_item_id = ?";
        return getById(orderItemId, query);
    }

    public void deleteOrderItem(int orderItemId) {
        String query = "DELETE FROM order_item WHERE order_item_id = ?";

        delete(orderItemId, query);
    }

    // Implement methods to fetch Order and ProductSize by their IDs from the database
    private Order getOrderById(int orderId) {
        OrderManagement orderManagement = new OrderManagement();
        return orderManagement.getOrderById(orderId);
    }
    
    private ProductSize getProductSizeById(int productSizeId) {
        ProductSizeManagement productSizeManagement = new ProductSizeManagement();
        return productSizeManagement.getProductSizeById(productSizeId);
    }
}
