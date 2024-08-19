package nhs.uhdb.NHS_project.admin.model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GoalRepositoryImpl implements GoalRepository {

    private JdbcTemplate jdbc;
    private RowMapper<Goal> goalRowMapper;

    public GoalRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        setGoalRowMapper();
    }

    private void setGoalRowMapper() {
        this.goalRowMapper = (resultSet, i) -> {
            Goal goal = new Goal();
            goal.setGoalId(resultSet.getLong("goal_id"));
            goal.setUserId(resultSet.getLong("user_id"));
            goal.setGoalDescription(resultSet.getString("goal_description"));
            goal.setGoalDeadline(resultSet.getString("goal_deadline"));
            return goal;
        };
    }

    @Override
    public Long createGoal(Goal goal) {
        return (goal.getGoalId() != null) ? updateGoal(goal) : insertGoal(goal);
    }

    private Long insertGoal(Goal goal) {
        String insertSql = "INSERT INTO admin_goal_setting (user_id, goal_description, goal_deadline) VALUES (?, ?, ?) RETURNING goal_id";
        try {
            Long goalId = jdbc.queryForObject(insertSql, Long.class, goal.getUserId(), goal.getGoalDescription(), goal.getGoalDeadline());
            goal.setGoalId(goalId);
            return goalId;
        } catch (Error e) {
            return null;
        }
    }

    private Long updateGoal(Goal goal) {
        String updateSql = "UPDATE admin_goal_setting SET user_id = ?, goal_description = ?, goal_deadline = ? WHERE goal_id = ?";
        try {
            jdbc.update(updateSql, goal.getUserId(), goal.getGoalDescription(), goal.getGoalDeadline(), goal.getGoalId());
            return goal.getGoalId();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean deleteGoalById(Long goalId) {
        String sqlDeleteGoal = "DELETE FROM admin_goal_setting WHERE goal_id = ?";
        int rowsAffected = jdbc.update(sqlDeleteGoal, goalId);
        return rowsAffected > 0;
    }

    @Override
    public List<Goal> getAllGoals() {
        String sql = "SELECT * FROM admin_goal_setting";
        return jdbc.query(sql, goalRowMapper);
    }

    @Override
    public Boolean setUserGoal(Long userId, Long goalId) {
        if (goalId == 0) return true;
        try {
            String deleteSql = "DELETE FROM user_goals WHERE user_id = ?";
            jdbc.update(deleteSql, userId);

            String sql = "INSERT INTO user_goals (user_id, goal_id) VALUES (?, ?)";
            int rowsAffected = jdbc.update(sql, userId, goalId);
            return rowsAffected > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public List<Goal> getGoalByUserId(Long userId) {
        String sql = "SELECT g.* FROM admin_goal_setting g " +
                "WHERE g.user_id = ?";
        try {
            return jdbc.query(sql, goalRowMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
