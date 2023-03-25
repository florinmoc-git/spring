package healthinformationsystem.his.controllers;

import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.rabbitmq.TriageRequestConsumer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/triage")
public class TriageRequestController {

    private final TriageRequestConsumer triageRequestConsumer;

    public TriageRequestController(TriageRequestConsumer triageRequestConsumer) {
        this.triageRequestConsumer = triageRequestConsumer;
    }

    @GetMapping("/get-next")
    public ResponseEntity<Patient> getNextPatient(){
        var patient = triageRequestConsumer.consumeTriageRequest();
        if (patient != null){
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
