package healthinformationsystem.his.repos;

import healthinformationsystem.his.entities.HospitalSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalSpecialityRepo extends JpaRepository<HospitalSpeciality, String> {
}
