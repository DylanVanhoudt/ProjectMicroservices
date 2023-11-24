package fact.it.appointmentservice;

import fact.it.appointmentservice.dto.AppointmentRequest;
import fact.it.appointmentservice.dto.AppointmentResponse;
import fact.it.appointmentservice.model.Appointment;
import fact.it.appointmentservice.repository.AppointmentRepository;
import fact.it.appointmentservice.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceUnitTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    public void testCreateAppointment() {
        // Arrange
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setPatientNumber("P001");
        appointmentRequest.setAppointmentDateTime(LocalDateTime.of(2023, 10, 15, 9, 0));
        appointmentRequest.setDoctor("Dr. Brown");
        appointmentRequest.setLocation("Main Clinic");

        // Act
        appointmentService.createAppointment(appointmentRequest);

        // Assert
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    public void testGetAllAppointments() {
        // Arrange
        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentNumber("A001");
        appointment1.setPatientNumber("P001");
        appointment1.setAppointmentDateTime(LocalDateTime.of(2023, 10, 15, 9, 0));
        appointment1.setDoctor("Dr. Brown");
        appointment1.setLocation("Main Clinic");

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentNumber("A002");
        appointment2.setPatientNumber("P002");
        appointment2.setAppointmentDateTime(LocalDateTime.of(2023, 11, 20, 14, 30));
        appointment2.setDoctor("Dr. Smith");
        appointment2.setLocation("Elm Clinic");

        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appointment1, appointment2));

        // Act
        List<AppointmentResponse> appointments = appointmentService.getAllAppointments();

        // Assert
        assertEquals(2, appointments.size());

        AppointmentResponse appointmentResponse1 = appointments.get(0);
        assertEquals("A001", appointmentResponse1.getAppointmentNumber());
        assertEquals("P001", appointmentResponse1.getPatientNumber());
        assertEquals(LocalDateTime.of(2023, 10, 15, 9, 0), appointmentResponse1.getAppointmentDateTime());
        assertEquals("Dr. Brown", appointmentResponse1.getDoctor());
        assertEquals("Main Clinic", appointmentResponse1.getLocation());

        AppointmentResponse appointmentResponse2 = appointments.get(1);
        assertEquals("A002", appointmentResponse2.getAppointmentNumber());
        assertEquals("P002", appointmentResponse2.getPatientNumber());
        assertEquals(LocalDateTime.of(2023, 11, 20, 14, 30), appointmentResponse2.getAppointmentDateTime());
        assertEquals("Dr. Smith", appointmentResponse2.getDoctor());
        assertEquals("Elm Clinic", appointmentResponse2.getLocation());

        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllAppointmentsByPatientNumber() {
        // Arrange
        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentNumber("A001");
        appointment1.setPatientNumber("P001");
        appointment1.setAppointmentDateTime(LocalDateTime.of(2023, 10, 15, 9, 0));
        appointment1.setDoctor("Dr. Brown");
        appointment1.setLocation("Main Clinic");

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentNumber("A002");
        appointment2.setPatientNumber("P001");
        appointment2.setAppointmentDateTime(LocalDateTime.of(2023, 11, 20, 10, 30));
        appointment2.setDoctor("Dr. Smith");
        appointment2.setLocation("Elm Clinic");

        when(appointmentRepository.findByPatientNumber("P001")).thenReturn(Arrays.asList(appointment1, appointment2));

        // Act
        List<AppointmentResponse> appointments = appointmentService.getAllAppointmentsByPatientNumber("P001");

        // Assert
        assertEquals(2, appointments.size());

        assertEquals("A001", appointments.get(0).getAppointmentNumber());
        assertEquals("P001", appointments.get(0).getPatientNumber());
        assertEquals(LocalDateTime.of(2023, 10, 15, 9, 0), appointments.get(0).getAppointmentDateTime());
        assertEquals("Dr. Brown", appointments.get(0).getDoctor());
        assertEquals("Main Clinic", appointments.get(0).getLocation());

        assertEquals("A002", appointments.get(1).getAppointmentNumber());
        assertEquals("P001", appointments.get(1).getPatientNumber());
        assertEquals(LocalDateTime.of(2023, 11, 20, 10, 30), appointments.get(1).getAppointmentDateTime());
        assertEquals("Dr. Smith", appointments.get(1).getDoctor());
        assertEquals("Elm Clinic", appointments.get(1).getLocation());

        verify(appointmentRepository, times(1)).findByPatientNumber("P001");
    }
}
