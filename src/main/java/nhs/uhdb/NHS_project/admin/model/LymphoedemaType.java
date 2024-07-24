package nhs.uhdb.NHS_project.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import nhs.uhdb.NHS_project.diary.model.MeasurementType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class LymphoedemaType {
    private Long id;
    private String name;
    private String description;

    private List<MeasurementType> measurements;

    public LymphoedemaType() {
        this.id = null;
        this.name = "";
        this.description = "";
        this.measurements = new ArrayList<>();
    }
}
