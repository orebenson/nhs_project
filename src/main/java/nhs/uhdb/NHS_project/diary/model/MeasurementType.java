package nhs.uhdb.NHS_project.diary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeasurementType {
    private Long id;
    private String name;
    private String description;
    private String unit;

    public MeasurementType() {
        this.id = null;
        this.description = "";
        this.name = "";
        this.unit = "";
    }
}
