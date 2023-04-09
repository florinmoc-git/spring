package com.hospital.patientadmin;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hospital.patientadmin.domain.Patient;
import com.hospital.patientadmin.domain.PatientRepository;

@DataJpaTest
public class PatientRepositoryTest {
	@Autowired
	private PatientRepository patRepo;
	
	@Test
	void savePatient() {
		patRepo.save(new Patient("Alam", "Lamb", "16 High Street, Dovental", "123 456 7820", LocalDate.now(), null));
		assertThat(patRepo.findByFirstName("Alam").isPresent()).isTrue();
	}
	
	@Test
	void deletePatients() {
		patRepo.save(new Patient("Rumbo", "Havana", "16 High Street, Dovental", "723 456 7820", LocalDate.now(), null));
		patRepo.deleteAll();
		assertThat(patRepo.count()).isEqualTo(0);
	}

}
