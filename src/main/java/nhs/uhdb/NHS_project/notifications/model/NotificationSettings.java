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

    private boolean questionnaireReminders;

    public boolean isQuestionnaireReminders() {
        return questionnaireReminders;
    }

    public void setQuestionnaireReminders(boolean questionnaireReminders) {
        this.questionnaireReminders = questionnaireReminders;
    }

    public boolean isGoalReminders() {
        return goalReminders;
    }

    public void setGoalReminders(boolean goalReminders) {
        this.goalReminders = goalReminders;
    }

}
