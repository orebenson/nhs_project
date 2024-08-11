package nhs.uhdb.NHS_project.diary.model;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository {
    Long createAppointmentForUserId(Appointment appointment, Long user_id);
    Boolean deleteAppointmentById(Long appointment_id);
    Appointment getAppointmentById(Long appointment_id);
    List<Appointment> getAppointmentsByUserIdAndDate(Long user_id, LocalDate date);
    List<Appointment> getAppointmentsByUserId(Long user_id);
}
