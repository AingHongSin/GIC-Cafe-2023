package com.softwareegineering.GICCafe2023.Model;

import java.util.ArrayList;
import java.util.List;


public class ProductSizeList extends Model{
    
    private Product product;
    private List<Size> sizeList;


    
    public ProductSizeList(int id, Product product, List<Size> sizeList) {
        super(id);
        this.product = product;
        this.sizeList = sizeList;
    }


    public ProductSizeList(Product product, List<Size> sizeList) {
        this.product = product;
        this.sizeList = sizeList;
    }

    
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public List<Size> getSizeList() {
        return sizeList;
    }
    public void setSizeList(ArrayList<Size> sizeList) {
        this.sizeList = sizeList;
    }
    
}
