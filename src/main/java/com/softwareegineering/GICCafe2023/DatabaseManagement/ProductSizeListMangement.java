package com.softwareegineering.GICCafe2023.DatabaseManagement;

import java.util.ArrayList;
import java.util.List;

import com.softwareegineering.GICCafe2023.Model.*;

public class ProductSizeListMangement {
    
    private ProductManagement productManagement;
    private SizeManagement sizeManagement;
    private ProductSizeManagement productSizeManagement;

    public ProductSizeListMangement() {
        this.productManagement = new ProductManagement();
        this.sizeManagement = new SizeManagement();
        this.productSizeManagement = new ProductSizeManagement();
    }

    public ArrayList<ProductSizeList> getProductWithListSizeByProductList(List<Product> products) {
        ArrayList<ProductSizeList> productSizeDisplayList = new ArrayList<ProductSizeList>();

        for (Product product : products) {
            List<Size> sizes = sizeManagement.getAllSizeOfProduct(product.getId());
            ProductSizeList productSizesViewModel = new ProductSizeList(product, sizes);
            productSizeDisplayList.add(productSizesViewModel);
        }
        
        return productSizeDisplayList;
    } 

    public ArrayList<ProductSizeList> getAllProductWithListSize() {
        ArrayList<ProductSizeList> allProductWithListSize = new ArrayList<ProductSizeList>();
        List<Product> products = productManagement.getAllProducts();

        for (Product product : products) {
            List<Size> sizes = sizeManagement.getAllSizeOfProduct(product.getId());
            ProductSizeList productSizesViewModel = new ProductSizeList(product, sizes);

            allProductWithListSize.add(productSizesViewModel);
        }
        
        return allProductWithListSize;
    } 

    public void deleteProductAndAllSize(Product product) {
        List<ProductSize> productSizes = productSizeManagement.getProductSizesByProductId(product.getId());

        ArrayList<Size> sizes = new ArrayList<Size>();

        for (ProductSize productSize : productSizes) {
            sizes.add(productSize.getSize());
        }
        productSizeManagement.deleteProductSize(productSizes.get(0).getId());

        for (Size size : sizes) {
            sizeManagement.deleteSize(size.getId());
        }

        productManagement.deleteProduct(product.getId());

    }

}
