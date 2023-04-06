package patientmanagementsystem.pms.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import patientmanagementsystem.pms.entities.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
