package nhs.uhdb.NHS_project.cookes_page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CookiesPageController {
    @GetMapping("/cookies")
    ModelAndView getCookiesPage(){return new ModelAndView("cookies_page/cookies");}
}
