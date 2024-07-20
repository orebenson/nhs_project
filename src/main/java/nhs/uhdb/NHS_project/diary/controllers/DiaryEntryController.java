package nhs.uhdb.NHS_project.diary.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.service.ExerciseService;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.services.DiaryEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DiaryEntryController {

    private UserService userService;
    private ExerciseService exerciseService;
    private DiaryEntryService diaryEntryService;

    public DiaryEntryController(UserService userService, ExerciseService exerciseService, DiaryEntryService diaryEntryService) {
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.diaryEntryService = diaryEntryService;
    }
    @GetMapping("/diary/entry")
    public ModelAndView getDiaryEntry(Principal principal) {
        ModelAndView mav = new ModelAndView("diary/diaryEntry");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        List<Exercise> userExercises = exerciseService.getTreatmentPlanExercisesByUserId(loggedInUser.getUser_id());
        DiaryEntry newEntry = new DiaryEntry();
        mav.addObject("date", LocalDate.now());
        mav.addObject("newEntry", newEntry);
        mav.addObject("userExercises", userExercises);
        return mav;
    }

    @PostMapping("/diary/entry")
    public ModelAndView postDiaryEntry(Principal principal, @ModelAttribute("newEntry") DiaryEntry newEntry, @RequestParam(value = "selectedExercises", required = false) List<Long> selectedExercises)  {
        newEntry.setUser_id(userService.getUserIdByEmail(principal.getName()));
        newEntry.setCreatedAt(LocalDate.now());

        if (selectedExercises != null && !selectedExercises.isEmpty()) {
            List<Exercise> selectedExercisesList = new ArrayList<>();
            for (Long exerciseId : selectedExercises) {
                Exercise temp = new Exercise();
                temp.setId(exerciseId);
                selectedExercisesList.add(temp);
            }
            newEntry.setCompletedExercises(selectedExercisesList);
        }

        Long result = diaryEntryService.createDiaryEntry(newEntry);
        if(result == null) return new ModelAndView("diary/diaryEntryError");
        return new ModelAndView("diary/diaryEntrySuccess");
    }

}
