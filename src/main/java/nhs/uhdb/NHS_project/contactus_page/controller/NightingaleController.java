package nhs.uhdb.NHS_project.contactus_page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NightingaleController {
    @GetMapping("/ContactUs/Nightingale")
    public ModelAndView getLandingPage() { return new ModelAndView("contactus/Nightingale");}
}
