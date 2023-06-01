package com.softwareegineering.GICCafe2023.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softwareegineering.GICCafe2023.DatabaseManagement.ProductManagement;
import com.softwareegineering.GICCafe2023.Model.Product;


@Controller
public class FoodAndDrinkManagement_Controller {
    
    @GetMapping(path = "/foodanddrinkmanagement")
    public ModelAndView displayPage(Model model) {

        var tmpUser = getProducts();

        model.addAttribute("products", tmpUser);

        return new ModelAndView("foodanddrinkmanagement");
    }

    private List<Product> getProducts() {

        ProductManagement  productManagement = new ProductManagement();

        List<Product> products =  new ArrayList<>();

        products = productManagement.getAllProducts();
        
        return products;
    }

    
    
}
