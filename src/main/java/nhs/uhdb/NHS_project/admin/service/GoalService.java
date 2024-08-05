package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.Goal;

import java.util.List;

public interface GoalService {
    Long createGoal(Goal goal);
    Boolean deleteGoalById(Long goalId);
    List<Goal> getAllGoals();
    Boolean setUserGoal(Long userId, Long goalId);
    Goal getGoalByUserId(Long userId);
    Goal getGoalByUserEmail(String email);
}
