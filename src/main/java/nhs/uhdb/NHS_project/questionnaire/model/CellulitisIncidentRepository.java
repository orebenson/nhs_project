package nhs.uhdb.NHS_project.questionnaire.model;

import java.util.List;

public interface CellulitisIncidentRepository {

    List<CellulitisIncident> getAllCellulitisIncident();
    Long saveIncident(CellulitisIncident incident, Long responseId);
}
