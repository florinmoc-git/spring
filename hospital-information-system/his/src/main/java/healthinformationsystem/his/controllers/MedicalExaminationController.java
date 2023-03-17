package healthinformationsystem.his.controllers;

import healthinformationsystem.his.entities.MedicalExamination;
import healthinformationsystem.his.services.IMedicalExaminationService;
import healthinformationsystem.his.services.IPatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-examinations")
public class MedicalExaminationController {

    private final IPatientService patientService;
    private final IMedicalExaminationService medicalExaminationService;

    public MedicalExaminationController(IPatientService patientService, IMedicalExaminationService medicalExaminationService) {
        this.patientService = patientService;
        this.medicalExaminationService = medicalExaminationService;
    }

    @PostMapping("/add")
    public MedicalExamination addMedicalExamination(@RequestBody MedicalExamination medicalExamination){
        patientService.addIllnessToPatient(medicalExamination.getPatientId(), medicalExamination.getDiagnosis());
        return medicalExaminationService.addMedicalExamination(medicalExamination);
    }
}
