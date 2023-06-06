package com.softwareegineering.GICCafe2023.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softwareegineering.GICCafe2023.DatabaseManagement.*;
import com.softwareegineering.GICCafe2023.Model.*;


@Controller
public class FoodAndDrinkManagement_Controller {
    
    @GetMapping(path = "/foodanddrinkmanagement")
    public ModelAndView displayPage(Model model) {

        ArrayList<String> sizes = new ArrayList<String>();
        sizes.add("Small");
        sizes.add("Medium");
        sizes.add("Large");
        
        model.addAttribute("sizes", sizes);
        model.addAttribute("productSizes", getProductWithSizeList());
        model.addAttribute("categories", getCategories());

        return new ModelAndView("foodanddrinkmanagement");
    }

    @PostMapping("/foodanddrinkmanagement")
    public ModelAndView addAndUpdateHandler(
        Model model,
        @RequestParam("productID") int productID,
        @RequestParam("productType") String productType,
        @RequestParam("productName") String productName,
        @RequestParam("productCategory") int productCategory_id,
        @RequestParam("productSize") String sizeProduct,
        @RequestParam("productPrice") double productPrice,
        @RequestParam("productDescription") String productDescription,
        @RequestParam("productImage") String productImage
        ) {
            ProductManagement productManagement = new ProductManagement();
            ProductSizeManagement productSizeManagement = new ProductSizeManagement();
        
            if(productID == 0) { // CREATE MODE
                Size newSize = new Size(sizeProduct, productPrice);
                int sizeID = addSize(newSize);
                newSize.setId(sizeID);

                
                Product product = new Product(0, productName, productDescription, getACategory(productCategory_id), productImage, null);
                int prod_id = productManagement.addProduct(product);
                product.setId(prod_id);

                ProductSize productSize = new ProductSize(0, newSize, product);
                productSizeManagement.addProductSize(productSize);
                
            } else { // UPDATE MODE

                // System.out.println(("Porduct Size ID: "+ productSizeID));
                // ProductSize productSize = getAProductSize(productSizeID);

                // System.out.println("Size: " + productSize);
                // System.out.println("Size: " + productSize.getSize().getSizeName());

                // Size size = productSize.getSize();
                // size.setSizeName(sizeProduct);
                // size.setPrice(productPrice);
                // updateSize(size);


                Product product = new Product(productID, productName, productDescription, getACategory(productCategory_id), productImage);
                productManagement.updateProduct(product);
            }

            model.addAttribute("productSizes", getProductWithSizeList());
            model.addAttribute("categories", getCategories());
            return new ModelAndView("redirect:/foodanddrinkmanagement");
    }

    @GetMapping("/foodanddrinkmanagement/DeleteProduct")
    public ModelAndView deleteProductHandler(Model model,
        @RequestParam("deleteProdID") int deleteProdID) {
        

            ProductSizeListMangement productSizeListMangement = new ProductSizeListMangement();
            productSizeListMangement.deleteProductAndAllSize(getProductByID(deleteProdID));

            
            model.addAttribute("productSizes", getProductWithSizeList());
            model.addAttribute("categories", getCategories());
        return new ModelAndView("/foodanddrinkmanagement");
    }

    private List<ProductSizeList> getProductWithSizeList() {
        ProductSizeListMangement productSizeListMangement = new ProductSizeListMangement();
        return productSizeListMangement.getAllProductWithListSize();
    }


    private ProductSize getAProductSize(int product_id) {
        System.out.println("id: " + product_id);
        ProductSizeManagement productSizeManagement = new ProductSizeManagement();
        return productSizeManagement.getProductSizeById(product_id);
    }

    private List<Category> getCategories() {
        CategoryManagement categoryManagemet = new CategoryManagement();
        return categoryManagemet.getAllCategories();
    }

    private Category getACategory(int cateId) {
        CategoryManagement categoryManagemet = new CategoryManagement();
        return categoryManagemet.getCategoryById(cateId);
    }

    private int addSize(Size size) {
        SizeManagement sizeManagement = new SizeManagement();
        return sizeManagement.addSize(size);
    }

    private void updateSize(Size size) {
        SizeManagement sizeManagement = new SizeManagement();
        sizeManagement.updateSize(size);
    }

    private Product getProductByID(int id) {
        ProductManagement productManagement = new ProductManagement();
        return productManagement.getProductById(id);
    }
    
}
