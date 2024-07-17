package nhs.uhdb.NHS_project.admin.model;

import java.util.List;

public interface TreatmentPlanRepository {
    Long createTreatmentPlan(TreatmentPlan plan);
    Boolean deleteTreatmentPlanById(Long id);
    List<TreatmentPlan> getAllTreatmentPlans();
    Boolean setUserTreatmentPlan(Long user_id, Long treatment_plan_id);
    TreatmentPlan getTreatmentPlanByUserId(Long user_id);
    TreatmentPlan getTreatmentPlanByUserEmail(String email);
}
