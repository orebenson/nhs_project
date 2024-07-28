package nhs.uhdb.NHS_project.questionnaire.model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QolResponseRepositoryImpl implements QolResponseRepository{

    private JdbcTemplate jdbc;
    private RowMapper<QolResponse> qolResponseRowMapper;

    public QolResponseRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setResponsesRowMapper();
    }

    //setting row mapper for responses received
    private void setResponsesRowMapper() {
        this.qolResponseRowMapper = (resultSet, i) -> {
            QolResponse qolResponse = new QolResponse();
            qolResponse.setId(resultSet.getLong("qol_questionnaire_response_id"));
            qolResponse.setUser_id(resultSet.getLong("user_id"));
            qolResponse.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
            qolResponse.setWalking(resultSet.getInt("walking"));
            qolResponse.setBending(resultSet.getInt("bending"));
            qolResponse.setStanding(resultSet.getInt("standing"));
            qolResponse.setGettingUp(resultSet.getInt("getting_up"));
            qolResponse.setOccupation(resultSet.getInt("occupation"));
            qolResponse.setHousework(resultSet.getInt("housework"));
            qolResponse.setLeisureActivities(resultSet.getInt("leisure_activities"));
            qolResponse.setLeisureExamples(resultSet.getString("leisure_examples"));
            qolResponse.setDependency(resultSet.getInt("dependency"));
            qolResponse.setAppearance(resultSet.getInt("appearance"));
            qolResponse.setClothesFitDifficulty(resultSet.getInt("clothes_fit_difficulty"));
            qolResponse.setClothesPreferenceDifficulty(resultSet.getInt("clothes_preference_difficulty"));
            qolResponse.setShoesFitDifficulty(resultSet.getInt("shoes_fit_difficulty"));
            qolResponse.setSocksFitDifficulty(resultSet.getInt("socks_fit_difficulty"));
            qolResponse.setSelfPerception(resultSet.getInt("self_perception"));
            qolResponse.setRelationshipImpact(resultSet.getInt("relationship_impact"));
            qolResponse.setPain(resultSet.getInt("pain"));
            qolResponse.setNumbness(resultSet.getInt("numbness"));
            qolResponse.setPinsNeedles(resultSet.getInt("pins_needles"));
            qolResponse.setLegWeakness(resultSet.getInt("leg_weakness"));
            qolResponse.setLegHeaviness(resultSet.getInt("leg_heaviness"));
            qolResponse.setSleepTrouble(resultSet.getInt("sleep_trouble"));
            qolResponse.setDifficultyConcentrating(resultSet.getInt("difficulty_concentrating"));
            qolResponse.setFeelingTense(resultSet.getInt("feeling_tense"));
            qolResponse.setFeelingWorried(resultSet.getInt("feeling_worried"));
            qolResponse.setFeelingIrritable(resultSet.getInt("feeling_irritable"));
            qolResponse.setFeelingDepressed(resultSet.getInt("feeling_depressed"));
            qolResponse.setQualityOfLife(resultSet.getInt("quality_of_life"));

            return qolResponse;
        };
    }

    //function for getting response user by id and date
    @Override
    public QolResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        String sql = "SELECT * FROM qol_questionnaire_responses WHERE user_id = ? AND created_at = ?";
        try {
            return jdbc.queryForObject(sql, qolResponseRowMapper, user_id, Date.valueOf(date));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //function for getting response by user id within a list
    @Override
    public List<QolResponse> getResponsesByUserId(Long user_id) {
        String sql = "SELECT * FROM qol_questionnaire_responses WHERE user_id = ?";
        try {
            return jdbc.query(sql, qolResponseRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


    //function for saving the response into the db
    public Long saveResponse(QolResponse qolResponse) {
        String createQolResponseSql = "INSERT INTO qol_questionnaire_responses "
                + "(user_id, created_at, walking, bending, standing, getting_up, occupation, housework, "
                + "leisure_activities, leisure_examples, dependency, appearance, clothes_fit_difficulty, clothes_preference_difficulty, "
                + "shoes_fit_difficulty, socks_fit_difficulty, self_perception, relationship_impact, pain, "
                + "numbness, pins_needles, leg_weakness, leg_heaviness, sleep_trouble, difficulty_concentrating, "
                + "feeling_tense, feeling_worried, feeling_irritable, feeling_depressed, quality_of_life)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" //30
                + " RETURNING qol_questionnaire_response_id";

        try {
            Long qolResponseId = jdbc.queryForObject(createQolResponseSql,
                    Long.class,
                    qolResponse.getUser_id(),
                    Date.valueOf(qolResponse.getCreatedAt()),
                    qolResponse.getWalking(),
                    qolResponse.getBending(),
                    qolResponse.getStanding(),
                    qolResponse.getGettingUp(),
                    qolResponse.getOccupation(),
                    qolResponse.getHousework(),
                    qolResponse.getLeisureActivities(),
                    qolResponse.getLeisureExamples(),
                    qolResponse.getDependency(),
                    qolResponse.getAppearance(),
                    qolResponse.getClothesFitDifficulty(),
                    qolResponse.getClothesPreferenceDifficulty(),
                    qolResponse.getShoesFitDifficulty(),
                    qolResponse.getSocksFitDifficulty(),
                    qolResponse.getSelfPerception(),
                    qolResponse.getRelationshipImpact(),
                    qolResponse.getPain(),
                    qolResponse.getNumbness(),
                    qolResponse.getPinsNeedles(),
                    qolResponse.getLegWeakness(),
                    qolResponse.getLegHeaviness(),
                    qolResponse.getSleepTrouble(),
                    qolResponse.getDifficultyConcentrating(),
                    qolResponse.getFeelingTense(),
                    qolResponse.getFeelingWorried(),
                    qolResponse.getFeelingIrritable(),
                    qolResponse.getFeelingDepressed(),
                    qolResponse.getQualityOfLife()
            );
            return qolResponseId;


        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
