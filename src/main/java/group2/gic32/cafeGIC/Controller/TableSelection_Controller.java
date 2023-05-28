package group2.gic32.cafeGIC.Controller;


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
