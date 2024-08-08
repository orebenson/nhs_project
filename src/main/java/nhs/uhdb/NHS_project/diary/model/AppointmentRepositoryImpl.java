package nhs.uhdb.NHS_project.diary.model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private JdbcTemplate jdbc;
    private RowMapper<Appointment> appointmentRowMapper;

    public AppointmentRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setAppointmentRowMapper();
    }

    private void setAppointmentRowMapper() {
        this.appointmentRowMapper = (resultSet, i) -> {
            Appointment appointment = new Appointment();
            appointment.setId(resultSet.getLong("appointment_id"));
            appointment.setType(resultSet.getString("type"));
            appointment.setDescription(resultSet.getString("description"));
            appointment.setDate(resultSet.getDate("date").toLocalDate());
            return appointment;
        };
    }

    @Override
    public Long createAppointmentForUserId(Appointment appointment, Long user_id) {
        String sql = "INSERT INTO user_appointments (user_id, date, type, description) VALUES (?, ?, ?, ?) RETURNING appointment_id";
        try {
            return jdbc.queryForObject(sql, Long.class, user_id, Date.valueOf(appointment.getDate()), appointment.getType(), appointment.getDescription());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean deleteAppointmentById(Long appointment_id) {
        String sql = "DELETE FROM user_appointments WHERE appointment_id = ?";
        try {
            jdbc.update(sql, appointment_id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
    }

    @Override
    public Appointment getAppointmentById(Long appointment_id) {
        String sql = "SELECT * FROM user_appointments WHERE appointment_id = ?";
        try {
            return jdbc.queryForObject(sql, appointmentRowMapper, appointment_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Appointment> getAppointmentsByUserIdAndDate(Long user_id, LocalDate date) {
        String sql = "SELECT * FROM user_appointments WHERE user_id = ? AND date = ?";
        try {
            return jdbc.query(sql, appointmentRowMapper, user_id, Date.valueOf(date));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(Long user_id) {
        String sql = "SELECT * FROM user_appointments WHERE user_id = ?";
        try {
            return jdbc.query(sql, appointmentRowMapper, user_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}
