package com.softwareegineering.GICCafe2023.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/cashiermanagement")
    public ModelAndView addUserView( @RequestParam("firstname") String firstname,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("sex") String sex,
                                @RequestParam("role") String role,
                                @RequestParam("dob")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("photo_url") String photo_url,
                                Model model
    ) {
        var age = getAge(dob);
        User user = new User(0, firstname, lastname, sex, role, dob, age, username, password, photo_url);
        addUser(user);
        return new ModelAndView("redirect:/cashiermanagement");
    }

    private int addUser(User user) {
        UserManagement userManagement = new UserManagement();
        return userManagement.addUser(user);
    }

    private List<User> getUser() {

        UserManagement  usermanagement = new UserManagement();
        List<User> users =  new ArrayList<>();

        users = usermanagement.getAllUsers();
        
        return users;
    }

    private int getAge(Date inputDate) {

        LocalDate dateOfBirth = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }
    
}
