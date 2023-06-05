package com.softwareegineering.GICCafe2023.Model;

import java.sql.Timestamp;

public class Order extends Model {
    private User user;
    private Timestamp dateCreated;
    private String status;
    private Table table;
    private double totalPrice;


    public Order() {
    }

    public Order(int id, User user, Timestamp dateCreated, String status, Table table, double totalPrice) {
        super(id);
        this.user = user;
        this.dateCreated = dateCreated;
        this.status = status;
        this.table = table;
        this.totalPrice = totalPrice;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Table getTable() {
        return table;
    }
    public void setTable(Table table) {
        this.table = table;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}


