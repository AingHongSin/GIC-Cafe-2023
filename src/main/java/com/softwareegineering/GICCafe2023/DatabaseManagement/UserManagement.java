package com.softwareegineering.GICCafe2023.DatabaseManagement;

import com.softwareegineering.GICCafe2023.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;


public class UserManagement extends Management<User> {

    @Override
    protected User mapRowToModel(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String sex = rs.getString("sex");
        String role = rs.getString("role");
        Date dob = rs.getDate("dob");
        Date hireDate = rs.getDate("hire_date");
        int age = rs.getInt("age");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String imageUrl = rs.getString("image_url");
        int served = rs.getInt("served");
        Date lastLogin = rs.getDate("last_login");

        return new User(id, firstName, lastName, sex, role, dob, hireDate, age, username, password, imageUrl, served, lastLogin);
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, User model) throws SQLException {
        stmt.setString(1, model.getFirstName());
        stmt.setString(2, model.getLastName());
        stmt.setString(3, model.getSex());
        stmt.setString(4, model.getRole());
        stmt.setDate(5, model.getDob());
        stmt.setDate(6, model.getHireDate());
        stmt.setInt(7, model.getAge());
        stmt.setString(8, model.getUsername());
        stmt.setString(9, model.getPassword());
        stmt.setString(10, model.getImageUrl());
        stmt.setInt(11, model.getServed());
        stmt.setDate(12, model.getLastLogin());

        // Additional parameters for add operation
        if (!isAddOperation) {
            stmt.setInt(13, model.getId());
        }
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        return getAll(query);
    }

    public int addUser(User user) {
        String query = "INSERT INTO user (first_name, last_name, sex, role, dob, hire_date, age, username, password, image_url, served, last_login) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return add(user, query);
    }

    public void updateUser(User user) {
        String query = "UPDATE user SET first_name=?, last_name=?, sex=?, role=?, dob=?, hire_date=?, age=?, username=?, password=?, " +
                "image_url=?, served=?, last_login=? WHERE id=?";
        update(user, query);
    }

    public void deleteUser(int id) {
        String query = "DELETE FROM user WHERE id=?";
        delete(id, query);
    }

    public void disableUser(int id) {
        String query = "UPDATE user SET role='Unavailable' WHERE id=?";
        disable(id, query);
    }

    public User login(String username, String password) {
        String query = "SELECT * FROM user WHERE username=? AND password=?";
        List<User> users = query("keyword", query, username, password);

        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id=?";
        return getById(id, query);
    }

    public List<User> queryUsers(String keyword) {
        String query = "SELECT * FROM user WHERE first_name LIKE ? OR last_name LIKE ?";
        return query(keyword, keyword, query);
    }
}
