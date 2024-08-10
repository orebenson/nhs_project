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
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());

        if (notificationSettings.isDailyReminders()) {
            message.setSubject("Daily Reminder");
            message.setText("This is your daily reminder.");
            mailSender.send(message);
        }

        if (notificationSettings.isAppointmentReminders()) {
            message.setSubject("Appointment Reminder");
            message.setText("This is your appointment reminder.");
            mailSender.send(message);
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
