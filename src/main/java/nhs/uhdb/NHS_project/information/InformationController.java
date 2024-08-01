package nhs.uhdb.NHS_project.information;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformationController {
    @GetMapping("/information")
    public ModelAndView getInformationPage() { return new ModelAndView("information/information"); }
    @GetMapping("/information/arm_lymphoedema")
    public ModelAndView getArmLymphodemaInformationPage() { return new ModelAndView("information/armLymphoedemaInformation"); }
    @GetMapping("/information/breast_lymphoedema")
    public ModelAndView getBreastLymphoedemaInformationPage() { return new ModelAndView("information/breastLymphoedemaInformation"); }
    @GetMapping("/information/head_and_neck_lymphoedema")
    public ModelAndView getHeadAndNeckLymphoedemaInformationPage() { return new ModelAndView("information/headAndNeckLymphoedemaInformation"); }
    @GetMapping("/information/leg_lymphoedema")
    public ModelAndView getLegLymphoedemaInformationPage() { return new ModelAndView("information/legLymphoedemaInformation"); }
    @GetMapping("/information/midline_and_genital_lymphoedema")
    public ModelAndView getMidlineAndGenitalLymphoedemaInformationPage() { return new ModelAndView("information/midlineAndGenitalLymphoedemaInformation"); }


}
