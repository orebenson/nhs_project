package nhs.uhdb.NHS_project.diary.controllers;

import nhs.uhdb.NHS_project.accounts.service.UserService;
import nhs.uhdb.NHS_project.diary.model.Appointment;
import nhs.uhdb.NHS_project.diary.services.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AppointmentController {

    private UserService userService;
    private AppointmentService appointmentService;

    public AppointmentController(UserService userService, AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping("/diary/appointment/entry")
    public ModelAndView getAppointmentEntryPage() {
        ModelAndView mav = new ModelAndView("diary/appointmentEntry");
        Appointment newAppointment = new Appointment();
        mav.addObject("newAppointment", newAppointment);
        return mav;
    }

    @GetMapping("/diary/appointment/{id}")
    public ModelAndView getAppointmentHistory(Principal principal, @PathVariable Long id) {
        Long user_id = userService.getUserIdByEmail(principal.getName());
        Appointment appointment = appointmentService.getAppointmentById(id);
        ModelAndView mav = new ModelAndView("diary/appointmentHistory");
        mav.addObject("appointment", appointment);
        return mav;
    }

    @PostMapping("/diary/appointment/entry")
    public ModelAndView postAppointmentEntryPage(Principal principal, @ModelAttribute("newAppointment") Appointment newAppointment, @RequestParam("newDate") String newDate) {
        Long user_id = userService.getUserIdByEmail(principal.getName());

        LocalDate formattedDate = LocalDate.parse(newDate);
        newAppointment.setDate(formattedDate);
        Long result = appointmentService.createAppointmentForUserId(newAppointment, user_id);
        if (result == null) return new ModelAndView("redirect:/diary/appointment/entry/error");
        return new ModelAndView("redirect:/diary/appointment/entry/success");
    }

    @GetMapping("/diary/appointment/entry/success")
    public ModelAndView getAppointmentEntrySuccess() {
        return new ModelAndView("diary/appointmentEntrySuccess");
    }

    @GetMapping("/diary/appointment/entry/error")
    public ModelAndView getAppointmentEntryError() {
        return new ModelAndView("diary/appointmentEntryError");
    }
}
