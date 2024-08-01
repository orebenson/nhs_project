package nhs.uhdb.NHS_project.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class Goal {
    private Long id;
    private String goal_part;
    private String goal_description;
    private int goal_measurement;
    private String goal_unit;
    private LocalDate deadline;

    public Goal() {
    this.id = null;
    this.goal_part = "";
    this.goal_description = "";
    this.goal_measurement = 0;
    this.goal_unit = "";
    this.deadline = null;
    }

}
