package fact.it.patientservice.service;

import fact.it.patientservice.dto.PatientRequest;
import fact.it.patientservice.dto.PatientResponse;
import fact.it.patientservice.model.Patient;
import fact.it.patientservice.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @PostConstruct
    public void loadData() {
        if (patientRepository.count() <= 0) {
            Patient patient1 = Patient.builder()
                    .patientNumber("P001")
                    .firstName("John")
                    .lastName("Doe")
                    .gender("Male")
                    .dateOfBirth(LocalDate.of(1980, 5, 15))
                    .address("123 Main Street")
                    .phoneNumber("555-123-4567")
                    .allergies(Arrays.asList("Pollen", "Penicillin"))
                    .medications(Arrays.asList("Aspirin", "Antibiotics"))
                    .build();

            Patient patient2 = Patient.builder()
                    .patientNumber("P002")
                    .firstName("Jane")
                    .lastName("Smith")
                    .gender("Female")
                    .dateOfBirth(LocalDate.of(1990, 8, 22))
                    .address("456 Elm Avenue")
                    .phoneNumber("555-987-6543")
                    .allergies(Arrays.asList("Nuts", "Latex"))
                    .medications(Collections.singletonList("Ibuprofen"))
                    .build();

            patientRepository.save(patient1);
            patientRepository.save(patient2);
        }
    }

    public void createPatient(PatientRequest patientRequest) {
        Patient patient = Patient.builder()
                .patientNumber(patientRequest.getPatientNumber())
                .firstName(patientRequest.getFirstName())
                .lastName(patientRequest.getLastName())
                .gender(patientRequest.getGender())
                .dateOfBirth(patientRequest.getDateOfBirth())
                .address(patientRequest.getAddress())
                .phoneNumber(patientRequest.getPhoneNumber())
                .allergies(patientRequest.getAllergies())
                .medications(patientRequest.getMedications())
                .build();

        patientRepository.save(patient);
    }

    public List<PatientResponse> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(this::mapToPatientResponse).collect(Collectors.toList());
    }

    public PatientResponse getPatientByPatientNumber(String patientNumber) {
        Optional<Patient> patientOptional = Optional.ofNullable(patientRepository.findByPatientNumber(patientNumber));

        if (patientOptional.isPresent()) {
            return mapToPatientResponse(patientOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with number " + patientNumber + " not found.");
        }
    }

    public void updatePatient(String patientNumber, PatientRequest patientRequest) {
        Optional<Patient> patientOptional = Optional.ofNullable(patientRepository.findByPatientNumber(patientNumber));

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();

            patient.setPatientNumber(patientRequest.getPatientNumber());
            patient.setFirstName(patientRequest.getFirstName());
            patient.setLastName(patientRequest.getLastName());
            patient.setGender(patientRequest.getGender());
            patient.setDateOfBirth(patientRequest.getDateOfBirth());
            patient.setAddress(patientRequest.getAddress());
            patient.setPhoneNumber(patientRequest.getPhoneNumber());
            patient.setAllergies(patientRequest.getAllergies());
            patient.setMedications(patientRequest.getMedications());

            patientRepository.save(patient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with number " + patientNumber + " not found.");
        }
    }


    public void deletePatient(String patientNumber) {
        patientRepository.deleteByPatientNumber(patientNumber);
    }

    private PatientResponse mapToPatientResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .patientNumber(patient.getPatientNumber())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .address(patient.getAddress())
                .phoneNumber(patient.getPhoneNumber())
                .allergies(patient.getAllergies())
                .medications(patient.getMedications())
                .build();
    }
}
