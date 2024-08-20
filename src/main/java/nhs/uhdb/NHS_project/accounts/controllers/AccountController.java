package nhs.uhdb.NHS_project.accounts.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AccountController {

    private UserService userService;


    public AccountController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/account")
    public ModelAndView account(Principal principal) {
        ModelAndView mav = new ModelAndView("account/account");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        mav.addObject("name", loggedInUser.getFirstname() + ' ' + loggedInUser.getLastname());
        mav.addObject("user", loggedInUser);

        return mav;
    }


}
