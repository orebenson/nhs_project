package nhs.uhdb.NHS_project.questionnaire.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PreAppointmentQuestionnaire {

    @GetMapping("/questionnaires")
    public ModelAndView questionnaire() {
        return new ModelAndView("questionnaires/pre-appointmentQuestionnaire");
    }

//    @PostMapping("/questionnaires")
//    public ModelAndView submitForm(){
//    return new
//    }

}
