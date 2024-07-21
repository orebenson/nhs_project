package nhs.uhdb.NHS_project.diary.controllers;

import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.services.DiaryEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DiaryController {

    private UserService userService;
    private DiaryEntryService diaryEntryService;
    private TreatmentPlanService treatmentPlanService;

    public DiaryController(UserService userService, DiaryEntryService diaryEntryService, TreatmentPlanService treatmentPlanService) {
        this.userService = userService;
        this.diaryEntryService = diaryEntryService;
        this.treatmentPlanService = treatmentPlanService;
    }

    @GetMapping("/diaryview")
    public ModelAndView getDiaryView(Principal principal) {
        ModelAndView mav = new ModelAndView("diary/diaryView");

        Long user_id = userService.getUserIdByEmail(principal.getName());

        DiaryEntry todayEntered = diaryEntryService.getDiaryEntryByUserIdAndDate(user_id, LocalDate.now());
        List<String> userDiaryEntries = diaryEntryService.getFormattedDiaryEntryDatesByUserId(user_id);
        mav.addObject("todayEntered",todayEntered);
        mav.addObject("entryDates",userDiaryEntries);
        return mav;
    }

    @GetMapping("/diary")
    public ModelAndView getDiary(Principal principal) {
        ModelAndView mav = new ModelAndView("diary/diary");

        Long user_id = userService.getUserIdByEmail(principal.getName());

        DiaryEntry todayEntered = diaryEntryService.getDiaryEntryByUserIdAndDate(user_id, LocalDate.now());
        List<String> userDiaryEntries = diaryEntryService.getFormattedDiaryEntryDatesByUserId(user_id);
        mav.addObject("todayEntered",todayEntered);
        mav.addObject("entryDates",userDiaryEntries);
        return mav;
    }


}
