package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.QolResponseBreast;

import java.time.LocalDate;
import java.util.List;

public interface QolResponseBreastService {
    QolResponseBreast getResponseByUserIdAndDate (Long user_id, LocalDate date);
    Long saveResponse(QolResponseBreast qolResponseBreast);
    List<QolResponseBreast> getResponsesByUserId(Long user_id);
    QolResponseBreast getResponseById(Long id);
}
