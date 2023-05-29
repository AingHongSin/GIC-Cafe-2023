package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gic_coffee";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM user")) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setSex(rs.getString("sex"));
                user.setRole(rs.getString("role"));
                user.setDob(rs.getDate("dob"));
                user.setAge(rs.getInt("age"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static boolean loginUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0; // User found, login successful
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // User not found or invalid credentials
    }

    public User getUserById(int id) {
        User user = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?")) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setSex(rs.getString("sex"));
                    user.setRole(rs.getString("role"));
                    user.setDob(rs.getDate("dob"));
                    user.setAge(rs.getInt("age"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void addUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO user (first_name, last_name, sex, role, dob, age, username, password) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getSex());
            stmt.setString(4, user.getRole());
            stmt.setDate(5, new java.sql.Date(user.getDob().getTime()));
            stmt.setInt(6, user.getAge());
            stmt.setString(7, user.getUsername());
            stmt.setString(8, user.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE user SET first_name = ?, last_name = ?, sex = ?, role = ?, " +
                             "dob = ?, age = ?, username = ?, password = ? WHERE id = ?")) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getSex());
            stmt.setString(4, user.getRole());
            stmt.setDate(5, new java.sql.Date(user.getDob().getTime()));
            stmt.setInt(6, user.getAge());
            stmt.setString(7, user.getUsername());
            stmt.setString(8, user.getPassword());
            stmt.setInt(9, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
