package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.model.TreatmentPlan;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminTreatmentPlanController {

    private TreatmentPlanService treatmentPlanService;

    public AdminTreatmentPlanController(TreatmentPlanService treatmentPlanService) {
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
        System.out.println(plan);
        Long treatmentPlanId = treatmentPlanService.createTreatmentPlan(plan);
        if(treatmentPlanId == null) return new ModelAndView("admin/adminCreateTreatmentPlanError");
        return new ModelAndView("admin/adminCreateTreatmentPlanSuccess");
    }
}
