package nhs.uhdb.NHS_project.notifications.controller;

import nhs.uhdb.NHS_project.notifications.model.NotificationSettings;
import nhs.uhdb.NHS_project.notifications.service.NotificationService;
import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.accounts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit-notifications")
    public ResponseEntity<String> submitNotifications(@ModelAttribute NotificationSettings notificationSettings, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        notificationService.sendNotifications(user, notificationSettings);
        return ResponseEntity.ok("Settings saved and notifications sent successfully!");
    }
}
