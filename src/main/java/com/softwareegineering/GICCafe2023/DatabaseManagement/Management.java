package com.softwareegineering.GICCafe2023.DatabaseManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Management<T> {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gic_coffee";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    protected abstract T mapRowToModel(ResultSet rs) throws SQLException;
    protected abstract void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, T model) throws SQLException;

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Object... params) throws SQLException {
        int parameterCount = params.length;

        for (int i = 0; i < parameterCount; i++) {
            Object param = params[i];
            int parameterIndex = i + 1;
    
            if (param instanceof String) {
                stmt.setString(parameterIndex, (String) param);
            } else if (param instanceof Integer) {
                stmt.setInt(parameterIndex, (Integer) param);
            } else if (param instanceof Double) {
                stmt.setDouble(parameterIndex, (Double) param);
            } else if (param instanceof Float) {
                stmt.setFloat(parameterIndex, (Float) param);
            } else if (param instanceof Boolean) {
                stmt.setBoolean(parameterIndex, (Boolean) param);
            } else if (param instanceof Date) {
                stmt.setDate(parameterIndex, (Date) param);
            } else {
                stmt.setObject(parameterIndex, param);
            }
        }
            // Parameter specific to the add operation
        if (isAddOperation) {
            
        }
    }

    public int add(T model, String query) {
        int generatedId = -1;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setStatementParams(true, stmt, model);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        System.out.println("Record added successfully with ID: " + generatedId);
                    }
                }
            } else {
                System.out.println("Failed to add the record");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    
    }

    public void update(T model, String query) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            setStatementParams(false, stmt, model);
            stmt.executeUpdate();
            
            System.out.println("Record updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id, String query) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully");
            } else {
                System.out.println("No record found with the given ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disable(int id, String query) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Disabled successfully");
            } else {
                System.out.println("No record found with the given ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<T> getAll(String query) {
        List<T> modelList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                T model = mapRowToModel(rs);
                modelList.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelList;
    }

    public T getById(int id, String query) {
        T model = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    model = mapRowToModel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public List<T> query(String keyword, String query, Object... params) {
        List<T> modelList = new ArrayList<>();
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            int parameterCount = params.length;
    
            for (int i = 0; i < parameterCount; i++) {
                Object param = params[i];
                int parameterIndex = i + 1;
    
                if (param instanceof String) {
                    stmt.setString(parameterIndex, (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(parameterIndex, (Integer) param);
                } else if (param instanceof Double) {
                    stmt.setDouble(parameterIndex, (Double) param);
                } else if (param instanceof Float) {
                    stmt.setFloat(parameterIndex, (Float) param);
                } else if (param instanceof Boolean) {
                    stmt.setBoolean(parameterIndex, (Boolean) param);
                } else if (param instanceof Date) {
                    stmt.setDate(parameterIndex, (Date) param);
                } else {
                    stmt.setObject(parameterIndex, param);
                }
            }
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    T model = mapRowToModel(rs);
                    modelList.add(model);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return modelList;
    }
}
