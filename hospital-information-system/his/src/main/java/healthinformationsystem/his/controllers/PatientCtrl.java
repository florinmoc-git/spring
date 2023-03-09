package healthinformationsystem.his.controllers;

import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.services.IPatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientCtrl {

    private final IPatientService patientService;

    public PatientCtrl(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable int id){
        return patientService.getPatientById(id);
    }

    @PostMapping("/admit")
    public Patient admit(@RequestBody Patient patient){
        return patientService.admitPatient(patient);
    }
}
