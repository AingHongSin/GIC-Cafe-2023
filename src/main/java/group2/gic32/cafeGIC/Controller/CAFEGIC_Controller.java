package group2.gic32.cafeGIC.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CAFEGIC_Controller {
    @GetMapping(path = "/")
    public ModelAndView test(){
        return new ModelAndView("test");
    }
}
