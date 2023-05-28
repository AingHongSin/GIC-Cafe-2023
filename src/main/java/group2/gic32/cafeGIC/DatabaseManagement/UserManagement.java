package group2.gic32.cafeGIC.DatabaseManagement;

import group2.gic32.cafeGIC.Model.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gic_coffee";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void addUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "INSERT INTO user (id, first_name, last_name, sex, dob, age, username, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, user.getId());
                stmt.setString(2, user.getFirstName());
                stmt.setString(3, user.getLastName());
                stmt.setString(4, user.getSex());
                stmt.setDate(5, new java.sql.Date(user.getDob().getTime()));
                stmt.setInt(6, user.getAge());
                stmt.setString(7, user.getUsername());
                stmt.setString(8, user.getPassword());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM user";
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setSex(rs.getString("sex"));
                        user.setDob(rs.getDate("dob"));
                        user.setAge(rs.getInt("age"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        userList.add(user);
                    }
                }
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

    public static void updateUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "UPDATE user SET first_name = ?, last_name = ?, sex = ?, dob = ?, age = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getSex());
                stmt.setDate(4, new java.sql.Date(user.getDob().getTime()));
                stmt.setInt(5, user.getAge());
                stmt.setInt(6, user.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}