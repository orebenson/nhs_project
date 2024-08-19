package nhs.uhdb.NHS_project.questionnaire.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionnairesController {


    //Method to handle GET requests for the questionnaires task hub page
//    @GetMapping("/questionnaires")
//    public ModelAndView questionnaires() {
//        return new ModelAndView("questionnaires/taskHubPage");
//    }
    /**     The above GET request for the questionnaires page has been moved to AccountController
     *      to keep consistency of the admin side, where it's in AdminAccountController.
    */

    @GetMapping("/questionnaires/success")
    public ModelAndView questionnaireSuccess() {
        return new ModelAndView("questionnaires/submissionSuccess");
    }

}
