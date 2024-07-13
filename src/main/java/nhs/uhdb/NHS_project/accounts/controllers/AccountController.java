package nhs.uhdb.NHS_project.accounts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
    @GetMapping("/account")
    public ModelAndView account() {
        ModelAndView mav = new ModelAndView("account/account");
        return mav;
    }
}
