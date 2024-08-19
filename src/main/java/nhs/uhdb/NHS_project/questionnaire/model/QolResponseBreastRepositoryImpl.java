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
public class QolResponseBreastRepositoryImpl implements QolResponseBreastRepository {

    private JdbcTemplate jdbc;
    private RowMapper<QolResponseBreast> qolResponseBreastRowMapper;

    public QolResponseBreastRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setResponsesRowMapper();
    }

    private void setResponsesRowMapper() {
        this.qolResponseBreastRowMapper = (resultSet, i) -> {
            QolResponseBreast qolResponseBreast = new QolResponseBreast();
            qolResponseBreast.setId(resultSet.getLong("qol_questionnaire_responses_breast_id"));
            qolResponseBreast.setUser_id(resultSet.getLong("user_id"));
            qolResponseBreast.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
            qolResponseBreast.setOccupation(resultSet.getInt("occupation"));
            qolResponseBreast.setHousework(resultSet.getInt("housework"));
            qolResponseBreast.setDressing(resultSet.getInt("dressing"));
            qolResponseBreast.setWashing(resultSet.getInt("washing"));
            qolResponseBreast.setLeisure(resultSet.getInt("leisure"));
            qolResponseBreast.setLeisure_examples(resultSet.getString("leisure_examples"));
            qolResponseBreast.setDepend_on_people(resultSet.getInt("depend_on_people"));
            qolResponseBreast.setAppearance(resultSet.getInt("appearance"));
            qolResponseBreast.setFinding_clothes_to_fit(resultSet.getInt("finding_clothes_to_fit"));
            qolResponseBreast.setFinding_clothes_to_wear(resultSet.getInt("finding_clothes_to_wear"));
            qolResponseBreast.setFeelings(resultSet.getInt("feelings"));
            qolResponseBreast.setRelationships(resultSet.getInt("relationships"));
            qolResponseBreast.setPain(resultSet.getInt("pain"));
            qolResponseBreast.setNumbness(resultSet.getInt("numbness"));
            qolResponseBreast.setPins_and_needles(resultSet.getInt("pins_and_needles"));
            qolResponseBreast.setTightness(resultSet.getInt("tightness"));
            qolResponseBreast.setHeavy(resultSet.getInt("heavy"));
            qolResponseBreast.setSleeping(resultSet.getInt("sleeping"));
            qolResponseBreast.setConcentrating(resultSet.getInt("concentrating"));
            qolResponseBreast.setWorried(resultSet.getInt("worried"));
            qolResponseBreast.setIrritable(resultSet.getInt("irritable"));
            qolResponseBreast.setDepressed(resultSet.getInt("depressed"));
            qolResponseBreast.setOverall(resultSet.getInt("overall"));
            return qolResponseBreast;
        };
    }

    @Override
    public QolResponseBreast getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        String sql = "SELECT * FROM qol_questionnaire_responses_breast WHERE user_id = ? AND created_at = ?";
        try {
            return jdbc.queryForObject(sql, qolResponseBreastRowMapper, user_id, Date.valueOf(date));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Long saveResponse(QolResponseBreast qolResponseBreast) {
        String createQolResponseBreastSql = "INSERT INTO qol_questionnaire_responses_breast "
                + "(user_id, created_at, occupation, housework, dressing, washing, leisure, leisure_examples, "
                + "depend_on_people, appearance, finding_clothes_to_fit, finding_clothes_to_wear, feelings, "
                + "relationships, pain, numbness, pins_and_needles, tightness, heavy, sleeping, concentrating, "
                + "worried, irritable, depressed, overall) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "RETURNING qol_questionnaire_responses_breast_id";

        try {
            return jdbc.queryForObject(createQolResponseBreastSql,
                    Long.class,
                    qolResponseBreast.getUser_id(),
                    Date.valueOf(qolResponseBreast.getCreatedAt()),
                    qolResponseBreast.getOccupation(),
                    qolResponseBreast.getHousework(),
                    qolResponseBreast.getDressing(),
                    qolResponseBreast.getWashing(),
                    qolResponseBreast.getLeisure(),
                    qolResponseBreast.getLeisure_examples(),
                    qolResponseBreast.getDepend_on_people(),
                    qolResponseBreast.getAppearance(),
                    qolResponseBreast.getFinding_clothes_to_fit(),
                    qolResponseBreast.getFinding_clothes_to_wear(),
                    qolResponseBreast.getFeelings(),
                    qolResponseBreast.getRelationships(),
                    qolResponseBreast.getPain(),
                    qolResponseBreast.getNumbness(),
                    qolResponseBreast.getPins_and_needles(),
                    qolResponseBreast.getTightness(),
                    qolResponseBreast.getHeavy(),
                    qolResponseBreast.getSleeping(),
                    qolResponseBreast.getConcentrating(),
                    qolResponseBreast.getWorried(),
                    qolResponseBreast.getIrritable(),
                    qolResponseBreast.getDepressed(),
                    qolResponseBreast.getOverall()
            );

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<QolResponseBreast> getResponsesByUserId(Long user_id) {
        String sql = "SELECT * FROM qol_questionnaire_responses_breast WHERE user_id = ?";
        try {
            return jdbc.query(sql, qolResponseBreastRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public QolResponseBreast getResponseById(Long id) {
        String sql = "SELECT * FROM qol_questionnaire_responses_breast WHERE qol_questionnaire_responses_breast_id = ?";
        try {
            return jdbc.queryForObject(sql, qolResponseBreastRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
