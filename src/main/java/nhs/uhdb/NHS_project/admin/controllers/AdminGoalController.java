package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminGoalController {

    private UserService userService;

    @GetMapping("/admin/{user_id}/goalSetting")
    public ModelAndView adminGoalSetting(@PathVariable("user_id") Long user_id) {
        ModelAndView modelAndView = new ModelAndView("admin/adminSetUserGoal");
        User user = userService.getUserByUserId(user_id);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");
        return modelAndView;
    }
}
