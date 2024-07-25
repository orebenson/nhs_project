package nhs.uhdb.NHS_project.questionnaire.controller;
import nhs.uhdb.NHS_project.accounts.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskHub {

    @GetMapping("/questionnaires")
    public ModelAndView tasks() {
        return new ModelAndView("questionnaires/taskHubPage");
    }

//    @GetMapping("/taskhub/questionnaire-submitted")
//    public ModelAndView questionnaireSuccess() {
//        return new ModelAndView("questionnaires/submissionSuccess");
//    }

}
