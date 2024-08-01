package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.diary.model.DiaryEntryRepository;
import nhs.uhdb.NHS_project.questionnaire.model.CellulitisIncident;
import nhs.uhdb.NHS_project.questionnaire.model.CellulitisIncidentRepository;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PreappointmentResponseServiceImpl implements PreappointmentResponseService {

    private PreappointmentResponseRepository preappointmentResponseRepository;
    private CellulitisIncidentRepository cellulitisIncidentRepository;

    //Using logger for debugging
    private static final Logger LOGGER = Logger.getLogger(PreappointmentResponseServiceImpl.class.getName());

    //Constructor to initialise repositories
    public PreappointmentResponseServiceImpl(PreappointmentResponseRepository preappointmentResponseRepository, CellulitisIncidentRepository cellulitisIncidentRepository) {
        this.preappointmentResponseRepository = preappointmentResponseRepository;
        this.cellulitisIncidentRepository = cellulitisIncidentRepository;
    }

    //Method to retrieve a PreappointmentResponse by user ID and date
    @Override
    public PreappointmentResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return preappointmentResponseRepository.getResponseByUserIdAndDate(user_id, date);
    }

    //Method to save a new PreappointmentResponse and associated CellulitisIncidents to the database
    @Override
    public Long saveResponse(PreappointmentResponse preappointmentResponse) {
        Long responseId = preappointmentResponseRepository.saveResponse(preappointmentResponse);
        for (CellulitisIncident incident : preappointmentResponse.getEpisodes()) {
            Long incidentId = cellulitisIncidentRepository.saveIncident(incident, responseId);

            //Log the successful save of the CellulitisIncident
            LOGGER.info("Saved CellulitisIncident with ID: " + incidentId);
        }
        return responseId;
    }

    //Method to retrieve all PreappointmentResponses by user ID and sort the list in descending date format
    @Override
    public List<PreappointmentResponse> getResponsesByUserId(Long userId) {
        List<PreappointmentResponse> responses = preappointmentResponseRepository.getResponsesByUserId(userId);

//        //Debugging: Print responses before sorting
//        System.out.println("Responses before sorting:");
//        responses.forEach(response -> System.out.println(response.getCreatedAt()));

        //Sorting the responses into another stream using Java Streams API.
        //The sorted list returns a new sorted stream which is then collected back into the list, leaving the original list unchanged,thereby preserving it.
        List<PreappointmentResponse> sortedResponses = responses.stream()
                .sorted(Comparator.comparing(PreappointmentResponse::getCreatedAt).reversed())
                .collect(Collectors.toList());

//        //Debugging: Print responses after sorting
//        System.out.println("Responses after sorting:");
//        sortedResponses.forEach(response -> System.out.println(response.getCreatedAt()));

        return sortedResponses;
    }

    @Override
    public PreappointmentResponse getResponseById(Long id) {
        PreappointmentResponse response = preappointmentResponseRepository.getResponseById(id);
        if (response != null) {
            List<CellulitisIncident> incidents = cellulitisIncidentRepository.findByResponseId(id);
            response.setEpisodes(incidents);
        }
        return response;
    }

    @Override
    public List<CellulitisIncident> getCellulitisIncidentsByResponseId(Long responseId) {
        return cellulitisIncidentRepository.findByResponseId(responseId);
    }

}
