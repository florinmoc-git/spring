package com.hospital.microservices.patient_service;

import com.hospital.microservices.patient_service.controller.PatientController;
import com.hospital.microservices.patient_service.entities.Patient;
import com.hospital.microservices.patient_service.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests validation of JSON payload
 */

@WebMvcTest(PatientController.class)
public class PatientControllerUnitTests {

//    @Autowired
//    private PatientController patientController;
    @MockBean
    private PatientService patientService;
	@Autowired
	private MockMvc mockMvc;

    @Test
	@Description("A patient is created when JSON is correctly formatted")
    public void shouldCreateAPatient() throws Exception {
		Patient patient = new Patient();
        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);
		String patientJson = """
				{
				    "title": "Mr",
				    "firstName": "Jim",
				    "lastName": "Elliot",
				    "address": {
				        "number": 124,
				        "street": "Soveja",
				        "city": "Constanta",
				        "county": "Constanta",
				        "postcode": "123454",
				        "country": "Romania"
				    },
				    "dob": "10.11.2012",
				    "phone": "0765569434",
				    "email": "jim@elliot.com",
				    "weight": 80,
				    "height": 1.80,
				    "wardId": 5
				}
				""";
		mockMvc.perform(post("/api/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientJson))
				.andDo(print())
				.andExpect(status().isCreated());
    }

	@Test
	@Description("The request is rejected when email is in the wrong format")
	public void shouldRejectWrongEmail() throws Exception {
		Patient patient = new Patient();
		when(patientService.createPatient(any(Patient.class))).thenReturn(patient);
		String patientJson = """
				{
				    "title": "Mr",
				    "firstName": "Jim",
				    "lastName": "Elliot",
				    "address": {
				        "number": 124,
				        "street": "Soveja",
				        "city": "Constanta",
				        "county": "Constanta",
				        "postcode": "123454",
				        "country": "Romania"
				    },
				    "dob": "10.11.2012",
				    "phone": "0765569434",
				    "email": "jimelliot.com",
				    "weight": 80,
				    "height": 1.80,
				    "wardId": 5
				}
				""";
		mockMvc.perform(post("/api/patient")
						.contentType(MediaType.APPLICATION_JSON)
						.content(patientJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
}
