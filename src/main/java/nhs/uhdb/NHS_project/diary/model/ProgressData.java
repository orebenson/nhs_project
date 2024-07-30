package nhs.uhdb.NHS_project.diary.model;

import java.time.LocalDate;

public class ProgressData {
    private LocalDate date;
    private double value;

    // Getter and setter for date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter and setter for value
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
