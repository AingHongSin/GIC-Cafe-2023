package com.softwareegineering.GICCafe2023.Model;

public class OrderItem extends Model {
    private Order order;
    private ProductSize productSize;
    private int quantity;
    private boolean cream;
    private double sugar;
    private String note;


    public OrderItem() {
    }

    public OrderItem(int id, Order order, ProductSize productSize, int quantity, boolean cream, double sugar,
            String note) {
        super(id);
        this.order = order;
        this.productSize = productSize;
        this.quantity = quantity;
        this.cream = cream;
        this.sugar = sugar;
        this.note = note;
    }

    
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public ProductSize getProductSize() {
        return productSize;
    }
    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public boolean isCream() {
        return cream;
    }
    public void setCream(boolean cream) {
        this.cream = cream;
    }
    public double getSugar() {
        return sugar;
    }
    public void setSugar(double sugar) {
        this.sugar = sugar;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
