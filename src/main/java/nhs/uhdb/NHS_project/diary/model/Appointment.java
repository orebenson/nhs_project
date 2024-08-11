package nhs.uhdb.NHS_project.diary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Appointment {
    private Long id;
    private String type;
    private String description;
    private LocalDate date;

    public Appointment() {
        this.id = null;
        this.type = "";
        this.description = "";
        this.date = null;
    }
}
