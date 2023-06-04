package com.softwareegineering.GICCafe2023.Model;

public class OrderItem extends Model {
    private Order order;
    private Product product;
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(int id, Order order, Product product, int quantity) {
        super(id);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
    
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
