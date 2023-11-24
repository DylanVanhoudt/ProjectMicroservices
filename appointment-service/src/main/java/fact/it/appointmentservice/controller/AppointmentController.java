package fact.it.appointmentservice.controller;

import fact.it.appointmentservice.dto.AppointmentRequest;
import fact.it.appointmentservice.dto.AppointmentResponse;
import fact.it.appointmentservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.createAppointment(appointmentRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/patient/{patientNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAllAppointmentsByPatientNumber(@PathVariable String patientNumber) {
        return appointmentService.getAllAppointmentsByPatientNumber(patientNumber);
    }
}
