package patientmanagementsystem.pms.dtos;

import com.his.entities.Patient;

public record PatientToDepartmentAllocationRequest(Patient patient, String routingKey) {}
