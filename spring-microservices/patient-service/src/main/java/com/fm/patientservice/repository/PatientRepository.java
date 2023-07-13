package com.fm.patientservice.repository;

import com.fm.patientservice.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository {
    private List<Patient> patients = new ArrayList<>();

    public Patient addPatient(Patient patient){
        patients.add(patient);
        return patient;
    }

    public Patient findById(long id){
        return patients.stream()
                .filter(patient -> patient.id() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Patient> findAll(){
        return patients;
    }

    public List<Patient> findByWard(long wardId){
        return patients.stream()
                .filter(patient -> patient.id() == wardId)
                .toList();
    }
}
