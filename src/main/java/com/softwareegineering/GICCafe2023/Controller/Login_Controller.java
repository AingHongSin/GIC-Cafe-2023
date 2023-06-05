package com.softwareegineering.GICCafe2023.Controller;

import com.softwareegineering.GICCafe2023.DatabaseManagement.UserManagement;
import com.softwareegineering.GICCafe2023.Model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.catalina.startup.UserConfig;
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

        User user = usermanagement.login(username, password);
        

        if (user != null) {
            // Redirect to a success page or perform other actions
            
            if (user.getRole().equals("Admin")) {
                updateCurrentTimeStamp(user);

                return new ModelAndView("redirect:/tablex");
            } else if (user.getRole().equals("Cashier")){
                updateCurrentTimeStamp(user);

                return new ModelAndView("redirect:/tableselection");
            } 

            return new ModelAndView("login");
        } else {
            // Redirect back to the login page with an error message
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }


    private void updateCurrentTimeStamp(User user) {

        LocalDateTime currentDateTime = LocalDateTime.now();

        user.setLastLogin(Timestamp.valueOf(currentDateTime));

        UserManagement userManagement = new UserManagement();
        userManagement.updateUser(user);
    }
}
