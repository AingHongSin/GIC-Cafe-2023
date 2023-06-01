package com.softwareegineering.GICCafe2023.Controller;

import com.softwareegineering.GICCafe2023.DatabaseManagement.UserManagement;
import com.softwareegineering.GICCafe2023.Model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Login_Controller {

    @GetMapping("/")
    public ModelAndView displayPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/")
    public ModelAndView login(
                            @RequestParam("username") String username,
                            @RequestParam("password") String password) {
        // Validate the credentials and perform login logic

        UserManagement usermanagement = new UserManagement();

        User user = usermanagement.loginUser(username, password);
        

        if (user != null) {
            // Redirect to a success page or perform other actions
            
            if (user.getRole().equals("Admin")) return new ModelAndView("redirect:/categorymanagement");
            else if (user.getRole().equals("Cashier")) return new ModelAndView("redirect:/tableselection");

            return new ModelAndView("login");
        } else {
            // Redirect back to the login page with an error message
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }
}
