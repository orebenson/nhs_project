package nhs.uhdb.NHS_project.questionnaire.controller;
import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.logging.Logger;

@Controller
public class PreappointmentQuestionnaireController {

    private UserService userService;
    private PreappointmentResponseService preappointmentResponseService;

    private static final Logger LOGGER = Logger.getLogger(PreappointmentQuestionnaireController.class.getName());


    @Autowired
    public PreappointmentQuestionnaireController(PreappointmentResponseService preappointmentResponseService, UserService userService){
        this.preappointmentResponseService = preappointmentResponseService;
        this.userService = userService;
    }

    @GetMapping("/questionnaires/preappointment-questionnaire")
    public ModelAndView preappointmentQuestionnaire() {
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

        LOGGER.info("Submitting form: " + form);

        Long responseID = preappointmentResponseService.saveResponse(form);

        LOGGER.info("Saved PreappointmentResponse with ID: " + responseID);

        return new ModelAndView("/questionnaires/submissionSuccess");
    }


}
