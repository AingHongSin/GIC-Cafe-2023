package com.softwareegineering.GICCafe2023.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.sql.Date;
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

        model.addAttribute("users", getUser());

        return new ModelAndView("cashiermanagement");
    }

    @PostMapping("/cashiermanagement")
    public ModelAndView addUserView( 
                                @RequestParam("userID") String userIDStr,
                                @RequestParam("firstname") String firstname,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("sex") String sex,
                                @RequestParam("role") String role,
                                @RequestParam("dob")  @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date dobUtil,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("photo_url") String photo_url,
                                Model model
    ) {

        System.out.println("SEX: " + sex);
        System.out.println("File Path: " + photo_url);


        Date dob = new Date(dobUtil.getTime());

        var age = getAge(dob);

        var userID = Integer.valueOf(userIDStr);
        if (userID == 0) {
            LocalDate currentDate = LocalDate.now();
            Date hireDate = Date.valueOf(currentDate);

            User user = new User(userID, firstname, lastname, sex, role, dob, hireDate, age, username, password, photo_url);
            addUser(user);
        } else {
            User user = new User(userID, firstname, lastname, sex, role, dob, age, username, password, photo_url);
            updateUser(user);
        }

        model.addAttribute("users", getUser());
        return new ModelAndView("redirect:/cashiermanagement");
    }

    @GetMapping("/cashiermanagement/delUser")
    public ModelAndView delUserHandler(@RequestParam("deleteUserId") String userIdStr, 
                                        Model model) {

        System.out.println("User id: " + userIdStr);

        int userID = Integer.valueOf(userIdStr);
        
        UserManagement userManagement = new UserManagement();
        userManagement.disableUser(userID);

        model.addAttribute("users", getUser());
        return new ModelAndView("cashiermanagement");
        }


    private int addUser(User user) {
        UserManagement userManagement = new UserManagement();
        return userManagement.addUser(user);
    }

    private void updateUser(User user) {
        UserManagement userManagement = new UserManagement();
        userManagement.updateUser(user);
    }

    private List<User> getUser() {

        UserManagement  usermanagement = new UserManagement();
        List<User> users =  new ArrayList<>();

        users = usermanagement.getAllUsers();
        
        return users;
    }

    private int getAge(Date inputDate) {
        LocalDate localDobDate = inputDate.toLocalDate();     
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(localDobDate, currentDate);
        return period.getYears();
    }
    
}
