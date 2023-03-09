package healthinformationsystem.his.repos;


import healthinformationsystem.his.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
