package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserManagement extends Management<User> {
    @Override
    protected User mapRowToModel(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String sex = rs.getString("sex");
        String role = rs.getString("role");
        Date dob = rs.getDate("dob");
        int age = rs.getInt("age");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String imageUrl = rs.getString("image_url");
        return new User(userId, firstName, lastName, sex, role, dob, age, username, password, imageUrl);
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        return getAll(query);
    }
    
    @Override
    protected void setStatementParams(PreparedStatement stmt, User model) throws SQLException {
        stmt.setString(1, model.getFirstName());
        stmt.setString(2, model.getLastName());
        stmt.setString(3, model.getSex());
        stmt.setString(4, model.getRole());
        stmt.setDate(5, new java.sql.Date(model.getDob().getTime()));
        stmt.setInt(6, model.getAge());
        stmt.setString(7, model.getUsername());
        stmt.setString(8, model.getPassword());
        stmt.setString(9, model.getImage_url());
    }


    public int addUser(User user) {
        String query = "INSERT INTO user (first_name, last_name, sex, role, dob, age, username, password, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return add(user, query);
    }

    public void updateUser(User user) {
        String query = "UPDATE user SET first_name = ?, last_name = ?, sex = ?, role = ?, dob = ?, age = ?, " +
                "username = ?, password = ?, image_url = ? WHERE user_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getSex());
            stmt.setString(4, user.getRole());
            stmt.setDate(5, new java.sql.Date(user.getDob().getTime()));
            stmt.setInt(6, user.getAge());
            stmt.setString(7, user.getUsername());
            stmt.setString(8, user.getPassword());
            stmt.setString(9, user.getImage_url());
            stmt.setInt(10, user.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("Failed to update user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByID(int id) {
        String query = "SELECT * FROM user WHERE user_id = ?";
        return getById(id, query);
    }

    public List<User> searchUser(String keyword) {
        String query = "SELECT * FROM user WHERE username LIKE ?";
        return query(keyword, query);
    }

    public User loginUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        User user = null;
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = mapRowToModel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
