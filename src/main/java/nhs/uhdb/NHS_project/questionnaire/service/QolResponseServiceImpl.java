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

    //Constructor to initialise QolResponseRepository
    public QolResponseServiceImpl(QolResponseRepository qolResponseRepository) {
        this.qolResponseRepository = qolResponseRepository;
    }

    //Method to retrieve a QolResponse by user ID and date
    @Override
    public QolResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return qolResponseRepository.getResponseByUserIdAndDate(user_id, date);
    }

    //Method to save a new QolResponse to the database and return its ID
    @Override
    public Long saveResponse(QolResponse qolResponse) {
        return qolResponseRepository.saveResponse(qolResponse);
    }

    //Method to retrieve all QolResponses by user ID
    @Override
    public List<QolResponse> getResponsesByUserId(Long user_id) {
        return qolResponseRepository.getResponsesByUserId(user_id);
    }


}
