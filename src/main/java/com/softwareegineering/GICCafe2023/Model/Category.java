package com.softwareegineering.GICCafe2023.Model;

public class Category extends Model {
    private String categoryName;
    private String type;
    


    public Category() {
        // Default constructor
    }

    public Category(int id, String categoryName, String type) {
        super(id);
        this.categoryName = categoryName;
        this.type = type;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
