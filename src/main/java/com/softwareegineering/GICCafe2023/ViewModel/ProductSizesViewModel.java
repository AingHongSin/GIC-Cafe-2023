package com.softwareegineering.GICCafe2023.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.softwareegineering.GICCafe2023.Model.*;

public class ProductSizesViewModel extends Model{
    
    private Product product;
    private List<Size> sizeList;


    
    public ProductSizesViewModel(Product product, List<Size> sizeList) {
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
