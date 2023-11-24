package fact.it.patientservice.controller;

import fact.it.patientservice.dto.PatientRequest;
import fact.it.patientservice.dto.PatientResponse;
import fact.it.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPatient
            (@RequestBody PatientRequest patientRequest) {
        patientService.createPatient(patientRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientResponse> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientNumber}")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse getPatientByPatientNumber(@PathVariable("patientNumber") String patientNumber) {
        return patientService.getPatientByPatientNumber(patientNumber);
    }

    @PutMapping("/{patientNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePatient(@PathVariable("patientNumber") String patientNumber, @RequestBody PatientRequest patientRequest) {
        patientService.updatePatient(patientNumber, patientRequest);
    }

    @DeleteMapping("/{patientNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("patientNumber") String patientNumber) {
        patientService.deletePatient(patientNumber);
    }
}
