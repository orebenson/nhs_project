package nhs.uhdb.NHS_project.questionnaire.model;

import java.time.LocalDate;
import java.util.List;

public interface PreappointmentResponseRepository {
    PreappointmentResponse getResponseByUserIdAndDate (Long user_id, LocalDate date);
    Long saveResponse(PreappointmentResponse preappointmentResponse);
    List<PreappointmentResponse> getResponsesByUserId(Long user_id);

}
