package nhs.uhdb.NHS_project.questionnaire.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class CellulitisIncidentRepositoryImpl implements CellulitisIncidentRepository {

    private JdbcTemplate jdbc;
    private RowMapper<CellulitisIncident> cellulitisIncidentRowMapper;

    //Using logger for debugging
    private static final Logger LOGGER = Logger.getLogger(CellulitisIncidentRepositoryImpl.class.getName());


    public CellulitisIncidentRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setResponsesRowMapper();
    }

    //Mapping fields from resultSet to CellulitisIncident object
    private void setResponsesRowMapper() {
        this.cellulitisIncidentRowMapper = (resultSet, i) -> {
            CellulitisIncident cellulitisIncident = new CellulitisIncident();
            cellulitisIncident.setDateOfCellulitis(resultSet.getString("date_of_cellulitis"));
            cellulitisIncident.setAreaAffected(resultSet.getString("area_affected"));
            cellulitisIncident.setRedness(resultSet.getString("redness"));
            cellulitisIncident.setPainDiscomfort(resultSet.getString("pain_discomfort"));
            cellulitisIncident.setWarmTouch(resultSet.getString("warm_touch"));
            cellulitisIncident.setSwellingWorsen(resultSet.getString("swelling_worsen"));
            cellulitisIncident.setBlisters(resultSet.getString("blisters"));
            cellulitisIncident.setRaisedTemperature(resultSet.getString("raised_temperature"));
            cellulitisIncident.setFluSymptoms(resultSet.getString("flu_symptoms"));
            cellulitisIncident.setAdviceVisit(resultSet.getString("advice_visit"));
            cellulitisIncident.setOralAntibiotics(resultSet.getString("oral_antibiotics"));
            cellulitisIncident.setCourseDuration(resultSet.getString("course_duration"));
            cellulitisIncident.setIvAntibiotics(resultSet.getString("iv_antibiotics"));
            cellulitisIncident.setHospitalAdmission(resultSet.getString("hospital_admission"));
            cellulitisIncident.setLymphoedemaClinicContact(resultSet.getString("lymphoedema_clinic_contact"));
            cellulitisIncident.setComments(resultSet.getString("comments"));
            return cellulitisIncident;
        };
    }

    @Override
    public List<CellulitisIncident> getAllCellulitisIncident() {
        String sql = "SELECT * FROM cellulitis_incident_responses";
        return jdbc.query(sql, cellulitisIncidentRowMapper);
    }

    @Override
    public Long saveIncident(CellulitisIncident incident, Long responseId) {
        String sql = "INSERT INTO cellulitis_incident_responses (date_of_cellulitis, area_affected, redness, pain_discomfort, warm_touch, swelling_worsen, blisters, raised_temperature, flu_symptoms, advice_visit, oral_antibiotics, course_duration, iv_antibiotics, hospital_admission, lymphoedema_clinic_contact, comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
//            LOGGER.info("Attempting to save CellulitisIncident: " + incident);
            jdbc.update(sql, incident.getDateOfCellulitis(), incident.getAreaAffected(), incident.getRedness(), incident.getPainDiscomfort(), incident.getWarmTouch(), incident.getSwellingWorsen(), incident.getBlisters(), incident.getRaisedTemperature(), incident.getFluSymptoms(), incident.getAdviceVisit(), incident.getOralAntibiotics(), incident.getCourseDuration(), incident.getIvAntibiotics(), incident.getHospitalAdmission(), incident.getLymphoedemaClinicContact(), incident.getComments());

            Long incidentId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
//            LOGGER.info("Saved CellulitisIncident with ID: " + incidentId);

            String linkSql = "INSERT INTO preappointment_cellulitis_incident_responses (preappointment_questionnaire_response_id, cellulitis_incident_response_id) VALUES (?, ?)";
            jdbc.update(linkSql, responseId, incidentId);

//            LOGGER.info("Linked CellulitisIncident with ID: " + incidentId + " to PreappointmentResponse with ID: " + responseId);

            return incidentId;

        } catch (EmptyResultDataAccessException e) {
//            LOGGER.severe("EmptyResultDataAccessException: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
//            LOGGER.severe("Exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
