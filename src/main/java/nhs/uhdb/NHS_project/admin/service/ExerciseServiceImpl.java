package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.model.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise getExerciseById(Long exercise_id) {
        return exerciseRepository.getExerciseById(exercise_id);
    }

    @Override
    public Boolean deleteExerciseById(Long exercise_id) {
        return exerciseRepository.deleteExerciseById(exercise_id);
    }

    @Override
    public List<Exercise> getTreatmentPlanExercisesByUserId(Long user_id) {
        return exerciseRepository.getTreatmentPlanExercisesByUserId(user_id);
    }

    @Override
    public List<Exercise> getTreatmentPlanExercisesByUserEmail(String email) {
        return exerciseRepository.getTreatmentPlanExercisesByUserEmail(email);
    }
}
