package nhs.uhdb.NHS_project.questionnaire.controller;
import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponseArm;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponseBreast;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseArmService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseBreastService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class QolQuestionnaireController {
    private UserService userService;
    private QolResponseService qolResponseService;
    private QolResponseArmService qolResponseArmService;
    private QolResponseBreastService qolResponseBreastService;

    public QolQuestionnaireController(QolResponseService qolResponseService, QolResponseBreastService qolResponseBreastService, QolResponseArmService qolResponseArmService, UserService userService){
        this.qolResponseService = qolResponseService;
        this.qolResponseArmService = qolResponseArmService;
        this.userService = userService;
        this.qolResponseBreastService = qolResponseBreastService;
    }

    @GetMapping("/questionnaires/qol-questionnaire")
    public ModelAndView qolQuestionnaire() {
        ModelAndView mav = new ModelAndView("questionnaires/qolQuestionnaire");
        QolResponse form = new QolResponse();
        mav.addObject("qolQuestionnaireForm", form);
        return mav;
    }

    @PostMapping("/questionnaires/qol-questionnaire")
    public ModelAndView qolSubmit(Principal principal, @ModelAttribute("qolQuestionnaireForm") QolResponse form) {
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);
        form.setUser_id(loggedInUser.getUser_id());
        form.setCreatedAt(LocalDate.now());
        Long id = qolResponseService.saveResponse(form);
        return new ModelAndView("redirect:/questionnaires/success");
    }

    @GetMapping("/questionnaires/qol-questionnaire-arm")
    public ModelAndView qolQuestionnaireArm() {
        ModelAndView mav = new ModelAndView("questionnaires/qolQuestionnaireArm");
        QolResponseArm form = new QolResponseArm();
        mav.addObject("qolQuestionnaireArmForm", form);
        return mav;
    }

    @PostMapping("/questionnaires/qol-questionnaire-arm")
    public ModelAndView submitQolArm(Principal principal, @ModelAttribute("qolQuestionnaireArmForm") QolResponseArm form) {
        User loggedInUser = userService.getUserByEmail(principal.getName());
        form.setUser_id(loggedInUser.getUser_id());
        form.setCreatedAt(LocalDate.now());
        Long id = qolResponseArmService.saveResponse(form);
        return new ModelAndView("redirect:/questionnaires/success");
    }

    @GetMapping("/questionnaires/qol-questionnaire-breast")
    public ModelAndView qolQuestionnaireBreast() {
        ModelAndView mav = new ModelAndView("questionnaires/qolQuestionnaireBreast");
        QolResponseBreast form = new QolResponseBreast();
        mav.addObject("qolQuestionnaireBreastForm", form);
        return mav;
    }

    @PostMapping("/questionnaires/qol-questionnaire-breast")
    public ModelAndView submitQolBreast(Principal principal, @ModelAttribute("qolQuestionnaireBreastForm") QolResponseBreast form) {
        User loggedInUser = userService.getUserByEmail(principal.getName());
        form.setUser_id(loggedInUser.getUser_id());
        form.setCreatedAt(LocalDate.now());
        Long id = qolResponseBreastService.saveResponse(form);
        return new ModelAndView("redirect:/questionnaires/success");
    }

}