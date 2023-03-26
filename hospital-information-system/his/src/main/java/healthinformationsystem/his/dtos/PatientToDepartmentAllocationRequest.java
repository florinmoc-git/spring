package healthinformationsystem.his.dtos;

import healthinformationsystem.his.entities.Patient;

public record PatientToDepartmentAllocationRequest(Patient patient, String routingKey) {}
