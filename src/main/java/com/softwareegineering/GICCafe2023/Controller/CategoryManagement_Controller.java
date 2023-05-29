package com.softwareegineering.GICCafe2023.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryManagement_Controller {
    
    @GetMapping("/categorymanagement")
    public ModelAndView category_management(){
        return new ModelAndView("categorymanagement");
    }

}
