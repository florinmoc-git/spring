package com.fm.wardservice.controller;


import com.fm.wardservice.client.PatientClient;
import com.fm.wardservice.model.Ward;
import com.fm.wardservice.repository.WardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ward")
public class WardController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(WardController.class);

    private WardRepository wardRepository;
    private PatientClient patientClient;

    public WardController(WardRepository wardRepository, PatientClient patientClient) {
        this.wardRepository = wardRepository;
        this.patientClient = patientClient;
    }

    @PostMapping
    public Ward add(@RequestBody Ward ward){
        LOGGER.info("Ward add: {}", ward);
        return wardRepository.addWard(ward);
    }

    @GetMapping
    public List<Ward> findAll(){
        LOGGER.info("Ward find");
        return wardRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ward findById(@PathVariable long id){
        LOGGER.info("Ward find: id={}", id);
        return wardRepository.findById(id);
    }

    @GetMapping("/with-patients")
    public List<Ward> findAllWithPatients(){
        LOGGER.info("Ward find all with patients");
        List<Ward> wards = wardRepository.findAll();
        wards.forEach(ward -> ward.setPatients(patientClient.findByWard(ward.getId())));
        return wards;
    }

}
