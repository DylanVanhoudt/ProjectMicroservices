package fact.it.appointmentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the appointment
    private String appointmentNumber; // Unique identifier for the appointment
    private String patientNumber;     // Number of the patient for whom the appointment is scheduled
    private LocalDateTime appointmentDateTime; // Date and time of the appointment
    private String doctor;            // Name or ID of the doctor or healthcare provider
    private String location;          // Location or clinic where the appointment takes place
}
