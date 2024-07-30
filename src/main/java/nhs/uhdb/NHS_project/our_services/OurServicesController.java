package nhs.uhdb.NHS_project.our_services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OurServicesController {
    @GetMapping("/services")
    public ModelAndView getServicesPage() { return new ModelAndView("our_services/services"); }
}
