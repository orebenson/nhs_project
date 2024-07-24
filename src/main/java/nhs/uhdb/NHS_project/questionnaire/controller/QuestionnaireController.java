package nhs.uhdb.NHS_project.questionnaire.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionnaireController {

    @GetMapping("/questionnaires/preappointment-questionnaire")
    public ModelAndView preAppointmentQuestionnaire() {
        return new ModelAndView("questionnaires/pre-appointmentQuestionnaire");
    }


    @GetMapping("/questionnaires/qol-questionnaire")
    public ModelAndView qolQuestionnaire() {
        return new ModelAndView("questionnaires/qolQuestionnaire");
    }


}
