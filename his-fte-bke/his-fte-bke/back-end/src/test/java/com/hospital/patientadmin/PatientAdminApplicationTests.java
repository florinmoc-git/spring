package com.hospital.patientadmin;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hospital.patientadmin.web.RequestedProcedureController;

@SpringBootTest
class PatientAdminApplicationTests {
	
	@Autowired
	private RequestedProcedureController controller;

	@Test
	@DisplayName("First example test case")
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
