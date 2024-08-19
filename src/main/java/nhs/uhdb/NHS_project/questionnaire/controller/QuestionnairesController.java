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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class QuestionnairesController {

    private PreappointmentResponseService preappointmentResponseService;
    private QolResponseService qolResponseService;
    private UserService userService;

    private QolResponseArmService qolResponseArmService;
    private QolResponseBreastService qolResponseBreastService;

    public QuestionnairesController(UserService userService, QolResponseBreastService qolResponseBreastService, QolResponseArmService qolResponseArmService, PreappointmentResponseService preappointmentResponseService, QolResponseService qolResponseService) {
        this.preappointmentResponseService = preappointmentResponseService;
        this.qolResponseService = qolResponseService;
        this.userService = userService;
        this.qolResponseArmService = qolResponseArmService;
        this.qolResponseBreastService = qolResponseBreastService;
    }


    @GetMapping("/questionnaires")
    public ModelAndView userQuestionnaires(Principal principal) {
        ModelAndView mav = new ModelAndView("questionnaires/taskHubPage");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        Long userId = loggedInUser.getUser_id();

        List<PreappointmentResponse> questionnaires = preappointmentResponseService.getResponsesByUserId(userId);
        List<QolResponse> qolQuestionnaires = qolResponseService.getResponsesByUserId(userId);
        List<QolResponseArm> qolArmQuestionnaires = qolResponseArmService.getResponsesByUserId(userId);
        List<QolResponseBreast> qolBreastQuestionnaires = qolResponseBreastService.getResponsesByUserId(userId);

        mav.addObject("name", loggedInUser.getFirstname() + ' ' + loggedInUser.getLastname());
        mav.addObject("user", loggedInUser);
        mav.addObject("questionnaires", questionnaires);
        mav.addObject("qolQuestionnaires", qolQuestionnaires);
        mav.addObject("qolArmQuestionnaires", qolArmQuestionnaires);
        mav.addObject("qolBreastQuestionnaires", qolBreastQuestionnaires);

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

    @GetMapping("/questionnaires/qol-questionnaire-arm/{id}")
    public ModelAndView qolQuestionnaireArmHistory(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("questionnaires/viewQolQuestionnaireArmUser");

        QolResponseArm qolResponseArm = qolResponseArmService.getResponseById(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = qolResponseArm.getCreatedAt().format(formatter);

        mav.addObject("qolQuestionnaireArm", qolResponseArm);
        mav.addObject("formattedDate", formattedDate);

        return mav;
    }

    @GetMapping("/questionnaires/qol-questionnaire-breast/{id}")
    public ModelAndView qolQuestionnaireBreastHistory(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("questionnaires/viewQolQuestionnaireBreastUser");

        QolResponseBreast qolResponseBreast = qolResponseBreastService.getResponseById(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = qolResponseBreast.getCreatedAt().format(formatter);

        mav.addObject("qolResponseBreast", qolResponseBreast);
        mav.addObject("formattedDate", formattedDate);

        return mav;
    }

    @GetMapping("/questionnaires/success")
    public ModelAndView questionnaireSuccess() {
        return new ModelAndView("questionnaires/submissionSuccess");
    }

}
