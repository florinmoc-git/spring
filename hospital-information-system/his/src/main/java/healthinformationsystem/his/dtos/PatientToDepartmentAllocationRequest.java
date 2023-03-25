package healthinformationsystem.his.dtos;

import healthinformationsystem.his.entities.Patient;

public class PatientToDepartmentAllocationRequest {
    private String routingKey;
    private Patient patient;

    public PatientToDepartmentAllocationRequest( Patient patient, String routingKey) {
        this.routingKey = routingKey;
        this.patient = patient;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
