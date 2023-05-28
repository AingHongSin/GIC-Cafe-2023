package group2.gic32.cafeGIC.Controller;

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
