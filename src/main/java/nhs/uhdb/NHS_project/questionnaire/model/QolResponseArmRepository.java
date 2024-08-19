package nhs.uhdb.NHS_project.questionnaire.model;

import java.time.LocalDate;
import java.util.List;

public interface QolResponseArmRepository {
    QolResponseArm getResponseByUserIdAndDate (Long user_id, LocalDate date);
    Long saveResponse(QolResponseArm qolResponseArm);
    List<QolResponseArm> getResponsesByUserId(Long user_id);
    QolResponseArm getResponseById(Long id);
}
