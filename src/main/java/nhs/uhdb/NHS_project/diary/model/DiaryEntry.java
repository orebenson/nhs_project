package nhs.uhdb.NHS_project.diary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import nhs.uhdb.NHS_project.admin.model.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DiaryEntry {
    private Long id;
    private Long user_id;
    private LocalDate createdAt;
    private int weight;
    private String cellulitisDetails;
    private String mobilityDetails;
    private String discomfortDetails;
    private int wellnessScore;
    private int qualityOfLifeScore;
    private List<Exercise> completedExercises;

    public DiaryEntry() {
        this.id = null;
        this.user_id = null;
        this.createdAt = null;
        this.weight = 0;
        this.cellulitisDetails = "";
        this.mobilityDetails = "";
        this.discomfortDetails = "";
        this.wellnessScore = 0;
        this.qualityOfLifeScore = 0;
        this.completedExercises = new ArrayList<>();
    }

}
