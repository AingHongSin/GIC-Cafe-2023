package group2.gic32.cafeGIC.Controller;

import group2.gic32.cafeGIC.DatabaseManagement.UserManagement;

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
        boolean isValidCredentials = UserManagement.loginUser(username, password);
        

        if (isValidCredentials) {
            // Redirect to a success page or perform other actions
            return new ModelAndView("tableselection");
        } else {
            // Redirect back to the login page with an error message
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }
}
