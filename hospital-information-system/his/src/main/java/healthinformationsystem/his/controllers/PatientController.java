package healthinformationsystem.his.controllers;

import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.services.IPatientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable int id){
        return patientService.getPatientById(id);
    }

    @PostMapping("/admit")
    public Patient admit(@RequestBody @Valid Patient patient){
        return patientService.admitPatient(patient);
    }
}
