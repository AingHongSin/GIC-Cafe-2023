package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.OrderItem;
import com.softwareegineering.GICCafe2023.Model.Product;
import com.softwareegineering.GICCafe2023.Model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderItemManagement extends Management<OrderItem> {

    @Override
    protected OrderItem mapRowToModel(ResultSet rs) throws SQLException {
        int id = rs.getInt("order_item_id");
        int orderId = rs.getInt("order_id");
        int productId = rs.getInt("product_id");
        int quantity = rs.getInt("quantity");

        // Retrieve the order and product objects
        OrderManagement orderManagement = new OrderManagement();
        ProductManagement productManagement = new ProductManagement();

        // Fetch the order and product from their respective management classes
        Order order = orderManagement.getOrderById(orderId);
        Product product = productManagement.getProductById(productId);

        return new OrderItem(id, order, product, quantity);
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, OrderItem model) throws SQLException {
        stmt.setInt(1, model.getOrder().getId());
        stmt.setInt(2, model.getProduct().getId());
        stmt.setInt(3, model.getQuantity());
    }

    public int add(OrderItem orderItem) {
        String query = "INSERT INTO order_item (order_id, product_id, quantity) VALUES (?, ?, ?)";
        return super.add(orderItem, query);
    }

    public void update(OrderItem orderItem) {
        String query = "UPDATE order_item SET order_id = ?, product_id = ?, quantity = ? WHERE id = ?";
        super.update(orderItem, query);
    }

    public void delete(int id) {
        String query = "DELETE FROM order_item WHERE id = ?";
        super.delete(id, query);
    }

    public void disable(int id) {
        String query = "UPDATE order_item SET disabled = true WHERE id = ?";
        super.disable(id, query);
    }

    public List<OrderItem> getAll() {
        String query = "SELECT * FROM order_item";
        return super.getAll(query);
    }

    public OrderItem getById(int id) {
        String query = "SELECT * FROM order_item WHERE id = ?";
        return super.getById(id, query);
    }

}
