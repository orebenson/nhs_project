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
public class QolResponseArmRepositoryImpl implements QolResponseArmRepository {

    private JdbcTemplate jdbc;
    private RowMapper<QolResponseArm> qolResponseArmRowMapper;

    public QolResponseArmRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setResponsesRowMapper();
    }

    private void setResponsesRowMapper() {
        this.qolResponseArmRowMapper = (resultSet, i) -> {
            QolResponseArm qolResponseArm = new QolResponseArm();
            qolResponseArm.setId(resultSet.getLong("qol_questionnaire_responses_arm_id"));
            qolResponseArm.setUser_id(resultSet.getLong("user_id"));
            qolResponseArm.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
            qolResponseArm.setOccupation(resultSet.getInt("occupation"));
            qolResponseArm.setHousework(resultSet.getInt("housework"));
            qolResponseArm.setCombing_hair(resultSet.getInt("combing_hair"));
            qolResponseArm.setDressing(resultSet.getInt("dressing"));
            qolResponseArm.setWriting(resultSet.getInt("writing"));
            qolResponseArm.setEating(resultSet.getInt("eating"));
            qolResponseArm.setWashing(resultSet.getInt("washing"));
            qolResponseArm.setCleaning_teeth(resultSet.getInt("cleaning_teeth"));
            qolResponseArm.setLeisure(resultSet.getInt("leisure"));
            qolResponseArm.setLeisure_examples(resultSet.getString("leisure_examples"));
            qolResponseArm.setDepend_on_people(resultSet.getInt("depend_on_people"));
            qolResponseArm.setAppearance(resultSet.getInt("appearance"));
            qolResponseArm.setDifficulty_finding_fitting_clothes(resultSet.getInt("difficulty_finding_fitting_clothes"));
            qolResponseArm.setDifficulty_finding_liked_clothes(resultSet.getInt("difficulty_finding_liked_clothes"));
            qolResponseArm.setFeelings(resultSet.getInt("feelings"));
            qolResponseArm.setRelationships(resultSet.getInt("relationships"));
            qolResponseArm.setPain(resultSet.getInt("pain"));
            qolResponseArm.setNumbness(resultSet.getInt("numbness"));
            qolResponseArm.setPins_and_needles(resultSet.getInt("pins_and_needles"));
            qolResponseArm.setWeak(resultSet.getInt("weak"));
            qolResponseArm.setHeavy(resultSet.getInt("heavy"));
            qolResponseArm.setTired(resultSet.getInt("tired"));
            qolResponseArm.setSleeping(resultSet.getInt("sleeping"));
            qolResponseArm.setConcentrating(resultSet.getInt("concentrating"));
            qolResponseArm.setTense(resultSet.getInt("tense"));
            qolResponseArm.setWorried(resultSet.getInt("worried"));
            qolResponseArm.setIrritable(resultSet.getInt("irritable"));
            qolResponseArm.setDepressed(resultSet.getInt("depressed"));
            qolResponseArm.setOverall(resultSet.getInt("overall"));
            return qolResponseArm;
        };
    }


    @Override
    public QolResponseArm getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        String sql = "SELECT * FROM qol_questionnaire_responses_arm WHERE user_id = ? AND created_at = ?";
        try {
            return jdbc.queryForObject(sql, qolResponseArmRowMapper, user_id, Date.valueOf(date));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Long saveResponse(QolResponseArm qolResponseArm) {
        String createQolResponseArmSql = "INSERT INTO qol_questionnaire_responses_arm "
                + "(user_id, created_at, occupation, housework, combing_hair, dressing, writing, eating, "
                + "washing, cleaning_teeth, leisure, leisure_examples, depend_on_people, appearance, "
                + "difficulty_finding_fitting_clothes, difficulty_finding_liked_clothes, feelings, "
                + "relationships, pain, numbness, pins_and_needles, weak, heavy, tired, sleeping, "
                + "concentrating, tense, worried, irritable, depressed, overall) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "RETURNING qol_questionnaire_responses_arm_id";

        try {
            Long qolResponseArmId = jdbc.queryForObject(createQolResponseArmSql,
                    Long.class,
                    qolResponseArm.getUser_id(),
                    Date.valueOf(qolResponseArm.getCreatedAt()),
                    qolResponseArm.getOccupation(),
                    qolResponseArm.getHousework(),
                    qolResponseArm.getCombing_hair(),
                    qolResponseArm.getDressing(),
                    qolResponseArm.getWriting(),
                    qolResponseArm.getEating(),
                    qolResponseArm.getWashing(),
                    qolResponseArm.getCleaning_teeth(),
                    qolResponseArm.getLeisure(),
                    qolResponseArm.getLeisure_examples(),
                    qolResponseArm.getDepend_on_people(),
                    qolResponseArm.getAppearance(),
                    qolResponseArm.getDifficulty_finding_fitting_clothes(),
                    qolResponseArm.getDifficulty_finding_liked_clothes(),
                    qolResponseArm.getFeelings(),
                    qolResponseArm.getRelationships(),
                    qolResponseArm.getPain(),
                    qolResponseArm.getNumbness(),
                    qolResponseArm.getPins_and_needles(),
                    qolResponseArm.getWeak(),
                    qolResponseArm.getHeavy(),
                    qolResponseArm.getTired(),
                    qolResponseArm.getSleeping(),
                    qolResponseArm.getConcentrating(),
                    qolResponseArm.getTense(),
                    qolResponseArm.getWorried(),
                    qolResponseArm.getIrritable(),
                    qolResponseArm.getDepressed(),
                    qolResponseArm.getOverall()
            );
            return qolResponseArmId;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public List<QolResponseArm> getResponsesByUserId(Long user_id) {
        String sql = "SELECT * FROM qol_questionnaire_responses_arm WHERE user_id = ?";
        try {
            return jdbc.query(sql, qolResponseArmRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public QolResponseArm getResponseById(Long id) {
        String sql = "SELECT * FROM qol_questionnaire_responses_arm WHERE qol_questionnaire_responses_arm_id = ?";
        try {
            return jdbc.queryForObject(sql, qolResponseArmRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
