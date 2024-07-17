package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.TreatmentPlan;
import nhs.uhdb.NHS_project.admin.model.TreatmentPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentPlanServiceImpl implements TreatmentPlanService{

    private TreatmentPlanRepository treatmentPlanRepository;

    public TreatmentPlanServiceImpl(TreatmentPlanRepository treatmentPlanRepository) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }
    @Override
    public Long createTreatmentPlan(TreatmentPlan plan) {
        return treatmentPlanRepository.createTreatmentPlan(plan);
    }

    @Override
    public Boolean deleteTreatmentPlanById(Long id) {
        return treatmentPlanRepository.deleteTreatmentPlanById(id);
    }

    @Override
    public List<TreatmentPlan> getAllTreatmentPlans() {
        return treatmentPlanRepository.getAllTreatmentPlans();
    }

    @Override
    public Boolean setUserTreatmentPlan(Long user_id, Long treatment_plan_id) {
        return treatmentPlanRepository.setUserTreatmentPlan(user_id, treatment_plan_id);
    }

    @Override
    public TreatmentPlan getTreatmentPlanByUserId(Long user_id) {
        return treatmentPlanRepository.getTreatmentPlanByUserId(user_id);
    }

    @Override
    public TreatmentPlan getTreatmentPlanByUserEmail(String email) {
        return treatmentPlanRepository.getTreatmentPlanByUserEmail(email);
    }
}
