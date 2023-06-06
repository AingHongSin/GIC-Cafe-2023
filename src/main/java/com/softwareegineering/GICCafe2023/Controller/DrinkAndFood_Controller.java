package com.softwareegineering.GICCafe2023.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softwareegineering.GICCafe2023.DatabaseManagement.*;
import com.softwareegineering.GICCafe2023.Model.*;

import org.springframework.ui.Model;


@Controller
public class DrinkAndFood_Controller {
    // MARK: View
    @GetMapping("/drinkandfood")
    public ModelAndView drinkAndFoodViewHome(Model model){

        model.addAttribute("catetories", getAllCategory());
        model.addAttribute("typeselelcted", "All");
        model.addAttribute("products", getAllProductSizesViewByProductList(getAllProducts()));
        return new ModelAndView("drinksection");
    }

    @GetMapping("/drinkandfood/drink")
    public ModelAndView drinkSelection(Model model){

        model.addAttribute("catetories", getCategoryByType("Drink"));
        model.addAttribute("typeselelcted", "Drink");
        model.addAttribute("products", getAllProductSizesViewByProductList(getAllDrinkProduct()));
        return new ModelAndView("drinksection");
    }

    @GetMapping("/drinkandfood/food")
    public ModelAndView foodSelection(Model model){

        model.addAttribute("catetories", getCategoryByType("Food"));
        model.addAttribute("typeselelcted", "Food");
        model.addAttribute("products", getAllProductSizesViewByProductList(getAllFoodProduct()));
        return new ModelAndView("drinksection");
    }

    @GetMapping("/drinkandfood/{cate_id}")
    public ModelAndView productInCate(Model model,
    @PathVariable("cate_id") int cate_id) {
        Category category = getCategoryByID(cate_id);
        

        model.addAttribute("catetories", getCategoryByType(category.getType()));
        model.addAttribute("typeselelcted", "Food");
        model.addAttribute("products", getAllProductSizesViewByProductList(getAllProductsByCategory(category.getId())));
        return new ModelAndView("drinksection");
    }

    @PostMapping("/drinkandfood")
    public ModelAndView selectProducthandler(Model model
    ) {
        return new ModelAndView("redirect:/drinkandfood");
    }

    // MARK: request API
    @GetMapping("/drinkandfood/{productId}")
    public Product getProduct(@PathVariable int productId) {
        ProductManagement productManagement = new ProductManagement();
        Product product = productManagement.getProductById(productId);
        return product;
    }

    // MARK: function

    private List<Product> getAllDrinkProduct() {
        ProductManagement productManagement = new ProductManagement();
        return productManagement.getAllDrink();
    }

    private List<Product> getAllFoodProduct() {
        ProductManagement productManagement = new ProductManagement();
        return productManagement.getAllFood();
    }

    private List<Product> getAllProducts() {
        ProductManagement productManagement = new ProductManagement();
        return productManagement.getAllProducts();
    }

    private List<Category> getAllCategory() {
        CategoryManagement categoryManagement = new CategoryManagement();
        return categoryManagement.getAllCategories();
    }
    private List<Product> getAllProductsByCategory(int cate_id) {
        ProductManagement productManagement = new ProductManagement();
        return productManagement.getProductsByCategory(cate_id);
    }
    private Category getCategoryByID(int category_id) {
        CategoryManagement categoryManagement = new CategoryManagement();
        return categoryManagement.getCategoryById(category_id);
    }
    private List<Category> getCategoryByType(String type) {
        CategoryManagement categoryManagement = new CategoryManagement();
        if (type.equals("Food")) {
            return categoryManagement.getAllFoodCatrgory();
        } else {
            return categoryManagement.getAllDrinksCategory();

        }
    }


    private List<ProductSizeList> getAllProductSizesViewByProductList(List<Product> products) {
        ProductSizeListMangement productSizeListMangement = new ProductSizeListMangement();
        return productSizeListMangement.getProductWithListSizeByProductList(products); 
    }
 
    
}

    
