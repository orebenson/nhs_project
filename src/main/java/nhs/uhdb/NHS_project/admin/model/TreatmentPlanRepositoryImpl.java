package nhs.uhdb.NHS_project.admin.model;

import nhs.uhdb.NHS_project.accounts.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreatmentPlanRepositoryImpl implements TreatmentPlanRepository {

    private JdbcTemplate jdbc;
    private RowMapper<TreatmentPlan> treatmentPlanMapper;
    private ExerciseRepository exerciseRepository;
    public TreatmentPlanRepositoryImpl(JdbcTemplate aJdbc, ExerciseRepository exerciseRepository) {
        this.jdbc = aJdbc;
        this.exerciseRepository = exerciseRepository;
        setTreatmentPlanMapper();
    }

    private void setTreatmentPlanMapper() {
        this.treatmentPlanMapper = (resultSet, i) -> {
            TreatmentPlan treatmentPlan = new TreatmentPlan();
            treatmentPlan.setId(resultSet.getLong("treatment_plan_id"));
            treatmentPlan.setName(resultSet.getString("name"));
            treatmentPlan.setDescription(resultSet.getString("description"));
            treatmentPlan.setExercises(exerciseRepository.getExercisesByTreatmentPlanId(resultSet.getLong("treatment_plan_id")));
            return treatmentPlan;
        };
    }

    @Override
    public Long createTreatmentPlan(TreatmentPlan plan) {
        return (plan.getId() != null) ? updateTreatmentPlan(plan) : insertTreatmentPlan(plan);
    }

    private Long insertTreatmentPlan(TreatmentPlan plan) {
        String sql = "INSERT INTO treatment_plans (name, description) VALUES (?, ?) RETURNING treatment_plan_id";
        Long treatmentPlanId = jdbc.queryForObject(sql, Long.class, plan.getName(), plan.getDescription());
        plan.setId(treatmentPlanId);
        createExercisesForTreatmentPlan(plan);
        return treatmentPlanId;
    }

    private void createExercisesForTreatmentPlan(TreatmentPlan plan) {
        for (Exercise exercise : plan.getExercises()) {
            Long exercise_id = exerciseRepository.createExercise(exercise);
            if(exercise_id == null) return;
            String sql = "INSERT INTO treatment_plan_exercises (treatment_plan_id, exercise_id) VALUES (?, ?)";
            jdbc.update(sql, plan.getId(), exercise_id);
        }
    }

    private Long updateTreatmentPlan(TreatmentPlan plan) {
        String sql = "UPDATE treatment_plans SET name = ?, description = ?, WHERE treatment_plan_id = ?";
        jdbc.update(sql, plan.getName(), plan.getDescription(), plan.getId());
        createExercisesForTreatmentPlan(plan);
        return plan.getId();
    }

    @Override
    public Boolean deleteTreatmentPlanById(Long id) {
        String sqlDeleteExercises = "DELETE FROM treatment_plan_exercises WHERE treatment_plan_id = ?";
        int rowsAffected = jdbc.update(sqlDeleteExercises, id);
        return rowsAffected == 0;
    }

    @Override
    public List<TreatmentPlan> getAllTreatmentPlans() {
        String sql = "SELECT * FROM treatment_plans";
        return jdbc.query(sql, treatmentPlanMapper);
    }

    @Override
    public Boolean setUserTreatmentPlan(Long user_id, Long treatment_plan_id) {
        if(treatment_plan_id == 0) return true;
        String sql = "INSERT INTO user_treatment_plans (user_id, treatment_plan_id) VALUES (?, ?)";
        int rowsAffected = jdbc.update(sql, user_id, treatment_plan_id);
        return rowsAffected > 0;
    }

    @Override
    public TreatmentPlan getTreatmentPlanByUserId(Long user_id) {
        String sql = "SELECT tp.* FROM treatment_plans tp " +
                "JOIN user_treatment_plans utp ON tp.treatment_plan_id = utp.treatment_plan_id " +
                "WHERE utp.user_id = ?";
        try {
            return jdbc.queryForObject(sql, treatmentPlanMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public TreatmentPlan getTreatmentPlanByUserEmail(String email) {
        String sql = "SELECT tp.* FROM treatment_plans tp " +
                "JOIN user_treatment_plans utp ON tp.treatment_plan_id = utp.treatment_plan_id " +
                "JOIN user_table u ON utp.user_id = u.user_id " +
                "WHERE u.email = ?";
        try {
            return jdbc.queryForObject(sql, treatmentPlanMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}
