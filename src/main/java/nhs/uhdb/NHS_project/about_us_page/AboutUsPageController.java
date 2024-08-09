package nhs.uhdb.NHS_project.about_us_page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutUsPageController {
    @GetMapping("/about-us")
    ModelAndView getAboutUsPage(){return new ModelAndView("about_us/aboutUs");}
}
