package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponseRepository;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<QolResponse> getResponsesByUserId(Long userId) {
        List<QolResponse> responses = qolResponseRepository.getResponsesByUserId(userId);

        return responses.stream()
                .sorted(Comparator.comparing(QolResponse::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public QolResponse getResponseById(Long id) {
        return qolResponseRepository.getResponseById(id);
    }


}
