package nhs.uhdb.NHS_project.accounts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("account/login");
    }

    @GetMapping("/login/success")
    public ModelAndView loginSuccess() {
        return new ModelAndView("account/loginSuccess");
    }

    @GetMapping("/login/error")
    public ModelAndView loginError() {
        return new ModelAndView("account/loginError");
    }
}
