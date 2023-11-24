package fact.it.appointmentservice.repository;

import fact.it.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientNumber(String patientNumber);
}
