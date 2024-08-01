package nhs.uhdb.NHS_project.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class Goal {
    private Long id;
    private int weight;
    private LocalDate deadline;

    public Goal() {
    this.id = null;
    this.weight = 0;
    this.deadline = null;
    }

}
