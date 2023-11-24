package fact.it.appointmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequest {
    private String patientNumber;           // Number of the patient for whom the appointment is scheduled
    private LocalDateTime appointmentDateTime; // Date and time of the appointment
    private String doctor;            // Name of the doctor or healthcare provider
    private String location;          // Location or clinic where the appointment takes place
}
