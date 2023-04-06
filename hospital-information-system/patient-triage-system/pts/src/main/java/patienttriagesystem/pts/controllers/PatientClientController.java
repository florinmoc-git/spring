package patienttriagesystem.pts.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patienttriagesystem.pts.entities.Patient;
import patienttriagesystem.pts.services.PatientClientService;

@RestController
@RequestMapping("/pts/patients")
public class PatientClientController {

    private final PatientClientService patientClientService;

    public PatientClientController(PatientClientService patientClientService) {
        this.patientClientService = patientClientService;
    }

    @GetMapping("/get/{id}")
    public Patient getPatient(@PathVariable int id){
        return patientClientService.getPatient(id);
    }
}
