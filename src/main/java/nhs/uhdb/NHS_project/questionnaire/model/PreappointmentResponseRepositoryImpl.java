package nhs.uhdb.NHS_project.questionnaire.model;

import nhs.uhdb.NHS_project.accounts.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PreappointmentResponseRepositoryImpl implements PreappointmentResponseRepository{

    private JdbcTemplate jdbc;
    private RowMapper<PreappointmentResponse> preappointmentResponseRowMapper;

    //Constructor to initialise JdbcTemplate and set up RowMapper
    public PreappointmentResponseRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setResponsesRowMapper();
    }

    //Method for mapping fields from resultSet to PreappointmentResponse object
    private void setResponsesRowMapper() {
        this.preappointmentResponseRowMapper = (resultSet, i) -> {
            PreappointmentResponse preappointmentResponse = new PreappointmentResponse();
            preappointmentResponse.setId(resultSet.getLong("preappointment_questionnaire_response_id"));
            preappointmentResponse.setUser_id(resultSet.getLong("user_id"));
            preappointmentResponse.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
            preappointmentResponse.setMedications(resultSet.getString("medications"));
            preappointmentResponse.setChangesToHealth(resultSet.getString("changes_to_health"));
            preappointmentResponse.setSwellingConcerns(resultSet.getString("swelling_concerns"));
            preappointmentResponse.setHosieryConcerns(resultSet.getString("hosiery_concerns"));
            preappointmentResponse.setCellulitisEpisodes(resultSet.getInt("cellulitis_episodes"));
            return preappointmentResponse;
        };
    }

    //Method to retrieve a PreappointmentResponse by user ID and date
    @Override
    public PreappointmentResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        String sql = "SELECT * FROM preappointment_questionnaire_responses WHERE user_id = ? AND createdAt = ?";
        try {
            return jdbc.queryForObject(sql, preappointmentResponseRowMapper, user_id, Date.valueOf(date));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Method to retrieve all PreappointmentResponses by user ID
    @Override
    public List<PreappointmentResponse> getResponsesByUserId(Long user_id) {
        String sql = "SELECT * FROM preappointment_questionnaire_responses WHERE user_id = ?";
        try {
            return jdbc.query(sql, preappointmentResponseRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


    //Method to save a new PreappointmentResponse to the database and return its ID
    public Long saveResponse(PreappointmentResponse preappointmentResponse) {
        String createPreappointmentResponseSql = "INSERT INTO preappointment_questionnaire_responses (user_id, created_at, medications, changes_to_health, swelling_concerns, hosiery_concerns, cellulitis_episodes)"
                + "Values (?, ?, ?, ?, ?, ?, ?)"
                + " RETURNING preappointment_questionnaire_response_id";

        try {
            Long preappointmentResponseId = jdbc.queryForObject(createPreappointmentResponseSql,
                    Long.class,
                    preappointmentResponse.getUser_id(),
                    Date.valueOf(preappointmentResponse.getCreatedAt()),
                    preappointmentResponse.getMedications(),
                    preappointmentResponse.getChangesToHealth(),
                    preappointmentResponse.getSwellingConcerns(),
                    preappointmentResponse.getHosieryConcerns(),
                    preappointmentResponse.getCellulitisEpisodes()
            );
            return preappointmentResponseId;


        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public PreappointmentResponse getResponseById(Long id) {
        String sql = "SELECT * FROM preappointment_questionnaire_responses WHERE preappointment_questionnaire_response_id = ?";
        try {
            return jdbc.queryForObject(sql, preappointmentResponseRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
