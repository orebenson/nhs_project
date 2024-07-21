package nhs.uhdb.NHS_project.admin.model;

import nhs.uhdb.NHS_project.accounts.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ExerciseRepositoryImpl implements ExerciseRepository {
    private JdbcTemplate jdbc;
    private RowMapper<Exercise> exerciseRowMapper;

    private VideoRepository videoRepository;

    public ExerciseRepositoryImpl(JdbcTemplate aJdbc, VideoRepository videoRepository) {
        this.jdbc = aJdbc;
        this.videoRepository = videoRepository;
        setExerciseMapper();
    }

    private void setExerciseMapper() {
        this.exerciseRowMapper = (resultSet, i) -> {
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getLong("exercise_id"));
            exercise.setName(resultSet.getString("name"));
            exercise.setDescription(resultSet.getString("description"));
            exercise.setVideoLink(videoRepository.getVideoById(resultSet.getLong("video_id")).getVideoLink());
            return exercise;
        };
    }

    @Override
    public List<Exercise> getExercisesByTreatmentPlanId(Long treatmentPlanId) {
        String sql = "SELECT e.* FROM exercises e " +
                "JOIN treatment_plan_exercises tpe ON e.exercise_id = tpe.exercise_id " +
                "WHERE tpe.treatment_plan_id = ?";
        try {
            return jdbc.query(sql, exerciseRowMapper, treatmentPlanId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Long createExercise(Exercise exercise) {
        if (exercise.getName().equals("")) return null;

        Long videoId = videoRepository.createVideo(exercise.getVideoLink());

        String sqlExercise = "INSERT INTO exercises (name, description, video_id) VALUES (?, ?, ?) RETURNING exercise_id";
        Long exerciseId = jdbc.queryForObject(sqlExercise, Long.class, exercise.getName(), exercise.getDescription(), videoId);

        exercise.setId(exerciseId);
        return exerciseId;
    }

    @Override
    public Exercise getExerciseById(Long exercise_id) {
        String sql = "SELECT * FROM exercises WHERE exercise_id = ?";
        try {
            return jdbc.queryForObject(sql, exerciseRowMapper, exercise_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public Boolean deleteExerciseById(Long exercise_id) {
        String sql = "DELETE FROM exercises WHERE exercise_id = ?";
        int rowsAffected = jdbc.update(sql, exercise_id);
        return rowsAffected > 0;
    }

    @Override
    public List<Exercise> getTreatmentPlanExercisesByUserId(Long user_id) {
        String sql = "SELECT e.* FROM exercises e " +
                "JOIN treatment_plan_exercises tpe ON e.exercise_id = tpe.exercise_id " +
                "JOIN user_treatment_plans utp ON tpe.treatment_plan_id = utp.treatment_plan_id " +
                "WHERE utp.user_id = ?";
        try {
            return jdbc.query(sql, exerciseRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Exercise> getTreatmentPlanExercisesByUserEmail(String email) {
        String sql = "SELECT e.* FROM exercises e " +
                "JOIN treatment_plan_exercises tpe ON e.exercise_id = tpe.exercise_id " +
                "JOIN user_treatment_plans utp ON tpe.treatment_plan_id = utp.treatment_plan_id " +
                "JOIN user_table u ON utp.user_id = u.user_id " +
                "WHERE u.email = ?";
        try {
            return jdbc.query(sql, exerciseRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Exercise> getCompletedExercisesByDiaryEntryId(Long diary_entry_id) {
        String sql = "SELECT e.* FROM exercises e " +
                "JOIN diary_entry_exercises dee ON e.exercise_id = dee.exercise_id " +
                "WHERE dee.diary_entry_id = ?";
        try {
            return jdbc.query(sql, exerciseRowMapper, diary_entry_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
