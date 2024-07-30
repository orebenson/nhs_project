package nhs.uhdb.NHS_project.information;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformationController {
    @GetMapping("/information")
    public ModelAndView getInformationPage() { return new ModelAndView("information/information"); }
}
