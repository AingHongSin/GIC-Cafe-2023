package com.softwareegineering.GICCafe2023.Model;

public class ProductSize extends Model {
    private Size size;
    private Product product;
    
    public ProductSize() {
    }

    public ProductSize(int id, Size size, Product product) {
        super(id);
        this.size = size;
        this.product = product;
    }

    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }



}
