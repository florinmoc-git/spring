package healthinformationsystem.his.controllers;

import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.services.IPatientService;
import jakarta.validation.Valid;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/get/{id}")
    public Patient getPatient(@PathVariable int id){
        return patientService.getPatientById(id);
    }

    @PostMapping("/admit")
    public Patient admitPatient(@RequestBody @Valid Patient patient) throws MethodArgumentNotValidException {
        if(patient.getId() != null){
            var result = new BeanPropertyBindingResult(patient, "patient");
            var exception = new MethodArgumentNotValidException((MethodParameter) null, result);
            var fieldError = new FieldError("patient", "id", patient.getId(), true, null, null,
                    "You cannot set value for patient id in admit endpoint. Did you mean to update patient?");
            exception.getBindingResult().addError(fieldError);
            throw exception;
        }
        return patientService.admitPatient(patient);
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(){
        return null;
    }

}
