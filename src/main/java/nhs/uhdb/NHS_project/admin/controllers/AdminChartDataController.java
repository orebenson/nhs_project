package nhs.uhdb.NHS_project.admin.controllers;

import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.admin.service.ChartService;
import nhs.uhdb.NHS_project.admin.service.TreatmentPlanService;
import nhs.uhdb.NHS_project.diary.model.ProgressData;
import nhs.uhdb.NHS_project.diary.services.DiaryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminChartDataController {

    private final DiaryEntryService diaryEntryService;
    private final UserService userService;
    private final ChartService chartService;

    public AdminChartDataController(UserService userService, DiaryEntryService diaryEntryService, ChartService chartService) {
        this.userService = userService;
        this.diaryEntryService = diaryEntryService;
        this.chartService = chartService;
    }

    @GetMapping("/progress")
    public ResponseEntity<List<ProgressData>> getProgressData(@RequestParam("metric") String metric, @RequestParam("userId") Long userId) {
        List<ProgressData> data = chartService.getMetricData(userId, metric);
        return ResponseEntity.ok(data);
    }
}
