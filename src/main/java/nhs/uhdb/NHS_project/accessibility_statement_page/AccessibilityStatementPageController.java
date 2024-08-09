package nhs.uhdb.NHS_project.accessibility_statement_page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessibilityStatementPageController {
    @GetMapping("/accessibility")
    ModelAndView getAccessibilityPage(){return new ModelAndView("accessibility_statement/accessibilityStatement");}
}
