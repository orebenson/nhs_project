package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.LymphoedemaType;
import nhs.uhdb.NHS_project.admin.model.TreatmentPlan;
import nhs.uhdb.NHS_project.admin.model.Goal;
import nhs.uhdb.NHS_project.admin.service.GoalService;
import nhs.uhdb.NHS_project.admin.service.LymphoedemaTypeService;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
import nhs.uhdb.NHS_project.diary.controllers.ImageUtil;
import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.services.DiaryEntryService;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponseArm;
import nhs.uhdb.NHS_project.questionnaire.service.PreappointmentResponseService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseArmService;
import nhs.uhdb.NHS_project.questionnaire.service.QolResponseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AdminAccountController {

    private UserService userService;
    private TreatmentPlanService treatmentPlanService;
    private LymphoedemaTypeService lymphoedemaTypeService;
    private DiaryEntryService diaryEntryService;
    private PreappointmentResponseService preappointmentResponseService;
    private QolResponseService qolResponseService;

    private QolResponseArmService qolResponseArmService;
    private GoalService goalService;

    public AdminAccountController(UserService userService,QolResponseArmService qolResponseArmService, TreatmentPlanService treatmentPlanService, LymphoedemaTypeService lymphoedemaTypeService, DiaryEntryService diaryEntryService, PreappointmentResponseService preappointmentResponseService, QolResponseService qolResponseService, GoalService goalService) {
        this.userService = userService;
        this.treatmentPlanService = treatmentPlanService;
        this.lymphoedemaTypeService = lymphoedemaTypeService;
        this.diaryEntryService = diaryEntryService;
        this.preappointmentResponseService = preappointmentResponseService;
        this.qolResponseService =  qolResponseService;
        this.goalService = goalService;
        this.qolResponseArmService = qolResponseArmService;
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
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        TreatmentPlan userPlan = treatmentPlanService.getTreatmentPlanByUserId(id);
        if (userPlan == null) {
            userPlan = new TreatmentPlan();
            userPlan.setName("None");
        }

        LymphoedemaType userLymphoedemaType = lymphoedemaTypeService.getLymphoedemaTypeByUserId(id);
        if (userLymphoedemaType == null) {
            userLymphoedemaType = new LymphoedemaType();
            userLymphoedemaType.setName("None");
        }

        List<String> userDiaryEntries = diaryEntryService.getFormattedDiaryEntryDatesByUserId(id);

        List<PreappointmentResponse> questionnaires = preappointmentResponseService.getResponsesByUserId(id);

        List<QolResponse> qolQuestionnaires = qolResponseService.getResponsesByUserId(id);
        List<QolResponseArm> qolArmQuestionnaires = qolResponseArmService.getResponsesByUserId(id);

        List<Goal> goals = goalService.getGoalByUserId(id);

        mav.addObject("entryDates", userDiaryEntries);
        mav.addObject("userPlan", userPlan);
        mav.addObject("userLymphoedemaType", userLymphoedemaType);
        mav.addObject("user", user);
        mav.addObject("questionnaires", questionnaires);
        mav.addObject("qolQuestionnaires", qolQuestionnaires);
        mav.addObject("qolArmQuestionnaires", qolArmQuestionnaires);
        mav.addObject("goals", goals);
        return mav;
    }

    @GetMapping("/admin/{id}/diary/history/{date}")
    public ModelAndView getUserDiaryHistory(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        ModelAndView mav = new ModelAndView("admin/adminViewUserDiaryHistory");

        User user = userService.getUserByUserId(id);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        DiaryEntry diaryEntry = diaryEntryService.getDiaryEntryByUserIdAndDate(id, date);
        if (diaryEntry == null) diaryEntry = new DiaryEntry();
        mav.addObject("entry", diaryEntry);
        mav.addObject("user", user);
        mav.addObject("date", date);
        mav.addObject("imgUtil", new ImageUtil());
        return mav;
    }

    @GetMapping("/admin/{userId}/preappointment-questionnaire/{questionnaireId}")
    public ModelAndView getUserPreQuestionnaireHistory(@PathVariable Long userId, @PathVariable Long questionnaireId) {
        ModelAndView mav = new ModelAndView("questionnaires/viewPreappointmentQuestionnaire");

        User user = userService.getUserByUserId(userId);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        PreappointmentResponse response = preappointmentResponseService.getResponseById(questionnaireId);
        if (response == null) return new ModelAndView("admin/adminSearchUserError");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = response.getCreatedAt().format(formatter);

        mav.addObject("preappointmentQuestionnaireForm", response);
        mav.addObject("formattedDate", formattedDate);
        mav.addObject("user", user);

        return mav;
    }

    @GetMapping("/admin/{userId}/qol-questionnaire/{qolQuestionnaireId}")
    public ModelAndView getUserQolQuestionnaireHistory(@PathVariable Long userId, @PathVariable Long qolQuestionnaireId) {
        ModelAndView mav = new ModelAndView("admin/adminViewUserQolQuestionnaireLeg");

        User user = userService.getUserByUserId(userId);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        QolResponse qolResponse = qolResponseService.getResponseById(qolQuestionnaireId);
        if (qolResponse == null) return new ModelAndView("admin/adminSearchUserError");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = qolResponse.getCreatedAt().format(formatter);

        mav.addObject("qolQuestionnaireForm", qolResponse);
        mav.addObject("formattedDate", formattedDate);
        mav.addObject("user", user);

        return mav;
    }

    @GetMapping("/admin/{userId}/qol-questionnaire-arm/{qol_arm_id}")
    public ModelAndView getUserQolQuestionnaireArmHistory(@PathVariable Long userId, @PathVariable Long qol_arm_id) {
        ModelAndView mav = new ModelAndView("admin/adminViewUserQolQuestionnaireArm");

        User user = userService.getUserByUserId(userId);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        QolResponseArm qolResponseArm = qolResponseArmService.getResponseById(qol_arm_id);
        if (qolResponseArm == null) return new ModelAndView("admin/adminSearchUserError");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = qolResponseArm.getCreatedAt().format(formatter);

        mav.addObject("qolQuestionnaireArm", qolResponseArm);
        mav.addObject("formattedDate", formattedDate);
        mav.addObject("user", user);

        return mav;
    }


}
