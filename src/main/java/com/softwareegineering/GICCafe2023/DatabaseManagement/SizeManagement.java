package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.Size;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SizeManagement extends Management<Size> {

    @Override
    protected Size mapRowToModel(ResultSet rs) throws SQLException {
        Size size = new Size();
        size.setId(rs.getInt("size_id"));
        size.setSizeName(rs.getString("size_name"));
        size.setPrice(rs.getDouble("price"));
        return size;
    }

    @Override
    protected void setStatementParams(PreparedStatement stmt, Size size) throws SQLException {
        stmt.setString(1, size.getSizeName());
        stmt.setDouble(2, size.getPrice());
    }
    
    public void addSize(Size size) {
        String query = "INSERT INTO size (size_name, price) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, size.getSizeName());
            stmt.setDouble(2, size.getPrice());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Size added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSize(Size size) {
        String query = "UPDATE size SET size_name = ?, price = ? WHERE size_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, size.getSizeName());
            stmt.setDouble(2, size.getPrice());
            stmt.setInt(3, size.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Size updated successfully");
            } else {
                System.out.println("No size found with the given ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Size> getAllSizes() {
        String query = "SELECT * FROM size";
        return getAll(query);
    }

    public Size getSizeById(int id) {
        String query = "SELECT * FROM size WHERE size_id = ?";
        return getById(id, query);
    }

    public List<Size> querySizes(String keyword) {
        String query = "SELECT * FROM size WHERE size_name LIKE ?";
        return query(keyword, query);
    }
}
