package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.CellulitisIncident;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponseRepository;

import java.time.LocalDate;
import java.util.List;

public interface PreappointmentResponseService {
    PreappointmentResponse getResponseByUserIdAndDate (Long user_id, LocalDate date);
    Long saveResponse(PreappointmentResponse preappointmentResponse);
    List<PreappointmentResponse> getResponsesByUserId(Long user_id);
    PreappointmentResponse getResponseById(Long id);
    List<CellulitisIncident> getCellulitisIncidentsByResponseId(Long responseId);

}
