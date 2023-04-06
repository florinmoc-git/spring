package patientmanagementsystem.pms.dtos;

import patientmanagementsystem.pms.entities.Patient;

public record PatientToDepartmentAllocationRequest(Patient patient, String routingKey) {}
