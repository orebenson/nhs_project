package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise getExerciseById(Long exercise_id);
    Boolean deleteExerciseById(Long exercise_id);

    List<Exercise> getTreatmentPlanExercisesByUserId(Long user_id);

    List<Exercise> getTreatmentPlanExercisesByUserEmail(String email);
}
