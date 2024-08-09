package nhs.uhdb.NHS_project.diary.controllers;

import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
import nhs.uhdb.NHS_project.diary.model.Appointment;
import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.model.ProgressData;
import nhs.uhdb.NHS_project.diary.services.AppointmentService;
import nhs.uhdb.NHS_project.diary.services.DiaryEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DiaryController {

    private UserService userService;
    private DiaryEntryService diaryEntryService;
    private TreatmentPlanService treatmentPlanService;
    private AppointmentService appointmentService;


    public DiaryController(UserService userService, DiaryEntryService diaryEntryService, TreatmentPlanService treatmentPlanService, AppointmentService appointmentService) {
        this.userService = userService;
        this.diaryEntryService = diaryEntryService;
        this.treatmentPlanService = treatmentPlanService;
        this.appointmentService = appointmentService;
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
        List<String> userAppointmentDates = appointmentService.getFormattedAppointmentDatesByUserId(user_id);
        List<Appointment> userAppointments = appointmentService.getAppointmentsByUserId(user_id);
        mav.addObject("todayEntered",todayEntered);
        mav.addObject("entryDates",userDiaryEntries);
        mav.addObject("appointmentDates",userAppointmentDates);
        mav.addObject("userAppointments",userAppointments);
        return mav;
    }

    @GetMapping("/diary/progress")
    public ResponseEntity<List<ProgressData>> getProgressData(@RequestParam("metric") String metric, Principal principal) {
        Long userId = userService.getUserIdByEmail(principal.getName());
        List<ProgressData> data = diaryEntryService.getMetricData(userId, metric);
        return ResponseEntity.ok(data);
    }


}
