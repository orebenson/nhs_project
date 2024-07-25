package nhs.uhdb.NHS_project.diary.model;

import nhs.uhdb.NHS_project.admin.model.LymphoedemaType;
import nhs.uhdb.NHS_project.admin.model.LymphoedemaTypeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MeasurementRepositoryImpl implements MeasurementRepository {

    private JdbcTemplate jdbc;
    private RowMapper<Measurement> measurementRowMapper;
    private RowMapper<MeasurementType> measurementTypeRowMapper;


    public MeasurementRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setMeasurementRowMapper();
        setMeasurementTypeRowMapper();
    }

    private void setMeasurementRowMapper() {
        this.measurementRowMapper = (resultSet, i) -> {
            Measurement measurement = new Measurement();
            measurement.setId(resultSet.getLong("measurement_id"));
            measurement.setMeasurementType(getMeasurementTypeByMeasurementId(resultSet.getLong("measurement_id")));
            measurement.setValue(resultSet.getLong("measurement_value"));
            return measurement;
        };
    }

    private void setMeasurementTypeRowMapper() {
        this.measurementTypeRowMapper = (resultSet, i) -> {
            MeasurementType measurementType = new MeasurementType();
            measurementType.setId(resultSet.getLong("measurement_type_id"));
            measurementType.setName(resultSet.getString("name"));
            measurementType.setDescription(resultSet.getString("description"));
            measurementType.setUnit(resultSet.getString("unit"));
            return measurementType;
        };
    }

    private MeasurementType getMeasurementTypeByMeasurementId(long measurementId) {
        String sql = "SELECT mt.* from measurement_types mt JOIN diary_entry_measurements dem ON dem.measurement_type_id = mt.measurement_type_id WHERE dem.measurement_id = ?";
        try {
            return jdbc.queryForObject(sql, measurementTypeRowMapper, measurementId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<MeasurementType> getMeasurementTypesByLymphoedemaTypeId(Long lymphoedema_type_id) {
        String sql = "SELECT mt.* FROM measurement_types mt JOIN lymphoedema_type_measurements ltm ON ltm.measurement_type_id = mt.measurement_type_id WHERE ltm.lymphoedema_type_id = ?";
        return jdbc.query(sql, measurementTypeRowMapper, lymphoedema_type_id);
    }

    @Override
    public Long createMeasurementType(MeasurementType measurementType) {
        return (measurementType.getId() == null) ? insertMeasuremementType(measurementType) : updateMeasurementType(measurementType);
    }

    private Long insertMeasuremementType(MeasurementType measurementType) {
        String sql = "INSERT INTO measurement_types (name, description, unit) VALUES (?, ?, ?) RETURNING measurement_type_id";
        try {
            return jdbc.queryForObject(sql, Long.class, measurementType.getName(), measurementType.getDescription(), measurementType.getUnit());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Long updateMeasurementType(MeasurementType measurementType) {
        String updateSql = "UPDATE measurement_types SET name = ?, description = ?, unit = ? WHERE measurement_type_id = ?";
        try {
            jdbc.update(updateSql, measurementType.getName(), measurementType.getDescription(), measurementType.getUnit(), measurementType.getId());
            return measurementType.getId();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Measurement> getMeasurementsByDiaryEntryId(Long diary_entry_id) {
        String sql = "SELECT * FROM diary_entry_measurements WHERE diary_entry_id = ?";
        try {
            return jdbc.query(sql, measurementRowMapper, diary_entry_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Measurement> getEmptyMeasurementsByUserId(Long user_id) {
        String sql = "SELECT mt.* " +
                "FROM measurement_types mt " +
                "JOIN lymphoedema_type_measurements ltm ON mt.measurement_type_id = ltm.measurement_type_id " +
                "JOIN user_lymphoedema_types ult ON ult.lymphoedema_type_id = ltm.lymphoedema_type_id " +
                "WHERE ult.user_id = ?";
        try {
            List<MeasurementType> measurementTypes = jdbc.query(sql, measurementTypeRowMapper, user_id);
            List<Measurement> userMeasurements = new ArrayList<>();
            for (MeasurementType measurementType : measurementTypes) {
                userMeasurements.add(new Measurement(null, measurementType, 0L));
            }
            return userMeasurements;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Long> submitMeasurementsForDiaryEntry(List<Measurement> measurements, Long diary_entry_id) {
        String deleteSql = "DELETE FROM diary_entry_measurements WHERE diary_entry_id = ?";
        String insertSql = "INSERT INTO diary_entry_measurements (diary_entry_id, measurement_type_id, measurement_value) VALUES (?, ?, ?) returning measurement_id";
        List<Long> measurementIds = new ArrayList<>();
        try {
            jdbc.update(deleteSql, diary_entry_id);
            for (Measurement measurement : measurements) {
                Long measurementId = jdbc.queryForObject(insertSql, Long.class, diary_entry_id, measurement.getMeasurementType().getId(), measurement.getValue());
                measurementIds.add(measurementId);
            }
            return measurementIds;
        } catch (Error e) {
            return new ArrayList<>();
        }
    }


}
