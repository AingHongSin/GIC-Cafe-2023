package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;

import java.sql.*;
import java.util.List;

public class TableManagement extends Management<Table> {

    @Override
    protected Table mapRowToModel(ResultSet rs) throws SQLException {
        Table table = new Table();
        table.setId(rs.getInt("table_id"));
        table.setTableNumber(rs.getInt("tableNumber"));
        table.setTableType(rs.getString("table_type"));
        table.setTableStatus(rs.getString("table_statue"));
        return table;
    }
        

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Table table) throws SQLException {
        stmt.setInt(1, table.getTableNumber());
        stmt.setString(2, table.getTableType());
        stmt.setString(3, table.getTableStatus());

        if (!isAddOperation) {
            stmt.setInt(4, table.getId());
        }
    }

    public int addTable(Table table) {
        String query = "INSERT INTO `table` (tableNumber, table_type, table_statue) VALUES (?, ?, ?)";
        return add(table, query);
    }

    public void updateTable(Table table) {
        String query = "UPDATE `table` SET tableNumber = ?, table_type = ?, table_statue = ? WHERE table_id = ?";
        update(table, query);
    }

    public List<Table> getAllTables() {
        String query = "SELECT * FROM `table`";
        return getAll(query);
    }

    public Table getTableById(int tableId) {
        String query = "SELECT * FROM `table` WHERE table_id = ?";
        return getById(tableId, query);
    }

    public void deleteTable(int tableId) {
        String query = "DELETE FROM `table` WHERE table_id = ?";
        delete(tableId, query);
    }

    // Implement methods to fetch Order and ProductSize by their IDs from the database
    private OrderItem getOrderItemById(int orderItemId) {
        OrderItemManagement orderItemManagement = new OrderItemManagement();
        return orderItemManagement.getOrderItemById(orderItemId);
    }
}
