package com.softwareegineering.GICCafe2023.Model;

import java.sql.Date;

public class Product extends Model {
    private String name;
    private String description;
    private String type;
    private Size size;
    private Category category;
    private String image_url;
    private Date lastOrder;
    private int orderCount;


    
    public Product() {
    }
    public Product(int id, String name, String description, String type, Size size, Category category, String image_url,
            Date lastOrder, int orderCount) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
        this.size = size;
        this.category = category;
        this.image_url = image_url;
        this.lastOrder = lastOrder;
        this.orderCount = orderCount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public Date getLastOrder() {
        return lastOrder;
    }
    public void setLastOrder(Date lastOrder) {
        this.lastOrder = lastOrder;
    }
    public int getOrderCount() {
        return orderCount;
    }
    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }



    
}