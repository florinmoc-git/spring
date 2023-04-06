package patientmanagementsystem.pms.repos;

import com.his.entities.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalExaminationRepo extends JpaRepository<MedicalExamination, Integer> {
}
