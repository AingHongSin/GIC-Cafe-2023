package com.softwareegineering.GICCafe2023.Model;

import java.util.Date;

public class Order extends Model {
    private int userId;
    private Date dateCreated;
    private String status;

    
    public Order() { }

    public Order(int id, int userId, Date dateCreated, String status) {
        super(id);
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.status = status;
    }
    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    


}