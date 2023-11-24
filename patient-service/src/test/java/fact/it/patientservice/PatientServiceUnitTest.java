package fact.it.patientservice;

import fact.it.patientservice.dto.PatientRequest;
import fact.it.patientservice.dto.PatientResponse;
import fact.it.patientservice.model.Patient;
import fact.it.patientservice.repository.PatientRepository;
import fact.it.patientservice.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceUnitTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void testCreatePatient() {
        // Arrange
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setPatientNumber("P003");
        patientRequest.setFirstName("Alice");
        patientRequest.setLastName("Johnson");
        patientRequest.setGender("Female");
        patientRequest.setDateOfBirth(LocalDate.of(1995, 3, 20));
        patientRequest.setAddress("789 Oak Street");
        patientRequest.setPhoneNumber("555-789-0123");
        patientRequest.setAllergies(Arrays.asList("Dust", "Peanuts"));
        patientRequest.setMedications(Collections.singletonList("Antihistamine"));

        // Act
        patientService.createPatient(patientRequest);

        // Assert
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void testGetAllPatients() {
        // Arrange
        Patient patient1 = new Patient();
        patient1.setPatientNumber("P001");
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setGender("Male");
        patient1.setDateOfBirth(LocalDate.of(1980, 5, 15));
        patient1.setAddress("123 Main Street");
        patient1.setPhoneNumber("555-123-4567");
        patient1.setAllergies(Arrays.asList("Pollen", "Penicillin"));
        patient1.setMedications(Arrays.asList("Aspirin", "Antibiotics"));

        Patient patient2 = new Patient();
        patient2.setPatientNumber("P002");
        patient2.setFirstName("Jane");
        patient2.setLastName("Smith");
        patient2.setGender("Female");
        patient2.setDateOfBirth(LocalDate.of(1990, 8, 22));
        patient2.setAddress("456 Elm Avenue");
        patient2.setPhoneNumber("555-987-6543");
        patient2.setAllergies(Arrays.asList("Nuts", "Latex"));
        patient2.setMedications(Collections.singletonList("Ibuprofen"));

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        // Act
        List<PatientResponse> patients = patientService.getAllPatients();

        // Assert
        assertEquals(2, patients.size());

        PatientResponse firstPatientResponse = patients.get(0);
        assertEquals("P001", firstPatientResponse.getPatientNumber());
        assertEquals("John", firstPatientResponse.getFirstName());
        assertEquals("Doe", firstPatientResponse.getLastName());
        assertEquals("Male", firstPatientResponse.getGender());
        assertEquals(LocalDate.of(1980, 5, 15), firstPatientResponse.getDateOfBirth());
        assertEquals("123 Main Street", firstPatientResponse.getAddress());
        assertEquals("555-123-4567", firstPatientResponse.getPhoneNumber());
        assertEquals(Arrays.asList("Pollen", "Penicillin"), firstPatientResponse.getAllergies());
        assertEquals(Arrays.asList("Aspirin", "Antibiotics"), firstPatientResponse.getMedications());

        PatientResponse secondPatientResponse = patients.get(1);
        assertEquals("P002", secondPatientResponse.getPatientNumber());
        assertEquals("Jane", secondPatientResponse.getFirstName());
        assertEquals("Smith", secondPatientResponse.getLastName());
        assertEquals("Female", secondPatientResponse.getGender());
        assertEquals(LocalDate.of(1990, 8, 22), secondPatientResponse.getDateOfBirth());
        assertEquals("456 Elm Avenue", secondPatientResponse.getAddress());
        assertEquals("555-987-6543", secondPatientResponse.getPhoneNumber());
        assertEquals(Arrays.asList("Nuts", "Latex"), secondPatientResponse.getAllergies());
        assertEquals(Collections.singletonList("Ibuprofen"), secondPatientResponse.getMedications());

        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testGetPatientByPatientNumber() {
        // Arrange
        Patient patient = new Patient();
        patient.setPatientNumber("P001");
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setGender("Male");
        patient.setDateOfBirth(LocalDate.of(1980, 5, 15));
        patient.setAddress("123 Main Street");
        patient.setPhoneNumber("555-123-4567");
        patient.setAllergies(Arrays.asList("Pollen", "Penicillin"));
        patient.setMedications(Arrays.asList("Aspirin", "Antibiotics"));

        when(patientRepository.findByPatientNumber("P001")).thenReturn(patient);

        // Act
        PatientResponse patientResponse = patientService.getPatientByPatientNumber("P001");

        // Assert
        assertEquals("P001", patientResponse.getPatientNumber());
        assertEquals("John", patientResponse.getFirstName());
        assertEquals("Doe", patientResponse.getLastName());
        assertEquals("Male", patientResponse.getGender());
        assertEquals(LocalDate.of(1980, 5, 15), patientResponse.getDateOfBirth());
        assertEquals("123 Main Street", patientResponse.getAddress());
        assertEquals("555-123-4567", patientResponse.getPhoneNumber());
        assertEquals(Arrays.asList("Pollen", "Penicillin"), patientResponse.getAllergies());
        assertEquals(Arrays.asList("Aspirin", "Antibiotics"), patientResponse.getMedications());

        verify(patientRepository, times(1)).findByPatientNumber("P001");
    }

    @Test
    public void testUpdatePatient() {
        // Arrange
        Patient existingPatient = new Patient();
        existingPatient.setPatientNumber("P001");
        existingPatient.setFirstName("John");
        existingPatient.setLastName("Doe");
        existingPatient.setGender("Male");
        existingPatient.setDateOfBirth(LocalDate.of(1980, 5, 15));
        existingPatient.setAddress("123 Main Street");
        existingPatient.setPhoneNumber("555-123-4567");
        existingPatient.setAllergies(Arrays.asList("Pollen", "Penicillin"));
        existingPatient.setMedications(Arrays.asList("Aspirin", "Antibiotics"));

        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setPatientNumber("P001");
        patientRequest.setFirstName("UpdatedName");
        patientRequest.setLastName("UpdatedLastname");
        patientRequest.setGender("Female");
        patientRequest.setDateOfBirth(LocalDate.of(1990, 8, 22));
        patientRequest.setAddress("456 Elm Avenue");
        patientRequest.setPhoneNumber("555-987-6543");
        patientRequest.setAllergies(Collections.singletonList("Nuts"));
        patientRequest.setMedications(Collections.singletonList("Ibuprofen"));

        when(patientRepository.findByPatientNumber("P001")).thenReturn(existingPatient);

        // Act
        patientService.updatePatient("P001", patientRequest);

        // Assert
        verify(patientRepository, times(1)).save(existingPatient);
        assertEquals("UpdatedName", existingPatient.getFirstName());
        assertEquals("UpdatedLastname", existingPatient.getLastName());
        assertEquals("Female", existingPatient.getGender());
        assertEquals(LocalDate.of(1990, 8, 22), existingPatient.getDateOfBirth());
        assertEquals("456 Elm Avenue", existingPatient.getAddress());
        assertEquals("555-987-6543", existingPatient.getPhoneNumber());
        assertEquals(Collections.singletonList("Nuts"), existingPatient.getAllergies());
        assertEquals(Collections.singletonList("Ibuprofen"), existingPatient.getMedications());
    }

    @Test
    public void testUpdatePatient_NotFound() {
        // Arrange
        when(patientRepository.findByPatientNumber("P001")).thenReturn(null);

        // Act & Assert
        assertThrows(Exception.class, () -> patientService.updatePatient("P001", new PatientRequest()));
    }

    @Test
    public void testDeletePatient() {
        // Arrange
        doNothing().when(patientRepository).deleteByPatientNumber("P001");

        // Act
        patientService.deletePatient("P001");

        // Assert
        verify(patientRepository, times(1)).deleteByPatientNumber("P001");
    }
}
