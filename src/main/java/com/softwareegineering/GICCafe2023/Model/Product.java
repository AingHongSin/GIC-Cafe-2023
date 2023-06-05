package com.softwareegineering.GICCafe2023.Model;

import java.sql.Timestamp;

public class Product extends Model {
    private String name;
    private String description;
    private Category category;
    private String imageUrl;
    private Timestamp lastOrder;

    public Product(int id, String name, String description, Category category, String imageUrl, Timestamp lastOrder) {
        super(id);
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.lastOrder = lastOrder;
    }
    public Product(int id, String name, String description, Category category, String imageUrl) {
        super(id);
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;

    }

    public Product() {
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
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Timestamp getLastOrder() {
        return lastOrder;
    }
    public void setLastOrder(Timestamp lastOrder) {
        this.lastOrder = lastOrder;
    }
    
}