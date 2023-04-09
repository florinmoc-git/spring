package com.hospital.patientadmin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.patientadmin.domain.RequestedProcedure;
import com.hospital.patientadmin.domain.RequestedProcedureRepository;

@RestController
public class RequestedProcedureController {
	
	private final RequestedProcedureRepository rpRepo;
	
	public RequestedProcedureController(RequestedProcedureRepository rpRepo) {
		this.rpRepo = rpRepo;
	}
	
	@GetMapping("requested-procedures")
	public Iterable<RequestedProcedure> getProcedures(){
		return rpRepo.findAll();
	}
}
