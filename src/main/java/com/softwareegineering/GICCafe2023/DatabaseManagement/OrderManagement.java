package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderManagement extends Management<Order> {
    @Override
    protected Order mapRowToModel(ResultSet rs) throws SQLException {
        // Map the ResultSet to an Order object
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        
        // Retrieve the user using the user_id from the result set
        int userId = rs.getInt("user_id");
        User user = getUserById(userId); // Implement this method to fetch the user from the database
        order.setUser(user);
        
        order.setDateCreated(rs.getDate("date_created"));
        order.setStatus(rs.getString("status"));
        
        // Retrieve the table using the table_id from the result set
        int tableId = rs.getInt("table_id");
        Table table = getTableById(tableId); // Implement this method to fetch the table from the database
        order.setTable(table);
        
        order.setTotalPrice(rs.getDouble("total_price"));
        
        // Set other order properties based on the columns in the ResultSet
        return order;
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Order order) throws SQLException {
        // Set the statement parameters based on the order properties
        stmt.setInt(1, order.getUser().getId());
        stmt.setDate(2, order.getDateCreated());
        stmt.setString(3, order.getStatus());
        stmt.setInt(4, order.getTable().getId());
        stmt.setDouble(5, order.getTotalPrice());

        if (!isAddOperation) {
            stmt.setInt(6, order.getId());
        }
    }

    public int addOrder(Order order) {
        String query = "INSERT INTO `order` (user_id, date_created, status, table_id, total_price) VALUES (?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, order.getUser().getId());
            stmt.setDate(2, order.getDateCreated());
            stmt.setString(3, order.getStatus());
            stmt.setInt(4, order.getTable().getId());
            stmt.setDouble(5, order.getTotalPrice());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    System.out.println("Order added successfully. ID: " + orderId);
                    return orderId;
                }
            }

            System.out.println("Failed to add order");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 to indicate failure
    }

    public void updateOrder(Order order) {
        String query = "UPDATE `order` SET user_id = ?, date_created = ?, status = ?, table_id = ?, total_price = ? WHERE order_id = ?";
        update(order, query);
    }

    public List<Order> getAllOrders() {
        String query = "SELECT * FROM `order`";
        return getAll(query);
    }

    public Order getOrderById(int orderId) {
        String query = "SELECT * FROM `order` WHERE order_id = ?";
        return getById(orderId, query);
    }

    public void deleteOrder(int orderId) {
        String query = "DELETE FROM `order` WHERE order_id = ?";
        delete(orderId, query);
    }

    // Implement methods to fetch User and Table by their respective IDs
    private User getUserById(int userId) {
        UserManagement userManagement = new UserManagement();
        return userManagement.getUserById(userId);
    }
    
    private Table getTableById(int tableId) {
        TableManagement tableManagement = new TableManagement();
        Table t = tableManagement.getTableById(tableId);
        return tableManagement.getTableById(tableId);
    }
}
