package com.softwareegineering.GICCafe2023.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.softwareegineering.GICCafe2023.DatabaseManagement.CategoryManagemet;
import com.softwareegineering.GICCafe2023.DatabaseManagement.ProductManagement;
import com.softwareegineering.GICCafe2023.Model.*;


@Controller
public class Category_Controller {
    
    @GetMapping("/categorymanagement")
    public ModelAndView category_management(Model model){

        Category defaultCate = new Category(0, "Category");

        model.addAttribute("categorySelected", defaultCate );
        model.addAttribute("categories", getCategories());
        model.addAttribute("products", getAllProducts());
        return new ModelAndView("categorymanagement");
    }

    @GetMapping("/categorymanagement/porductlist")
    public ModelAndView porductlist(@RequestParam("cateID") int categoryID, Model model) {
       
        model.addAttribute("categories", getCategories());
        model.addAttribute("categorySelected", getACategory(categoryID) );
        model.addAttribute("products", getProductByCategory(categoryID));      
        return new ModelAndView("categorymanagement");
    }

    @PostMapping("/categorymanagement")
    public ModelAndView addNewCategory(
                                @RequestParam("categoryId") int categoryID, 
                                @RequestParam("categoryName") String categoryName, 
                                @RequestParam("isCreate") Boolean isCreate,
                                Model model) {

        
        CategoryManagemet categoryManagemet = new CategoryManagemet();
        Category category = new Category(categoryID, categoryName);

        if (categoryName != "") {
            if(isCreate) {
                categoryID = categoryManagemet.addCategory(category);
            } else {
                categoryManagemet.updateCategory(category);
            }            
        }



        model.addAttribute("categories", getCategories());
        model.addAttribute("categorySelected", getACategory(categoryID) );
        model.addAttribute("products", getProductByCategory(categoryID));      
        return new ModelAndView("categorymanagement");
    }

    @GetMapping("/categorymanagement/deletecategory")
    public ModelAndView deleteCate(
                                @RequestParam("deleteCateId") int deleteCateId
                                ) {

        CategoryManagemet categoryManagemet = new CategoryManagemet();
        categoryManagemet.deleteCategory(deleteCateId);


        return new ModelAndView("redirect:/categorymanagement");
    }





    private Category getACategory(int cateId) {
        CategoryManagemet categoryManagemet = new CategoryManagemet();

        return categoryManagemet.getCategoryById(cateId);
    }

    private List<Category> getCategories() {

        CategoryManagemet categoryManagemet = new CategoryManagemet();
        List<Category> categories = categoryManagemet.getAllCategories();

        return categories;
    }

    private List<Product> getProductByCategory(int categoryID) {
        ProductManagement productManagement = new ProductManagement();
        return productManagement.getProductsByCategory(categoryID);
    }

    private List<Product> getAllProducts() {

        ProductManagement  productManagement = new ProductManagement();
        List<Product> products = productManagement.getAllProducts();
        
        return products;
    }

}