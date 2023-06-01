package com.softwareegineering.GICCafe2023.Model;

public class Product extends Model {
    private String name;
    private String description;
    private String type;
    private Size size;
    private Category category;
    private String image_url;



    public Product(int productId, String name, String description, String type, Size size, Category category, String image_url ) {
        super(productId);
        this.name = name;
        this.description = description;
        this.type = type;
        this.size = size;
        this.category = category;
        this.image_url = image_url;
    }

    public Product() {
        // Default constructor
    }

    // Getters and Setters

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
    
}

