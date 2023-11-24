package fact.it.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private String patientNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;
    private List<String> allergies;
    private List<String> medications;
}
