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
        int userId = rs.getInt("user_id");
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

        return new User(userId, firstName, lastName, sex, role, dob, hireDate, age, username, password, imageUrl, served, lastLogin);
    }

    // @Override
    // protected void setStatementParams(boolean isAddOperation, PreparedStatement stmt, User model) throws SQLException {
    //     stmt.setString(1, model.getFirstName());
    //     stmt.setString(2, model.getLastName());
    //     stmt.setString(3, model.getSex());
    //     stmt.setString(4, model.getRole());
    //     stmt.setDate(5, new java.sql.Date(model.getDob().getTime()));
    //     stmt.setDate(6, model.getHireDate() != null ? new java.sql.Date(model.getHireDate().getTime()) : null);
    //     stmt.setInt(7, model.getAge());
    //     stmt.setString(8, model.getUsername());
    //     stmt.setString(9, model.getPassword());
    //     stmt.setString(10, model.getImage_url());
    //     stmt.setInt(11, model.getServed());
    //     stmt.setDate(12, model.getLastLogin() != null ? new java.sql.Date(model.getLastLogin().getTime()) : null);
    
    //     if (isAddOperation) {
    //         // Adjust parameters for add operation
    //         stmt.setNull(13, java.sql.Types.INTEGER);
    //     } else {
    //         // Adjust parameters for update operation
    //         stmt.setInt(13, model.getUserId());
    //     }
    // }
    
    
    
    
    
    
    
    public List<User> getAllUsers() {
        String query = "SELECT * FROM user WHERE role <> 'Unavailable'";
        return getAll(query);
    }
    

    public int addUser(User user) {
        String query = "INSERT INTO user (first_name, last_name, sex, role, dob, hire_date, age, username, password, image_url, served, last_login) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return add(user, query);
    }
    public void updateUser(User user) {
        String query = "UPDATE user SET first_name = ?, last_name = ?, sex = ?, role = ?, dob = ?, hire_date = ?, age = ?, " +
                "username = ?, password = ?, image_url = ?, served = ?, last_login = ? WHERE user_id = ?";
        update(user, query);
    }

    public void disableUser(int userId) {
        String query = "UPDATE user SET role = 'Unavailable' WHERE user_id = ?";
        disable(userId, query);
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

	@Override
	protected void setStatementParams(PreparedStatement stmt, User model) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setStatementParams'");
	}
}
