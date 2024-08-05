package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.Goal;
import nhs.uhdb.NHS_project.admin.model.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Long createGoal(Goal goal) {
        return goalRepository.createGoal(goal);
    }

    @Override
    public Boolean deleteGoalById(Long goalId) {
        return goalRepository.deleteGoalById(goalId);
    }

    @Override
    public List<Goal> getAllGoals() {
        return goalRepository.getAllGoals();
    }

    @Override
    public Boolean setUserGoal(Long userId, Long goalId) {
        return goalRepository.setUserGoal(userId, goalId);
    }

    @Override
    public Goal getGoalByUserId(Long userId) {
        return goalRepository.getGoalByUserId(userId);
    }

    @Override
    public Goal getGoalByUserEmail(String email) {
        return goalRepository.getGoalByUserEmail(email);
    }
}
