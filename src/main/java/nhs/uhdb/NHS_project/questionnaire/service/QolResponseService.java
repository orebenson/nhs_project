package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.QolResponse;

import java.time.LocalDate;
import java.util.List;

public interface QolResponseService {
    QolResponse getResponseByUserIdAndDate (Long user_id, LocalDate date);
    Long saveResponse(QolResponse qolResponse);
    List<QolResponse> getResponsesByUserId(Long user_id);
    QolResponse getResponseById(Long id);
}
