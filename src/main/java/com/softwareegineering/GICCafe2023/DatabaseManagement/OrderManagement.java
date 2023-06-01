package com.softwareegineering.GICCafe2023.DatabaseManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softwareegineering.GICCafe2023.Model.Order;

public class OrderManagement extends Management<Order> {
    @Override
    protected Order mapRowToModel(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setDateCreated(rs.getDate("date_created"));
        order.setStatus(rs.getString("status"));
        return order;
    }

    public void addOrder(Order order) {
        String query = "INSERT INTO `order` (user_id, date_created, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, order.getUserId());
            stmt.setDate(2, new java.sql.Date(order.getDateCreated().getTime()));
            stmt.setString(3, order.getStatus());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Order added successfully");
            } else {
                System.out.println("Failed to add order");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        String query = "UPDATE `order` SET user_id = ?, date_created = ?, status = ? WHERE order_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, order.getUserId());
            stmt.setDate(2, new java.sql.Date(order.getDateCreated().getTime()));
            stmt.setString(3, order.getStatus());
            stmt.setInt(4, order.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Order updated successfully");
            } else {
                System.out.println("Failed to update order");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersByUserId(int userId) {
        String query = "SELECT * FROM `order` WHERE user_id = ?";
        return query(String.valueOf(userId), query);
    }
    
}
