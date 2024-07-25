package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.model.LymphoedemaType;
import nhs.uhdb.NHS_project.admin.model.TreatmentPlan;
import nhs.uhdb.NHS_project.admin.service.LymphoedemaTypeService;
import nhs.uhdb.NHS_project.diary.model.Measurement;
import nhs.uhdb.NHS_project.diary.model.MeasurementType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminLymphoedemaTypeController {

    private UserService userService;
    private LymphoedemaTypeService lymphoedemaTypeService;

    public AdminLymphoedemaTypeController(LymphoedemaTypeService lymphoedemaTypeService, UserService userService) {
        this.lymphoedemaTypeService = lymphoedemaTypeService;
        this.userService = userService;
    }

    @GetMapping("/admin/lymphoedema/add")
    public ModelAndView adminGetCreateLymphoedemaType() {
        ModelAndView mav = new ModelAndView("admin/adminAddLymphoedemaType");
        mav.addObject("lymphoedemaType", new LymphoedemaType());
//        mav.addObject("measurementTypes", new ArrayList<MeasurementType>());
        return mav;
    }

    @PostMapping("/admin/lymphoedema/add")
    public ModelAndView adminPostCreateLymphoedemaType(@ModelAttribute("lymphoedemaType") LymphoedemaType lymphoedemaType) {
        Long lymphoedemaTypeId = lymphoedemaTypeService.createLymphoedemaType(lymphoedemaType);
        if (lymphoedemaTypeId == null) return new ModelAndView("admin/adminAddLymphoedemaTypeError");
        return new ModelAndView("admin/adminAddLymphoedemaTypeSuccess");
    }

    @GetMapping("/admin/{user_id}/lymphoedema")
    public ModelAndView adminGetUserSetLymphoedemaType(@PathVariable Long user_id) {
        ModelAndView mav = new ModelAndView("admin/adminSetUserLymphoedemaType");
        User user = userService.getUserByUserId(user_id);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        List<LymphoedemaType> lymphoedemaTypes = lymphoedemaTypeService.getAllLymphoedemaTypes();
        if(lymphoedemaTypes.size() == 0) lymphoedemaTypes = new ArrayList<>();

        LymphoedemaType selectedLymphoedemaType = lymphoedemaTypeService.getLymphoedemaTypeByUserId(user_id);
        if(selectedLymphoedemaType == null)  {
            selectedLymphoedemaType = new LymphoedemaType();
            selectedLymphoedemaType.setName("None");
        }

        mav.addObject("lyphoedemaTypes", lymphoedemaTypes);
        mav.addObject("user", user);
        mav.addObject("selectedLymphoedemaType", selectedLymphoedemaType);
        return mav;
    }

    @PostMapping("/admin/{user_id}/lymphoedema")
    public ModelAndView adminPostUserSetLymphoedemaType(@PathVariable Long user_id, @ModelAttribute("selectedLymphoedemaType") LymphoedemaType selectedLymphoedemaType) {
        User user = userService.getUserByUserId(user_id);
        if (user == null) return new ModelAndView("admin/adminSearchUserError");

        Boolean result = lymphoedemaTypeService.setUserLymphoedemaType(user_id, selectedLymphoedemaType.getId());
        if(!result) return new ModelAndView("admin/adminSetUserLymphoedemaTypeError").addObject("user", user);
        return new ModelAndView("admin/adminSetUserLymphoedemaTypeSuccess").addObject("user", user);
    }

}
