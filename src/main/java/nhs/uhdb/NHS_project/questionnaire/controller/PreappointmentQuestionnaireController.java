package nhs.uhdb.NHS_project.questionnaire.controller;
import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.logging.Logger;

@Controller
public class PreappointmentQuestionnaireController {

    private UserService userService;
    private PreappointmentResponseService preappointmentResponseService;

    //Using logger for debugging
    private static final Logger LOGGER = Logger.getLogger(PreappointmentQuestionnaireController.class.getName());


    //Constructor to initialise PreappointmentResponseService and UserService
    @Autowired
    public PreappointmentQuestionnaireController(PreappointmentResponseService preappointmentResponseService, UserService userService){
        this.preappointmentResponseService = preappointmentResponseService;
        this.userService = userService;
    }

    //Method to handle GET requests for the preappointment questionnaire page
    @GetMapping("/questionnaires/preappointment-questionnaire")
    public ModelAndView preappointmentQuestionnaire() {
        ModelAndView mav = new ModelAndView("questionnaires/pre-appointmentQuestionnaire");
        PreappointmentResponse form = new PreappointmentResponse();
        mav.addObject("preappointmentQuestionnaireForm", form);
        return mav;
    }

    //Method to handle POST requests for submitting the preappointment questionnaire form
    @PostMapping("/questionnaires/preappointment-questionnaire")
    public ModelAndView postPreappointmentSubmit(Principal principal, @ModelAttribute("preappointmentQuestionnaireForm") PreappointmentResponse form) {
        //Retrieve the logged-in user's email from the Principal object
        String loggedInUserEmail = principal.getName();

        //Fetch the User object using the email
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        //set the User ID and current date on the form
        form.setUser_id(loggedInUser.getUser_id());
        form.setCreatedAt(LocalDate.now());

        //Log the form submission
        LOGGER.info("Submitting form: " + form);

        //Save the PreappointmentResponse and retrieve the response ID
        Long responseID = preappointmentResponseService.saveResponse(form);

        //Log the successful save with the response ID
        LOGGER.info("Saved PreappointmentResponse with ID: " + responseID);

        //Return the success view
        return new ModelAndView("redirect:/questionnaires/success");
    }


}
