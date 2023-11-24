package fact.it.patientservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(value = "patient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Patient {
    private String id;
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
