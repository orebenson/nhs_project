package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PreappointmentResponseServiceImpl implements PreappointmentResponseService {

    private PreappointmentResponseRepository preappointmentResponseRepository;

    @Override
    public PreappointmentResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return null;
    }

    @Override
    public Long createResponse(PreappointmentResponse preappointmentResponse) {
        return null;
    }

    @Override
    public List<PreappointmentResponse> getResponsesbyUserId(Long user_id) {
        return null;
    }


}
