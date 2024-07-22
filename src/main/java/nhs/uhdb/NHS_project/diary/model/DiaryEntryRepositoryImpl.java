package nhs.uhdb.NHS_project.diary.model;

import nhs.uhdb.NHS_project.admin.model.Exercise;
import nhs.uhdb.NHS_project.admin.model.ExerciseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DiaryEntryRepositoryImpl implements DiaryEntryRepository {
    private JdbcTemplate jdbc;
    private RowMapper<DiaryEntry> diaryEntryRowMapper;
    private ExerciseRepository exerciseRepository;

    public DiaryEntryRepositoryImpl(JdbcTemplate aJdbc, ExerciseRepository exerciseRepository) {
        this.jdbc = aJdbc;
        this.exerciseRepository = exerciseRepository;
        setDiaryEntryRowMapper();
    }

    private void setDiaryEntryRowMapper() {
        this.diaryEntryRowMapper = (resultSet, i) -> {
            DiaryEntry diaryEntry = new DiaryEntry();
            diaryEntry.setId(resultSet.getLong("diary_entry_id"));
            diaryEntry.setUser_id(resultSet.getLong("user_id"));
            diaryEntry.setWeight(resultSet.getInt("weight"));
            diaryEntry.setCreatedAt(resultSet.getDate("createdAt").toLocalDate());
            diaryEntry.setCellulitisDetails(resultSet.getString("cellulitisDetails"));
            diaryEntry.setDiscomfortDetails(resultSet.getString("mobilityDetails"));
            diaryEntry.setMobilityDetails(resultSet.getString("discomfortDetails"));
            diaryEntry.setQualityOfLifeScore(resultSet.getInt("qualityOfLifeScore"));
            diaryEntry.setWellnessScore(resultSet.getInt("wellnessScore"));
            diaryEntry.setCompletedExercises(exerciseRepository.getCompletedExercisesByDiaryEntryId(resultSet.getLong("diary_entry_id")));
            return diaryEntry;
        };
    }

    @Override
    public Long createDiaryEntry(DiaryEntry diaryEntry) {
        String createDiaryEntrySql = "INSERT INTO diary_entries (user_id, createdAt, weight, cellulitisDetails, mobilityDetails, discomfortDetails, wellnessScore, qualityOfLifeScore) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING diary_entry_id";
        String insertExerciseSql = "INSERT INTO diary_entry_exercises (diary_entry_id, exercise_id) VALUES (?, ?) RETURNING diary_entry_id";

        try {
            deleteDiaryEntryIfExists(diaryEntry.getUser_id(), diaryEntry.getCreatedAt());

            Long diaryEntryId = jdbc.queryForObject(createDiaryEntrySql, Long.class,
                    diaryEntry.getUser_id(),
                    Date.valueOf(diaryEntry.getCreatedAt()),
                    diaryEntry.getWeight(),
                    diaryEntry.getCellulitisDetails(),
                    diaryEntry.getMobilityDetails(),
                    diaryEntry.getDiscomfortDetails(),
                    diaryEntry.getWellnessScore(),
                    diaryEntry.getQualityOfLifeScore()
            );
            for (Exercise exercise : diaryEntry.getCompletedExercises()) {
                jdbc.update(insertExerciseSql, diaryEntryId, exercise.getId());
            }
            return diaryEntryId;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    private void deleteDiaryEntryIfExists(Long userId, LocalDate createdAt) {
        String removeExistingExercisesSql = "DELETE FROM diary_entry_exercises WHERE diary_entry_id = (SELECT diary_entry_id FROM diary_entries WHERE user_id = ? AND createdAt = ?)";
        String removeExistingEntrySql = "DELETE FROM diary_entries WHERE user_id = ? AND createdAt = ?";

        jdbc.update(removeExistingExercisesSql, userId, Date.valueOf(createdAt));
        jdbc.update(removeExistingEntrySql, userId, Date.valueOf(createdAt));
    }


    @Override
    public DiaryEntry getDiaryEntryByUserIdAndDate(Long user_id, LocalDate date) {
        String sql = "SELECT * FROM diary_entries WHERE user_id = ? AND createdAt = ?";
        try {
            return jdbc.queryForObject(sql, diaryEntryRowMapper, user_id, Date.valueOf(date));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<DiaryEntry> getDiaryEntriesByUserId(Long user_id) {
        String sql = "SELECT * FROM diary_entries WHERE user_id = ?";
        try {
            return jdbc.query(sql, diaryEntryRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


}
