package patientmanagementsystem.pms.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import patientmanagementsystem.pms.entities.MedicalExamination;

public interface MedicalExaminationRepo extends JpaRepository<MedicalExamination, Integer> {
}
