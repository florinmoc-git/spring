package com.hospital.microservices.patient_service.service;

import com.hospital.microservices.patient_service.entities.Patient;
import com.hospital.microservices.patient_service.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    public Patient getPatient(Long id) {
        return null;
    }
    public Patient updatePatient(Patient patient) {
        return patient;
    }
    public Patient deletePatient(Long id) {
        return null;
    }
}
