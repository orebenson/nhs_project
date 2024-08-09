package nhs.uhdb.NHS_project.notifications.model;

public class NotificationSettings {
    private boolean dailyReminders;
    private boolean appointmentReminders;

    // Getters and Setters
    public boolean isDailyReminders() {
        return dailyReminders;
    }

    public void setDailyReminders(boolean dailyReminders) {
        this.dailyReminders = dailyReminders;
    }

    public boolean isAppointmentReminders() {
        return appointmentReminders;
    }

    public void setAppointmentReminders(boolean appointmentReminders) {
        this.appointmentReminders = appointmentReminders;
    }
}
