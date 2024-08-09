package nhs.uhdb.NHS_project.site_map_page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteMapPageController {
    @GetMapping("/site-map")
    ModelAndView getSiteMapPage(){return new ModelAndView("site_map/siteMap");}
}
