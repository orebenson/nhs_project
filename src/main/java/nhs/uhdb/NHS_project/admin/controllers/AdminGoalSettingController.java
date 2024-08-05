package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.Goal;
import nhs.uhdb.NHS_project.admin.service.GoalService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminGoalSettingController {

    private final GoalService goalService;
    private final UserService userService;

    public AdminGoalSettingController(GoalService goalService, UserService userService) {
        this.goalService = goalService;
        this.userService = userService;
    }

    // Display form to set a new goal for a user
    @GetMapping("/admin/{user_id}/goal")
    public ModelAndView adminGetSetUserGoalForm(@PathVariable Long user_id) {
        User user = userService.getUserByUserId(user_id);
        ModelAndView mav = new ModelAndView("admin/adminSetUserGoal");
        if (user == null) {
            mav.setViewName("admin/adminSearchUserError");
        } else {
            mav.addObject("user", user);
            mav.addObject("goal", new Goal()); // For form binding
        }
        return mav;
    }

    // Handle form submission to create a new goal for a user
    @PostMapping("/admin/{user_id}/goal")
    public ModelAndView adminPostSetUserGoal(@PathVariable Long user_id, @ModelAttribute("goal") Goal goal) {
        System.out.println("Received goal: " + goal);
        goal.setUserId(user_id);
        Long goalId = goalService.createGoal(goal);
        goal.setUserId(user_id);
        User user = userService.getUserByUserId(user_id);
        if (user == null) {return new ModelAndView("admin/adminSearchUserError");}


        if (goalId == null) {return new ModelAndView("admin/adminSetUserGoalError").addObject("user", user);}
        return new ModelAndView("admin/adminSetUserGoalSuccess").addObject("user", user);
    }
}

