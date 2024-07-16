package nhs.uhdb.NHS_project.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TreatmentPlan {
    private Long id;
    private String name;
    private String description;
    private List<Exercise> exercises;
    public TreatmentPlan() {
        this.id = null;
        this.exercises = new ArrayList<>();
        this.description = "";
        this.name = "";
    }
}
