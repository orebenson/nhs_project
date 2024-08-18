package nhs.uhdb.NHS_project.accounts.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AccountController {

    private UserService userService;
    private PreappointmentResponseService preappointmentResponseService;
    private QolResponseService qolResponseService;

    public AccountController(UserService userService, PreappointmentResponseService preappointmentResponseService, QolResponseService qolResponseService) {
        this.userService = userService;
        this.preappointmentResponseService = preappointmentResponseService;
        this.qolResponseService =  qolResponseService;
    }
    @GetMapping("/account")
    public ModelAndView account(Principal principal) {
        ModelAndView mav = new ModelAndView("account/account");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        mav.addObject("name", loggedInUser.getFirstname() + ' ' + loggedInUser.getLastname());
        mav.addObject("user", loggedInUser);

        return mav;
    }

    @GetMapping("/questionnaires")
    public ModelAndView userQuestionnaires(Principal principal) {
        ModelAndView mav = new ModelAndView("questionnaires/taskHubPage");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        Long userId = loggedInUser.getUser_id();

        List<PreappointmentResponse> questionnaires = preappointmentResponseService.getResponsesByUserId(userId);
        List<QolResponse> qolQuestionnaires = qolResponseService.getResponsesByUserId(userId);

        mav.addObject("name", loggedInUser.getFirstname() + ' ' + loggedInUser.getLastname());
        mav.addObject("user", loggedInUser);
        mav.addObject("questionnaires", questionnaires);
        mav.addObject("qolQuestionnaires", qolQuestionnaires);

        return mav;
    }

    @GetMapping("/questionnaires/preappointment-questionnaire/{questionnaireId}")
    public ModelAndView getUserPreQuestionnaireHistory(Principal principal, @PathVariable Long questionnaireId) {
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        Long userId = loggedInUser.getUser_id();

        ModelAndView mav = new ModelAndView("questionnaires/viewPreappointmentQuestionnaireUser");

        PreappointmentResponse response = preappointmentResponseService.getResponseById(questionnaireId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = response.getCreatedAt().format(formatter);

        mav.addObject("preappointmentQuestionnaireForm", response);
        mav.addObject("formattedDate", formattedDate);

        return mav;
    }

    @GetMapping("/questionnaires/qol-questionnaire/{qolQuestionnaireId}")
    public ModelAndView getUserQolQuestionnaireHistory(Principal principal, @PathVariable Long qolQuestionnaireId) {
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        Long userId = loggedInUser.getUser_id();

        ModelAndView mav = new ModelAndView("questionnaires/viewQolQuestionnaireUser");

        QolResponse qolResponse = qolResponseService.getResponseById(qolQuestionnaireId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = qolResponse.getCreatedAt().format(formatter);

        mav.addObject("qolQuestionnaireForm", qolResponse);
        mav.addObject("formattedDate", formattedDate);

        return mav;
    }
}
