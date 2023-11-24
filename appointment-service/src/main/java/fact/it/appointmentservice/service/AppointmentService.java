package fact.it.appointmentservice.service;

import fact.it.appointmentservice.dto.AppointmentRequest;
import fact.it.appointmentservice.dto.AppointmentResponse;
import fact.it.appointmentservice.model.Appointment;
import fact.it.appointmentservice.repository.AppointmentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @PostConstruct
    public void loadData() {
        if (appointmentRepository.count() <= 0) {
            // Create appointment data
            Appointment appointment1 = new Appointment();
            appointment1.setAppointmentNumber("A001");
            appointment1.setPatientNumber("P001");
            appointment1.setAppointmentDateTime(LocalDateTime.of(2023, 4, 15, 10, 0));
            appointment1.setDoctor("Dr. Smith");
            appointment1.setLocation("Main Clinic");

            Appointment appointment2 = new Appointment();
            appointment2.setAppointmentNumber("A002");
            appointment2.setPatientNumber("P002");
            appointment2.setAppointmentDateTime(LocalDateTime.of(2023, 7, 22, 14, 30));
            appointment2.setDoctor("Dr. Johnson");
            appointment2.setLocation("Elm Clinic");

            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2);
        }
    }

    public Appointment createAppointment(AppointmentRequest appointmentRequest) {
        // Create a new Appointment entity from the request data
        Appointment newAppointment = new Appointment();
        newAppointment.setAppointmentNumber(UUID.randomUUID().toString());
        newAppointment.setPatientNumber(appointmentRequest.getPatientNumber());
        newAppointment.setAppointmentDateTime(appointmentRequest.getAppointmentDateTime());
        newAppointment.setDoctor(appointmentRequest.getDoctor());
        newAppointment.setLocation(appointmentRequest.getLocation());

        // Save the new appointment to the repository
        return appointmentRepository.save(newAppointment);
    }

    public List<AppointmentResponse> getAllAppointments() {
        // Retrieve all appointments from the repository
        List<Appointment> appointments = appointmentRepository.findAll();

        // Convert appointment entities to response objects
        return appointments.stream()
                .map(this::mapToAppointmentResponse)
                .toList();
    }

    public List<AppointmentResponse> getAllAppointmentsByPatientNumber(String patientNumber) {
        // Retrieve all appointments for a specific patient number from the repository
        List<Appointment> appointments = appointmentRepository.findByPatientNumber(patientNumber);

        // Convert appointment entities to response objects
        return appointments.stream()
                .map(this::mapToAppointmentResponse)
                .toList();
    }

    private AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        // Convert an Appointment entity to an AppointmentResponse object
        return AppointmentResponse.builder()
                .appointmentNumber(appointment.getAppointmentNumber())
                .patientNumber(appointment.getPatientNumber())
                .appointmentDateTime(appointment.getAppointmentDateTime())
                .doctor(appointment.getDoctor())
                .location(appointment.getLocation())
                .build();
    }
}
