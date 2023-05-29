package com.softwareegineering.GICCafe2023.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TableSelection_Controller {
    
    @GetMapping(path = "/tableselection")
    public ModelAndView displayPage() {
        return new ModelAndView("tableselection");
    }

    
}
