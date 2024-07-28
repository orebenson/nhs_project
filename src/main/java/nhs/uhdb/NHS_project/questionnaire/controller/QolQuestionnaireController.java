package nhs.uhdb.NHS_project.questionnaire.controller;
import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class QolQuestionnaireController {
    private UserService userService;
    private QolResponseService qolResponseService;

    public QolQuestionnaireController(QolResponseService qolResponseService, UserService userService){
        this.qolResponseService = qolResponseService;
        this.userService = userService;
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

        return new ModelAndView("/questionnaires/submissionSuccess");
    }

}