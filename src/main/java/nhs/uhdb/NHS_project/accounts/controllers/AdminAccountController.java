package nhs.uhdb.NHS_project.accounts.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AdminAccountController {

    private UserService userService;

    public AdminAccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ModelAndView admin(Principal principal) {
        ModelAndView mav = new ModelAndView("account/adminAccount");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);
        mav.addObject("name", loggedInUser.getFirstname() + ' ' + loggedInUser.getLastname());
        return mav;
    }

    @GetMapping("/admin/register")
    public ModelAndView adminRegister() {
        ModelAndView mav = new ModelAndView("account/adminRegister");
        mav.addObject("newUserObject", new User());
        return mav;
    }

    @PostMapping("/admin/register")
    public ModelAndView registerUser(@ModelAttribute("newUserObject") User user) {
        Long user_id = userService.createAdminUser(user);
        if (user_id == null) return new ModelAndView("redirect:/register/error");
        return new ModelAndView("redirect:/register/success");
    }

    @GetMapping("/admin/search")
    public ModelAndView adminSearchUser() {
        ModelAndView mav = new ModelAndView("account/adminSearchUser");
        mav.addObject("newUserObject", new User());
        return mav;
    }

    @PostMapping("/admin/search")
    public ModelAndView adminSearchUserSubmit(@ModelAttribute("newUserObject") User user) {
        Long user_id = userService.getUserIdByEmail(user.getEmail());
        if (user_id == null) return new ModelAndView("account/adminSearchUserError");
        return new ModelAndView("redirect:/admin/search/" + user_id);
    }

    @GetMapping("/admin/search/{id}")
    public ModelAndView adminViewUser(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("account/adminViewUser");
        User user = userService.getUserByUserId(id);
        if (user == null) return new ModelAndView("account/adminSearchUser");
        mav.addObject("user", user);
        return mav;
    }
}
