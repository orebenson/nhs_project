package nhs.uhdb.NHS_project.accounts.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("account/register");
        mav.addObject("newUserObject", new User());
        return mav;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("newUserObject") User user) {
        Long user_id = userService.createUser(user);
        if (user_id == null) return new ModelAndView("redirect:/register/error");
        return new ModelAndView("redirect:/register/success");
    }

    @GetMapping("/register/success")
    public ModelAndView registerSuccess() {
        return new ModelAndView("account/registerSuccess");
    }

    @GetMapping("/register/error")
    public ModelAndView registerError() {
        return new ModelAndView("account/registerError");
    }

    @GetMapping("/register/consent")
    public ModelAndView registerConsent() {
        return new ModelAndView("account/registrationConsent");
    }

    @GetMapping("/register/consent/error")
    public ModelAndView consentError() {
        return new ModelAndView("account/consentError");
    }

    @PostMapping("/register/consent")
    public ModelAndView postConsentForm(
            @RequestParam(name = "photo-consent", defaultValue = "false") Boolean photoConsent,
            @RequestParam(name = "info-consent", defaultValue = "false") Boolean infoConsent,
            @RequestParam(name = "forgotten-history-consent", defaultValue = "false") Boolean forgottenHistoryConsent,
            @RequestParam(name = "abnormal-results-consent", defaultValue = "false") Boolean abnormalResultsConsent,
            @RequestParam(name = "share-information-consent", defaultValue = "false") Boolean shareInformationConsent,
            @RequestParam(name = "coercion-consent", defaultValue = "false") Boolean coercionConsent,
            @RequestParam(name = "misunderstood-information-consent", defaultValue = "false") Boolean misunderstoodInformationConsent,
            @RequestParam(name = "info-about-others-consent", defaultValue = "false") Boolean infoAboutOthersConsent,
            @RequestParam(name = "proxy-access-consent", defaultValue = "false") Boolean proxyAccessConsent
    ) {
        boolean consentAccepted = photoConsent || infoConsent || forgottenHistoryConsent ||
                abnormalResultsConsent || shareInformationConsent || coercionConsent ||
                misunderstoodInformationConsent || infoAboutOthersConsent || proxyAccessConsent;

        if (!consentAccepted) return new ModelAndView("redirect:/register/consent/error");

        return new ModelAndView("redirect:/register");
    }

}