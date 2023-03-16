package healthinformationsystem.his.services;

import healthinformationsystem.his.entities.MedicalExamination;
import org.springframework.stereotype.Service;


public interface IMedicalExaminationService {

    MedicalExamination addMedicalExamination(MedicalExamination medicalExamination);
}
