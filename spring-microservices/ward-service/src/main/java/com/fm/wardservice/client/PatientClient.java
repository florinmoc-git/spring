package com.fm.wardservice.client;

import com.fm.wardservice.model.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface PatientClient {
    @GetExchange("/patient/ward/{wardId}")
    public List<Patient> findByWard(@PathVariable long wardId);

}
