package com.softwareegineering.GICCafe2023.Model;

public class OrderItem extends Model {
    private Order order;
    private ProductSize productSize;
    private int quantity;


    public OrderItem() {
    }

    public OrderItem(int id, Order order, ProductSize productSize, int quantity) {
        super(id);
        this.order = order;
        this.productSize = productSize;
        this.quantity = quantity;
        
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

}
