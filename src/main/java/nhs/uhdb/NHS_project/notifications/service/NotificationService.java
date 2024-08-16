package nhs.uhdb.NHS_project.notifications.service;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.notifications.model.NotificationSettings;
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
            sendEmail(user.getEmail(), "Daily Reminder", "This is your daily reminder to update your diary.");
        }

        if (notificationSettings.isAppointmentReminders()) {
            sendEmail(user.getEmail(), "Appointment Reminder", "This is your appointment reminder.");
        }

        if (notificationSettings.isQuestionnaireReminders()) {
            sendEmail(user.getEmail(), "Questionnaire Reminder", "Please remember to complete your pending questionnaire.");
        }
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        System.out.println("Sending email to: " + to);
        message.setText(text);
        mailSender.send(message);
        System.out.println("Email sent successfully");
    }
}
