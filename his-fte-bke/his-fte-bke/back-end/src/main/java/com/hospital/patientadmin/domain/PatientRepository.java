package com.hospital.patientadmin.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository <Patient, Long> {

	public Optional<Patient> findByFirstName(String string);


}
