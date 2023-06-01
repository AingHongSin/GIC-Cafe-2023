package com.softwareegineering.GICCafe2023.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softwareegineering.GICCafe2023.DatabaseManagement.UserManagement;
import com.softwareegineering.GICCafe2023.Model.User;


@Controller
public class Cashire_Controller {
    
    @GetMapping(path = "/cashiermanagement")
    public ModelAndView displayPage(Model model) {

        var tmpUser = getUser();

        model.addAttribute("users", tmpUser);

        return new ModelAndView("cashiermanagement");
    }

    private List<User> getUser() {

        UserManagement  usermanagement = new UserManagement();
        List<User> users =  new ArrayList<>();

        users = usermanagement.getAllUsers();
        
        return users;
    }
    
}
