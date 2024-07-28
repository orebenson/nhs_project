package nhs.uhdb.NHS_project.questionnaire.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionnairesController {

    @GetMapping("/questionnaires")
    public ModelAndView tasks() {
        return new ModelAndView("questionnaires/taskHubPage");
    }

}
