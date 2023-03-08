package healthinformationsystem.his.repos;

import healthinformationsystem.his.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepo extends JpaRepository<Hospital, Integer> {
}
