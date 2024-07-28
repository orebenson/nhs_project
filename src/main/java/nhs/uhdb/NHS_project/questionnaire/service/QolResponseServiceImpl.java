package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponseRepository;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class QolResponseServiceImpl implements QolResponseService {

    private QolResponseRepository qolResponseRepository;

    public QolResponseServiceImpl(QolResponseRepository qolResponseRepository) {
        this.qolResponseRepository = qolResponseRepository;
    }

    @Override
    public QolResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return qolResponseRepository.getResponseByUserIdAndDate(user_id, date);
    }

    @Override
    public Long saveResponse(QolResponse qolResponse) {
        return qolResponseRepository.saveResponse(qolResponse);
    }

    @Override
    public List<QolResponse> getResponsesByUserId(Long user_id) {
        return qolResponseRepository.getResponsesByUserId(user_id);
    }


}
