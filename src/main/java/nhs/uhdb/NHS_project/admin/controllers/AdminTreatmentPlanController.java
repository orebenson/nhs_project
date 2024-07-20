package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.model.TreatmentPlan;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminTreatmentPlanController {

    private TreatmentPlanService treatmentPlanService;
    private UserService userService;

    public AdminTreatmentPlanController(TreatmentPlanService treatmentPlanService, UserService userService) {
        this.userService = userService;
        this.treatmentPlanService = treatmentPlanService;
    }
    @GetMapping("/admin/plans/add")
    public ModelAndView adminGetCreateTreatmentPlan() {
        ModelAndView mav = new ModelAndView("admin/adminCreateTreatmentPlan");
        mav.addObject("plan", new TreatmentPlan());
        mav.addObject("exercises", new ArrayList<Exercise>());
        return mav;
    }

    @PostMapping("/admin/plans/add")
    public ModelAndView adminPostCreateTreatmentPlan(@ModelAttribute("plan") TreatmentPlan plan, @ModelAttribute("exercises") ArrayList<Exercise> exercises) {
        Long treatmentPlanId = treatmentPlanService.createTreatmentPlan(plan);
        if(treatmentPlanId == null) return new ModelAndView("admin/adminCreateTreatmentPlanError");
        return new ModelAndView("admin/adminCreateTreatmentPlanSuccess");
    }

    @GetMapping("/admin/{id}/plan")
    public ModelAndView adminGetSetUserPlan(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/adminSetUserTreatmentPlan");
        User user = userService.getUserByUserId(id);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");
        List<TreatmentPlan> plans = treatmentPlanService.getAllTreatmentPlans();
        if(plans.size() == 0) plans = new ArrayList<>();
        mav.addObject("plans", plans);
        mav.addObject("user", user);
        mav.addObject("selectedPlan", new TreatmentPlan());
        return mav;
    }

    @PostMapping("/admin/{user_id}/plan")
    public ModelAndView adminPostSetUserPlan(@PathVariable Long user_id, @ModelAttribute("selectedPlan") TreatmentPlan selectedPlan) {
        User user = userService.getUserByUserId(user_id);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        Boolean result = treatmentPlanService.setUserTreatmentPlan(user_id, selectedPlan.getId());
        if(!result) return new ModelAndView("admin/adminSetUserTreatmentPlanError").addObject("user", user);
        return new ModelAndView("admin/adminSetUserTreatmentPlanSuccess").addObject("user", user);
    }
}
