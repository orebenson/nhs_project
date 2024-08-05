package nhs.uhdb.NHS_project.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class Goal {
    private Long goalId;
    private Long userId;
    private String goalPart;
    private String goalDescription;
    private Integer goalMeasurement;
    private String goalUnit;
    private Date goalDeadline;

    public Goal() {
        this.goalId = null;
        this.userId = null;
        this.goalPart = "";
        this.goalDescription = "";
        this.goalMeasurement = null;
        this.goalUnit = "";
        this.goalDeadline = null;
    }
}