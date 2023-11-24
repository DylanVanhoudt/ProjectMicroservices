package fact.it.patientservice.repository;

import fact.it.patientservice.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
    Patient findByPatientNumber(String patientNumber);

    void deleteByPatientNumber(String patientNumber);
}
