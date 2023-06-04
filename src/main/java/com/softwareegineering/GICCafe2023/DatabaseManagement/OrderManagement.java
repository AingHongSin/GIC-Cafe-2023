package com.softwareegineering.GICCafe2023.DatabaseManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.softwareegineering.GICCafe2023.Model.Order;
import com.softwareegineering.GICCafe2023.Model.User;


public class OrderManagement extends Management<Order> {

    @Override
    protected Order mapRowToModel(ResultSet rs) throws SQLException {
        int id = rs.getInt("order_id");
        int userId = rs.getInt("user_id");
        User user = getUserById(userId);
        java.sql.Date dateCreated = rs.getDate("date_created");
        String status = rs.getString("status");

        return new Order(id, user, dateCreated, status);
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Order order) throws SQLException {
        stmt.setInt(1, order.getUser().getId());
        stmt.setDate(2, order.getDateCreated());
        stmt.setString(3, order.getStatus());

        if (!isAddOperation) {
            stmt.setInt(4, order.getId());
        }
    }

    public int addOrder(Order order) {
        String query = "INSERT INTO order (user_id, date_created, status) VALUES (?, ?, ?)";
        return add(order, query);
    }

    public void updateOrder(Order order) {
        String query = "UPDATE order SET user_id=?, date_created=?, status=? WHERE id=?";
        update(order, query);
    }

    public void deleteOrder(int id) {
        String query = "DELETE FROM order WHERE id=?";
        delete(id, query);
    }

    public List<Order> getAllOrders() {
        String query = "SELECT * FROM order";
        return getAll(query);
    }

    public Order getOrderById(int id) {
        String query = "SELECT * FROM order WHERE id=?";
        return getById(id, query);
    }

    public List<Order> searchOrders(String keyword) {
        String query = "SELECT * FROM order WHERE status LIKE ?";
        return query(keyword, query);
    }

    private User getUserById(int userId) {
        UserManagement userManagement = new UserManagement();
        return userManagement.getUserById(userId);
    }
}
