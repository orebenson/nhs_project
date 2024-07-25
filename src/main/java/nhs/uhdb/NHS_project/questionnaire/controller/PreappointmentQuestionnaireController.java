package nhs.uhdb.NHS_project.questionnaire.controller;
import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class PreappointmentQuestionnaireController {

    private UserService userService;
    private PreappointmentResponseService preappointmentResponseService;

    public PreappointmentQuestionnaireController(PreappointmentResponseService preappointmentResponseService, UserService userService){
        this.preappointmentResponseService = preappointmentResponseService;
        this.userService = userService;
    }

    @GetMapping("/questionnaires/preappointment-questionnaire")
    public ModelAndView preAppointmentQuestionnaire() {
        ModelAndView mav = new ModelAndView("questionnaires/pre-appointmentQuestionnaire");
        PreappointmentResponse form = new PreappointmentResponse();
        mav.addObject("preappointmentQuestionnaireForm", form);
        return mav;
    }

    @PostMapping("/questionnaires/preappointment-questionnaire")
    public ModelAndView postPreappointmentSubmit(Principal principal, @ModelAttribute("preappointmentQuestionnaireForm") PreappointmentResponse form) {
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);
        form.setUser_id(loggedInUser.getUser_id());
        form.setCreatedAt(LocalDate.now());
        Long id = preappointmentResponseService.saveResponse(form);

        return new ModelAndView("/questionnaires/submissionSuccess");
    }


}
