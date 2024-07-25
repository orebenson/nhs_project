package nhs.uhdb.NHS_project.questionnaire.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QolQuestionnaireController {

    @GetMapping("/questionnaires/qol-questionnaire")
    public ModelAndView questionnaire() {
        return new ModelAndView("questionnaires/qolQuestionnaire");
    }


}