package nhs.uhdb.NHS_project.admin.model;

import java.util.List;

public interface ExerciseRepository {
    List<Exercise> getExercisesByTreatmentPlanId(Long treatmentPlanId);
    Long createExercise(Exercise exercise);
    Exercise getExerciseById(Long exercise_id);
    Boolean deleteExerciseById(Long exercise_id);

    List<Exercise> getTreatmentPlanExercisesByUserId(Long user_id);

    List<Exercise> getTreatmentPlanExercisesByUserEmail(String email);
}
