package com.hospital.microservices.patient_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Tests creation of a patient
 */

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerIntegrationTests {
	@LocalServerPort
	private int port;
	@Autowired
	MySQLContainer<?> mysqlContainer;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		mysqlContainer.start();
	}

	@Test
	void shouldCreateAPatient() {
		String requestBody = """
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

		RestAssured
				.given()
					.contentType("application/json")
					.body(requestBody)
				.when()
					.post("/api/patient")
				.then()
					.statusCode(201)
					.body("id", notNullValue())
					.body("firstName", equalTo("Jim"))
					.body("lastName", equalTo("Elliot"))
					.body("phone", equalTo("0765569434"))
					.body("email", equalTo("jim@elliot.com"))
					.body("weight", equalTo(80))
					.body("height", equalTo(1.8F))
					.body("wardId", equalTo(5))
					.body("dob", equalTo("10.11.2012"))
					.body("address", notNullValue())
					.body("address.number", equalTo(124))
					.body("address.street", equalTo("Soveja"))
					.body("address.city", equalTo("Constanta"))
					.body("address.county", equalTo("Constanta"))
					.body("address.postcode", equalTo("123454"))
					.body("address.country", equalTo("Romania"));
	}

}
