package nhs.uhdb.NHS_project.our_services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OurServicesController {
    @GetMapping("/services")
    public ModelAndView getServicesPage() { return new ModelAndView("our_services/services"); }
    @GetMapping("/services/first-consultation-choose")
    public ModelAndView getFirstConsultationChoose() { return new ModelAndView("our_services/1stConsultationChoose"); }
    @GetMapping("/services/first-consultation-derby")
    public ModelAndView getFirstConsultationDerbyPage() { return new ModelAndView("our_services/1stConsultationDerby"); }
    @GetMapping("/services/first-consultation-nottingham")
    public ModelAndView getFirstConsultationNottingPage() { return new ModelAndView("our_services/1stConsultationNottingham"); }
    @GetMapping("/services/second-consultation")
    public ModelAndView getSecondConsultationPage() { return new ModelAndView("our_services/followConsultation"); }
    @GetMapping("/select-clinic")
    public String selectClinic() { return "select-clinic"; }
}
