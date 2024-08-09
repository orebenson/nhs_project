package nhs.uhdb.NHS_project.diary.controllers;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.service.ExerciseService;
import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.model.Photo;
import nhs.uhdb.NHS_project.diary.model.ProgressData;
import nhs.uhdb.NHS_project.diary.model.Measurement;
import nhs.uhdb.NHS_project.diary.services.AppointmentService;
import nhs.uhdb.NHS_project.diary.services.DiaryEntryService;
import nhs.uhdb.NHS_project.diary.services.MeasurementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


@Controller
public class DiaryEntryController {

    private UserService userService;
    private ExerciseService exerciseService;
    private DiaryEntryService diaryEntryService;
    private MeasurementService measurementService;


    public DiaryEntryController(UserService userService, ExerciseService exerciseService, DiaryEntryService diaryEntryService, MeasurementService measurementService) {
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.diaryEntryService = diaryEntryService;
        this.measurementService = measurementService;

    }

    @GetMapping("/diary/entry")
    public ModelAndView getDiaryEntry(Principal principal) {
        ModelAndView mav = new ModelAndView("diary/diaryEntry");
        String loggedInUserEmail = principal.getName();
        User loggedInUser = userService.getUserByEmail(loggedInUserEmail);

        List<Exercise> userExercises = exerciseService.getTreatmentPlanExercisesByUserId(loggedInUser.getUser_id());
        List<Measurement> userMeasurements = measurementService.getEmptyMeasurementsByUserId(loggedInUser.getUser_id());
        DiaryEntry newEntry = new DiaryEntry();
        newEntry.setMeasurements(userMeasurements);
        mav.addObject("date", LocalDate.now());
        mav.addObject("newEntry", newEntry);
        mav.addObject("userExercises", userExercises);
        return mav;
    }

    @PostMapping("/diary/entry")
    public ModelAndView postDiaryEntry(Principal principal, @ModelAttribute("newEntry") DiaryEntry newEntry,
                                       @RequestParam(value = "selectedExercises", required = false) List<Long> selectedExercises,
                                       @RequestParam(value = "measurements", required = false) List<Measurement> measurements,
                                       @RequestParam(value = "photoFile0", required = false) MultipartFile photoFile0,
                                       @RequestParam(value = "photoFile1", required = false) MultipartFile photoFile1,
                                       @RequestParam(value = "photoFile2", required = false) MultipartFile photoFile2
    ) throws IOException {
        newEntry.setUser_id(userService.getUserIdByEmail(principal.getName()));
        newEntry.setCreatedAt(LocalDate.now());

        List<MultipartFile> photoFiles = List.of(photoFile0, photoFile1, photoFile2);
        for (MultipartFile photoFile : photoFiles) {
            if (photoFile != null && !photoFile.isEmpty()) {
                BufferedImage bufferedImage = ImageIO.read(photoFile.getInputStream());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpeg", byteArrayOutputStream);

                Photo photo = new Photo();
                photo.setBytes(photoFile.getBytes());
                newEntry.getPhotos().add(photo);
            }
        }

        if (measurements != null && !measurements.isEmpty()) {
            newEntry.setMeasurements(measurements);
        }

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
        if (result == null) return new ModelAndView("diary/diaryEntryError");
        return new ModelAndView("diary/diaryEntrySuccess");
    }

    @GetMapping("/diary/history/{date}")
    public ModelAndView getDiaryHistory(Principal principal, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        Long user_id = userService.getUserIdByEmail(principal.getName());
        ModelAndView mav = new ModelAndView("diary/diaryHistory");
        DiaryEntry diaryEntry = diaryEntryService.getDiaryEntryByUserIdAndDate(user_id, date);
        if (diaryEntry == null) diaryEntry = new DiaryEntry();
        mav.addObject("entry", diaryEntry);
        mav.addObject("date", date);
        mav.addObject("imgUtil", new ImageUtil());
        return mav;
    }



}
