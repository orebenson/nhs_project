package nhs.uhdb.NHS_project.admin.model;

import java.util.List;

public interface GoalRepository {
    Long createGoal(Goal goal);
    Boolean deleteGoalById(Long goalId);
    List<Goal> getAllGoals();
    Boolean setUserGoal(Long userId, Long goalId);
    List<Goal> getGoalByUserId(Long userId);
}