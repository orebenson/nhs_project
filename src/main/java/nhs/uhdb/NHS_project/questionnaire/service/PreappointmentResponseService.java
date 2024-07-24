package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;

import java.time.LocalDate;
import java.util.List;

public interface PreappointmentResponseService {
    PreappointmentResponse getResponseByUserIdAndDate (Long user_id, LocalDate date);
    Long createResponse(PreappointmentResponse preappointmentResponse);
    List<PreappointmentResponse> getResponsesbyUserId(Long user_id);


}
