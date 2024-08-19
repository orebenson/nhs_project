package nhs.uhdb.NHS_project.notifications.controller;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.notifications.model.NotificationSettings;
import nhs.uhdb.NHS_project.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notifications/submit-notifications")
    public String submitNotificationSettings(
            // Parameters
            @RequestParam("userId") Long userId,
            @RequestParam(value = "dailyReminders", required = false, defaultValue = "false") boolean dailyReminders,
            @RequestParam(value = "appointmentReminders", required = false, defaultValue = "false") boolean appointmentReminders,
            @RequestParam(value = "questionnaireReminders", required = false, defaultValue = "false") boolean questionnaireReminders,
            @RequestParam(value = "goalReminders", required = false, defaultValue = "false") boolean goalReminders) {

        User user = userService.getUserByUserId(userId); // Fetch user from the database
        if (user == null) {
            log.error("User not found for userId: {}", userId);
            return "redirect:/admin/adminViewUser?error=User+not+found";
        }

        NotificationSettings notificationSettings = new NotificationSettings();
        notificationSettings.setDailyReminders(dailyReminders);
        notificationSettings.setAppointmentReminders(appointmentReminders);
        notificationSettings.setQuestionnaireReminders(questionnaireReminders);
        notificationSettings.setGoalReminders(goalReminders);

        // Log the email to ensure it's being fetched correctly
        log.info("Sending notifications to email: {}", user.getEmail());

        notificationService.sendNotifications(user, notificationSettings);

        return "redirect:/admin/search/" + userId + "#details";
    }

}
