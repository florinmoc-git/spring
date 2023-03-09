package healthinformationsystem.his.repos;

import healthinformationsystem.his.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalDepartmentRepo extends JpaRepository<Department, String> {
}
