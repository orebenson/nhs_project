package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.TreatmentPlan;

import java.util.List;

public interface TreatmentPlanService {
    Long createTreatmentPlan(TreatmentPlan plan);
    Boolean deleteTreatmentPlanById(Long id);
    List<TreatmentPlan> getAllTreatmentPlans();
    Boolean setUserTreatmentPlan(Long user_id, Long treatment_plan_id);
    TreatmentPlan getTreatmentPlanByUserId(Long user_id);
    TreatmentPlan getTreatmentPlanByUserEmail(String email);
}
