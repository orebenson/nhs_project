package nhs.uhdb.NHS_project.landing_page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class landingController {
    @GetMapping("/landing")
    public ModelAndView getLandingPage() { return new ModelAndView("landingpage/landing"); }
}
