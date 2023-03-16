package healthinformationsystem.his.services;

import healthinformationsystem.his.entities.Illness;
import healthinformationsystem.his.entities.MedicalExamination;
import healthinformationsystem.his.repos.MedicalExaminationRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
