package nhs.uhdb.NHS_project.security;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorControllerImpl implements ErrorController{
    @GetMapping("/403")
    public ModelAndView error403() {
        return new ModelAndView("error/403");
    }

    @GetMapping("/500")
    public ModelAndView error500() {
        return new ModelAndView("error/500");
    }

    @GetMapping("/404")
    public ModelAndView error404() {
        return new ModelAndView("error/404");
    }

    @GetMapping("/400")
    public ModelAndView error400() {
        return new ModelAndView("error/400");
    }

    @GetMapping("/405")
    public ModelAndView error405() {
        return new ModelAndView("error/405");
    }
}
