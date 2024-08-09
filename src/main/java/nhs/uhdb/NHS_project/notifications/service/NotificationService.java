package nhs.uhdb.NHS_project.notifications.service;

import nhs.uhdb.NHS_project.notifications.model.NotificationSettings;
import nhs.uhdb.NHS_project.accounts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotifications(User user, NotificationSettings notificationSettings) {
        if (notificationSettings.isDailyReminders()) {
            sendEmail(user.getEmail(), "Daily Reminder", "This is your daily reminder.");
        }
        if (notificationSettings.isAppointmentReminders()) {
            sendEmail(user.getEmail(), "Appointment Reminder", "This is your appointment reminder.");
        }
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
