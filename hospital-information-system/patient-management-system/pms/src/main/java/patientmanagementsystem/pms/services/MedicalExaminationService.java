package patientmanagementsystem.pms.services;

import org.springframework.stereotype.Service;
import patientmanagementsystem.pms.entities.MedicalExamination;
import patientmanagementsystem.pms.repos.MedicalExaminationRepo;

@Service
public class MedicalExaminationService implements IMedicalExaminationService {
    private final MedicalExaminationRepo medicalExaminationRepo;

    public MedicalExaminationService(MedicalExaminationRepo medicalExaminationRepo) {
        this.medicalExaminationRepo = medicalExaminationRepo;
    }

    @Override
    public MedicalExamination addMedicalExamination(MedicalExamination medicalExamination) {
        return medicalExaminationRepo.save(medicalExamination);
    }
}
