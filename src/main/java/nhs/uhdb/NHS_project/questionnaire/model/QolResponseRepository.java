package nhs.uhdb.NHS_project.questionnaire.model;

import java.time.LocalDate;
import java.util.List;

public interface QolResponseRepository {
    QolResponse getResponseByUserIdAndDate (Long user_id, LocalDate date);
    Long saveResponse(QolResponse qolResponse);
    List<QolResponse> getResponsesByUserId(Long user_id);

}
