package nhs.uhdb.NHS_project.diary.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.service.ExerciseService;
import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.services.DiaryEntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/diary/history/{date}") // date = YYYY-MM-DD
    public ModelAndView getDiaryHistory(Principal principal, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        Long user_id = userService.getUserIdByEmail(principal.getName());
        ModelAndView mav = new ModelAndView("diary/diaryHistory");
        DiaryEntry diaryEntry = diaryEntryService.getDiaryEntryByUserIdAndDate(user_id, date);
        if (diaryEntry == null) diaryEntry = new DiaryEntry();
        mav.addObject("entry", diaryEntry);
        mav.addObject("date", date);
        return mav;
    }

}
