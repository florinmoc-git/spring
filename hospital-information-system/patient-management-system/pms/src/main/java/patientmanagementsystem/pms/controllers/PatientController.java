package patientmanagementsystem.pms.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.his.entities.Patient;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.validation.Valid;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import patientmanagementsystem.pms.rabbitmq.PatientToDepartmentAllocator;
import patientmanagementsystem.pms.services.IPatientService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pms/patients")
@CrossOrigin
public class PatientController {

    private final IPatientService patientService;
    private final PatientToDepartmentAllocator patientToDepartmentAllocator;
    private final ObservationRegistry observationRegistry;

    public PatientController(IPatientService patientService, PatientToDepartmentAllocator patientToDepartmentAllocator, ObservationRegistry observationRegistry) {
        this.patientService = patientService;
        this.patientToDepartmentAllocator = patientToDepartmentAllocator;
        this.observationRegistry = observationRegistry;
    }

    @GetMapping("/get/all")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/get/{id}")
    public Patient getPatient(@PathVariable int id) {
        return Observation.createNotStarted("get-patient", observationRegistry).observe(() -> patientService.getPatientById(id));
    }

    @PostMapping("/admit/{department}")
    public Patient admitPatient(@RequestBody @Valid Patient patient, @PathVariable String department) throws MethodArgumentNotValidException {
        // When first created, a patient cannot have an id assigned
        if (patient.getId() != null) {
            var exception = getMethodArgumentNotValidException(patient);
            throw exception;
        }
        var admittedPatient = patientService.admitPatient(patient);
//        patientToDepartmentAllocator.sendAllocationMessage(admittedPatient, department);
        return Observation
                .createNotStarted("admit-patient", observationRegistry)
                .observe(() -> admittedPatient);
    }

    private void sendToAllocator(Patient patient, String department) {
    }

    private MethodArgumentNotValidException getMethodArgumentNotValidException(Patient patient) {
        var result = new BeanPropertyBindingResult(patient, "patient");
        var exception = new MethodArgumentNotValidException((MethodParameter) null, result);
        var fieldError =
                new FieldError("patient", "id", patient.getId(), true, null, null,
                        "Patient cannot have 'id' when first created. Did you mean to update patient?");
        exception.getBindingResult().addError(fieldError);
        return exception;
    }

    @PutMapping("/update-put")
    public Patient updatePatientPut(@RequestBody @Valid Patient patient) {
        return Observation
                .createNotStarted("update-put-patient", observationRegistry)
                .observe(() -> patientService.updatePatient(patient));
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
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody JsonPatch patch)
            throws JsonPatchException, JsonProcessingException {
        Patient Patient = patientService.getPatientById(id);
        Patient PatientPatched = applyPatchToPatient(patch, Patient);
        patientService.updatePatient(PatientPatched);
        return ResponseEntity.ok(PatientPatched);
    }

    private Patient applyPatchToPatient(
            JsonPatch patch, Patient targetPatient) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPatient, JsonNode.class));
        return objectMapper.treeToValue(patched, Patient.class);
    }
}
