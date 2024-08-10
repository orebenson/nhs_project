package nhs.uhdb.NHS_project.notifications.controller;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.notifications.model.NotificationSettings;
import nhs.uhdb.NHS_project.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit-notifications")
    public ModelAndView submitNotificationSettings(@RequestParam("dailyReminders") boolean dailyReminders, @RequestParam("appointmentReminders") boolean appointmentReminders, Principal principal) {
        Long userId = userService.getUserIdByEmail(principal.getName());
        User user = userService.getUserByUserId(userId);

        NotificationSettings settings = new NotificationSettings();
        settings.setDailyReminders(dailyReminders);
        settings.setAppointmentReminders(appointmentReminders);

        notificationService.sendNotifications(user, settings);

        ModelAndView mav = new ModelAndView("redirect:/admin/viewUser");
        mav.addObject("notificationSettings", settings);
        return mav;
    }
}
