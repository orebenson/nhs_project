package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.diary.model.DiaryEntryRepository;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PreappointmentResponseServiceImpl implements PreappointmentResponseService {

    private PreappointmentResponseRepository preappointmentResponseRepository;

    public PreappointmentResponseServiceImpl(PreappointmentResponseRepository preappointmentResponseRepository) {
        this.preappointmentResponseRepository = preappointmentResponseRepository;
    }

    @Override
    public PreappointmentResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return preappointmentResponseRepository.getResponseByUserIdAndDate(user_id, date);
    }

    @Override
    public Long saveResponse(PreappointmentResponse preappointmentResponse) {
        return preappointmentResponseRepository.saveResponse(preappointmentResponse);
    }

    @Override
    public List<PreappointmentResponse> getResponsesByUserId(Long user_id) {
        return preappointmentResponseRepository.getResponsesByUserId(user_id);
    }


}
