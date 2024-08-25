package com.hospital.microservices.patient_service.repository;

import com.hospital.microservices.patient_service.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
