package com.hospital.patientadmin;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hospital.patientadmin.domain.Patient;
import com.hospital.patientadmin.domain.PatientRepository;
import com.hospital.patientadmin.domain.RequestedProcedure;
import com.hospital.patientadmin.domain.RequestedProcedureRepository;
import com.hospital.patientadmin.domain.User;
import com.hospital.patientadmin.domain.UserRepository;
import com.hospital.patientadmin.proxy.MirthProxy;

@SpringBootApplication
public class PatientAdminApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(PatientAdminApplication.class);
	
	@Autowired
	private MirthProxy mirthProxy;
	@Autowired
	private PatientRepository patRepo;
	@Autowired
	private RequestedProcedureRepository rpRepo;
	@Autowired
	private UserRepository urepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientAdminApplication.class, args);
		logger.info("Application started");
	}
	
//	Patient(String firstName, String lastName, String address, String nhsNumber, Date dateOfBirth,
//			Date dateOfDeath, List<RequestedProcedure> procedures)

	@Override
	public void run(String... args) throws Exception {
		
		Patient p1 = new Patient("John", "Lambergh", "215 Long Street, Portmouth", "123 456 7890", LocalDate.now(), null);
		Patient p2 = new Patient("Phill", "Collins", "11 High Street, Dovental", "123 456 7890", LocalDate.now(), null);
		Patient p3 = new Patient("Mo", "Jave", "8 Groovy lane, Copperwall", "123 456 7890", LocalDate.now(), null);
		patRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		RequestedProcedure rp1 = new RequestedProcedure("CT Head", "Head", "Dr Jarvis", "NEW", LocalDate.now(), LocalDate.now(), p1);
		RequestedProcedure rp2 = new RequestedProcedure("CT Abdo", "Abdoment", "Dr James", "MORE_INFO_NEEDED", LocalDate.now(), LocalDate.now(), p2);
		RequestedProcedure rp3 = new RequestedProcedure("XR Leg", "Leg", "Dr Sopater", "CANCELLED", LocalDate.now(), LocalDate.now(), p3);
		RequestedProcedure rp4 = new RequestedProcedure("CT Head", "Head", "Dr James", "IN_REVIEW", LocalDate.now(), LocalDate.now(), p2);
		RequestedProcedure rp5 = new RequestedProcedure("CT Head", "Head", "Dr Abrahame", "IN_REVIEW", LocalDate.now(), LocalDate.now(), p1);	
		rpRepo.saveAll(Arrays.asList(rp1, rp2, rp3, rp4, rp5));
		
		// Username: user, password: user
		urepository.save(new User("user", "$2a$12$tnnXJen8v.t/tuuhooPQAODOihwvtUxfUULAgBo6SZS9LT/xrvALa", "USER"));
		
		// Username: admin, password: admin
		urepository.save(new User("admin", "$2a$12$/I3WMY1qsDUb5FD45OkuA.jrpJ5w/aiB9.l6/Z8CUmriOtqXJ/zgG", "ADMIN"));
//		var thing = mirthProxy.getChannelsAndStatuses();
//		System.out.println("Status: " + thing);
	
	}

}
