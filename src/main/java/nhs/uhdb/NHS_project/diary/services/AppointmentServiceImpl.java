package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.Appointment;
import nhs.uhdb.NHS_project.diary.model.AppointmentRepository;
import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
class AppointmentServiceImpl implements AppointmentService {

    AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Long createAppointmentForUserId(Appointment appointment, Long user_id) {
        return appointmentRepository.createAppointmentForUserId(appointment, user_id);
    }

    @Override
    public Boolean deleteAppointmentById(Long appointment_id) {
        return appointmentRepository.deleteAppointmentById(appointment_id);
    }

    @Override
    public Appointment getAppointmentById(Long appointment_id) {
        return appointmentRepository.getAppointmentById(appointment_id);
    }

    @Override
    public List<Appointment> getAppointmentsByUserIdAndDate(Long user_id, LocalDate date) {
        return appointmentRepository.getAppointmentsByUserIdAndDate(user_id, date);
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(Long user_id) {
        return appointmentRepository.getAppointmentsByUserId(user_id);
    }

    @Override
    public List<String> getFormattedAppointmentDatesByUserId(Long user_id) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByUserId(user_id);
        List<String> formattedDates = new ArrayList<>();
        if (appointments != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Appointment appointment : appointments) {
                LocalDate date = appointment.getDate();
                String formattedDate = date.format(formatter);
                formattedDates.add(formattedDate);
            }
        }
        return formattedDates;
    }
}
