package healthinformationsystem.his.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import healthinformationsystem.his.dtos.PatientToDepartmentAllocationRequest;
import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.rabbitmq.PatientToDepartmentAllocator;
import healthinformationsystem.his.services.IPatientService;
import jakarta.validation.Valid;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;
    private final PatientToDepartmentAllocator patientToDepartmentAllocator;

    public PatientController(IPatientService patientService, PatientToDepartmentAllocator patientToDepartmentAllocator) {
        this.patientService = patientService;
        this.patientToDepartmentAllocator = patientToDepartmentAllocator;
    }

    @GetMapping("/get/{id}")
    public Patient getPatient(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/admit/{department}")
    public Patient admitPatient(@RequestBody @Valid Patient patient, @PathVariable String department) throws MethodArgumentNotValidException {
        // When first created, a patient cannot have an id assigned
        if (patient.getId() != null) {
            var exception = getMethodArgumentNotValidException(patient);
            throw exception;
        }
        var admittedPatient =  patientService.admitPatient(patient);
        sendToAllocator(admittedPatient, department);
        return admittedPatient;
    }

    private void sendToAllocator(Patient patient, String department){
        patientToDepartmentAllocator.sendAllocationMessage(new PatientToDepartmentAllocationRequest(patient, department));
    }

    private MethodArgumentNotValidException getMethodArgumentNotValidException(Patient patient) {
        var result = new BeanPropertyBindingResult(patient, "patient");
        var exception = new MethodArgumentNotValidException((MethodParameter) null, result);
        var fieldError = new FieldError("patient", "id", patient.getId(), true, null, null,
                "You cannot set value for patient id in admit endpoint. Did you mean to update patient?");
        exception.getBindingResult().addError(fieldError);
        return exception;
    }

    @PutMapping("/update-put")
    public Patient updatePatientPut(@RequestBody @Valid Patient patient) {
        return patientService.updatePatient(patient);
    }

    @PatchMapping("/update-reflection/{id}")
    public Patient updatePatientPatch(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return patientService.updatePatientPatch(id, fields);
    }

    // This looks like an interesting and promising approach. My concern is that the maven
    // repo has not been updated since May 2020.
    // Also, updating an object in an array relies on the position of said object in array. But how
    // confident can we be that the order of the objects in the array will always be the same?
    @PatchMapping(path = "/update-json-patch/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody JsonPatch patch) {
        try {
            Patient Patient = patientService.getPatientById(id);
            Patient PatientPatched = applyPatchToPatient(patch, Patient);
            patientService.updatePatient(PatientPatched);
            return ResponseEntity.ok(PatientPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private Patient applyPatchToPatient(
            JsonPatch patch, Patient targetPatient) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPatient, JsonNode.class));
        return objectMapper.treeToValue(patched, Patient.class);
    }

}
