package nhs.uhdb.NHS_project.support;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SupportController {
    @GetMapping("/support")
    public ModelAndView getSupportPage() { return new ModelAndView("support/support"); }
}
