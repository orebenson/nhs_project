package nhs.uhdb.NHS_project.admin.model;

import nhs.uhdb.NHS_project.diary.model.MeasurementRepository;
import nhs.uhdb.NHS_project.diary.model.MeasurementType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LymphoedemaTypeRepositoryImpl implements LymphoedemaTypeRepository {

    private JdbcTemplate jdbc;
    private RowMapper<LymphoedemaType> lymphoedemaTypeRowMapper;

    private MeasurementRepository measurementRepository;

    public LymphoedemaTypeRepositoryImpl(JdbcTemplate aJdbc, MeasurementRepository measurementRepository) {
        this.jdbc = aJdbc;
        this.measurementRepository = measurementRepository;
        setLymphoedemaTypeRowMapper();
    }

    private void setLymphoedemaTypeRowMapper() {
        this.lymphoedemaTypeRowMapper = (resultSet, i) -> {
            LymphoedemaType lymphoedemaType = new LymphoedemaType();
            lymphoedemaType.setId(resultSet.getLong("lymphoedema_type_id"));
            lymphoedemaType.setName(resultSet.getString("name"));
            lymphoedemaType.setDescription(resultSet.getString("description"));
            lymphoedemaType.setMeasurements(measurementRepository.getMeasurementTypesByLymphoedemaTypeId(resultSet.getLong("lymphoedema_type_id")));
            return lymphoedemaType;
        };
    }

    @Override
    public Long createLymphoedemaType(LymphoedemaType lymphoedema_type) {
        return (lymphoedema_type.getId() != null) ? updateLymphoedemaType(lymphoedema_type) : insertLymphoedemaType(lymphoedema_type);
    }
    private Long insertLymphoedemaType(LymphoedemaType lymphoedema_type) {

        String insertSql = "INSERT INTO lymphoedema_types (name, description) VALUES (?, ?) RETURNING lymphoedema_type_id";
        try {
            Long lymphoedema_type_id = jdbc.queryForObject(insertSql, Long.class, lymphoedema_type.getName(), lymphoedema_type.getDescription());
            lymphoedema_type.setId(lymphoedema_type_id);
            createMeasurementsForLymphoedemaType(lymphoedema_type);
            return lymphoedema_type_id;
        } catch (Error e) {
            return null;
        }
    }
    private void createMeasurementsForLymphoedemaType(LymphoedemaType lymphoedema_type) {
        for (MeasurementType measurement_type : lymphoedema_type.getMeasurements()) {
            Long measurement_type_id = measurementRepository.createMeasurementType(measurement_type);
            if(measurement_type_id == null) return;
            String sql = "INSERT INTO lymphoedema_type_measurements (lymphoedema_type_id, measurement_type_id) VALUES (?, ?)";
            jdbc.update(sql, lymphoedema_type.getId(), measurement_type_id);
        }
    }
    private void updateMeasurementsForLymphoedemaType(LymphoedemaType lymphoedemaType) {
        String deleteSql = "DELETE FROM lymphoedema_type_measurements WHERE lymphoedema_type_id = ?";
        jdbc.update(deleteSql, lymphoedemaType.getId());
        createMeasurementsForLymphoedemaType(lymphoedemaType);
    }

    private Long updateLymphoedemaType(LymphoedemaType lymphoedema_type) {
        String updateSql = "UPDATE lymphoedema_types SET name = ?, description = ? WHERE lymphoedema_type_id = ?";
        try {
            jdbc.update(updateSql, lymphoedema_type.getName(), lymphoedema_type.getDescription(), lymphoedema_type.getId());
            updateMeasurementsForLymphoedemaType(lymphoedema_type);
            return lymphoedema_type.getId();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean deleteLymphoedemaTypeById(Long id) {
        String sqlDeleteMeasurementTypes = "DELETE FROM lymphoedema_type_measurements WHERE lymphoedema_type_id = ?";
        int rowsAffected = jdbc.update(sqlDeleteMeasurementTypes, id);
        String sqlDeleteLymphoedemaType = "DELETE FROM lymphoedema_types WHERE lymphoedema_type_id = ?";
        int rowsAffected2 = jdbc.update(sqlDeleteLymphoedemaType, id);
        return rowsAffected == 0 && rowsAffected2 == 0;
    }

    @Override
    public List<LymphoedemaType> getAllLymphoedemaTypes() {
        String sql = "SELECT * FROM lymphoedema_types";
        return jdbc.query(sql, lymphoedemaTypeRowMapper);
    }

    @Override
    public Boolean setUserLymphoedemaType(Long user_id, Long lymphoedema_type_id) {
        if(lymphoedema_type_id == 0) return true;
        try {
            String deleteSql = "DELETE FROM user_lymphoedema_types WHERE user_id = ?";
            jdbc.update(deleteSql, user_id);

            String sql = "INSERT INTO user_lymphoedema_types (user_id, lymphoedema_type_id) VALUES (?, ?)";
            int rowsAffected = jdbc.update(sql, user_id, lymphoedema_type_id);
            return rowsAffected > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public LymphoedemaType getLymphoedemaTypeByUserId(Long user_id) {
        String sql = "SELECT lt.* FROM lymphoedema_types lt " +
                "JOIN user_lymphoedema_types ult ON lt.lymphoedema_type_id = ult.lymphoedema_type_id " +
                "WHERE ult.user_id = ?";
        try {
            return jdbc.queryForObject(sql, lymphoedemaTypeRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public LymphoedemaType getLymphoedemaTypeByUserEmail(String email) {
        String sql = "SELECT lt.* FROM lymphoedema_types lt " +
                "JOIN user_lymphoedema_types ult ON lt.lymphoedema_type_id = ult.lymphoedema_type_id " +
                "JOIN user_table u ON ult.user_id = u.user_id " +
                "WHERE u.email = ?";
        try {
            return jdbc.queryForObject(sql, lymphoedemaTypeRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
