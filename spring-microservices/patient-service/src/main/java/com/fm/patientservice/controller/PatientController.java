package com.fm.patientservice.controller;

import com.fm.patientservice.model.Patient;
import com.fm.patientservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
    
    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping
    public Patient add(@RequestBody Patient patient){
        LOGGER.info("Patient add: {}", patient);
        return patientRepository.addPatient(patient);
    }

    @GetMapping
    public List<Patient> findAll(){
        LOGGER.info("Patient find all");
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable long id){
        LOGGER.info("Patient find: id={}", id);
        return patientRepository.findById(id);
    }

    @GetMapping("/ward/{wardId}")
    public List<Patient> findByWard(@PathVariable long wardId){
        LOGGER.info("Patient find: wardId={}", wardId);
        return patientRepository.findByWard(wardId);
    }
}
