package patientmanagementsystem.pms.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patientmanagementsystem.pms.entities.Patient;
import patientmanagementsystem.pms.rabbitmq.AllocationRequestConsumer;
import patientmanagementsystem.pms.rabbitmq.PatientToDepartmentAllocator;
import patientmanagementsystem.pms.services.IPatientService;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/allocator")
public class AllocationRequestController {

    private final IPatientService patientService;
    private final AllocationRequestConsumer allocationRequestConsumer;
    private final PatientToDepartmentAllocator patientToDepartmentAllocator;
    @Value("#{${rabbitmq.department.request.queues}}")
    private Map<String, String> requestQueues;

    public AllocationRequestController(
            IPatientService patientService, AllocationRequestConsumer allocationRequestConsumer,
            PatientToDepartmentAllocator patientToDepartmentAllocator
    ) {
        this.patientService = patientService;
        this.allocationRequestConsumer = allocationRequestConsumer;
        this.patientToDepartmentAllocator = patientToDepartmentAllocator;
    }

    @GetMapping("/get-next/{department}")
    public ResponseEntity<Patient> getNextPatient(@PathVariable String department) {
        checkDepartmentExists(department);
        var patient = allocationRequestConsumer.consumeAllocationRequest(requestQueues.get(department));
        if (patient != null) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/reallocate/{department}")
    public Patient reallocatePatient(@PathVariable String department, @RequestBody @Valid Patient patient) {
        checkDepartmentExists(department);
        var updatedPatient = patientService.updatePatient(patient);
        patientToDepartmentAllocator.sendAllocationMessage(updatedPatient, department);
        return updatedPatient;
    }

    private void checkDepartmentExists(String department) {
        if (!requestQueues.containsKey(department.toLowerCase())) {
            throw new NoSuchElementException("Department not found: " + department);
        }
    }
}
