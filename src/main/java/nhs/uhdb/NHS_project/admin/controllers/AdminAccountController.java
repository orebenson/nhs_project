package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.TreatmentPlan;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
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
    private TreatmentPlanService treatmentPlanService;

    public AdminAccountController(UserService userService, TreatmentPlanService treatmentPlanService) {
        this.userService = userService;
        this.treatmentPlanService = treatmentPlanService;
    }

    @GetMapping("/admin")
    public ModelAndView admin(Principal principal) {
        ModelAndView mav = new ModelAndView("admin/adminAccount");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);
        mav.addObject("name", loggedInUser.getFirstname() + ' ' + loggedInUser.getLastname());
        return mav;
    }

    @GetMapping("/admin/register")
    public ModelAndView adminRegister() {
        ModelAndView mav = new ModelAndView("admin/adminRegister");
        mav.addObject("newUserObject", new User());
        return mav;
    }

    @PostMapping("/admin/register")
    public ModelAndView registerUser(@ModelAttribute("newUserObject") User user) {
        Long user_id = userService.createAdminUser(user);
        if (user_id == null) return new ModelAndView("redirect:/admin/register/error");
        return new ModelAndView("redirect:/admin/register/success");
    }

    @GetMapping("/admin/register/success")
    public ModelAndView adminRegisterSuccess() {
        return new ModelAndView("admin/adminRegisterSuccess");
    }

    @GetMapping("/admin/register/error")
    public ModelAndView adminRegisterError() {
        return new ModelAndView("admin/adminRegisterError");
    }

    @GetMapping("/admin/search")
    public ModelAndView adminSearchUser() {
        ModelAndView mav = new ModelAndView("admin/adminSearchUser");
        mav.addObject("newUserObject", new User());
        return mav;
    }

    @PostMapping("/admin/search")
    public ModelAndView adminSearchUserSubmit(@ModelAttribute("newUserObject") User user) {
        Long user_id = userService.getUserIdByEmail(user.getEmail());
        if (user_id == null) return new ModelAndView("admin/adminSearchUserError");
        return new ModelAndView("redirect:/admin/search/" + user_id);
    }

    @GetMapping("/admin/search/{id}")
    public ModelAndView adminViewUser(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/adminViewUser");
        User user = userService.getUserByUserId(id);
        if (user == null) return new ModelAndView("admin/adminSearchUser");
        TreatmentPlan userPlan = treatmentPlanService.getTreatmentPlanByUserId(user.getUser_id());
        if(userPlan == null) userPlan = new TreatmentPlan();
        userPlan.setName("None");
        mav.addObject("userPlan", userPlan);
        mav.addObject("user", user);
        return mav;
    }
}
