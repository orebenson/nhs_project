package nhs.uhdb.NHS_project.our_policies_page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OurPoliciesPageController {
    @GetMapping("/policies")
    ModelAndView getPoliciesPage(){return new ModelAndView("our_policies/ourPolicies");}
}
