package patientmanagementsystem.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.his.*")
public class PatientManagementSystem {

	public static void main(String[] args) {
		SpringApplication.run(PatientManagementSystem.class, args);
	}

}
