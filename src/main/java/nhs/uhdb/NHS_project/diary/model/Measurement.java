package nhs.uhdb.NHS_project.diary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Measurement {
    private Long id;
    private MeasurementType measurementType;
    private Long value;

    public Measurement() {
        this.id = null;
        this.measurementType = new MeasurementType();
        this.value = 0L;
    }


}
