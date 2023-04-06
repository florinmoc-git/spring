package patientmanagementsystem.pms.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import patientmanagementsystem.pms.entities.MedicalExamination;
import patientmanagementsystem.pms.services.IMedicalExaminationService;
import patientmanagementsystem.pms.services.IPatientService;

@RestController
@RequestMapping("/medical-examinations")
public class MedicalExaminationController {

    private final IPatientService patientService;
    private final IMedicalExaminationService medicalExaminationService;

    public MedicalExaminationController(IPatientService patientService, IMedicalExaminationService medicalExaminationService) {
        this.patientService = patientService;
        this.medicalExaminationService = medicalExaminationService;
    }

    @PostMapping("/add/{patientId}")
    @Transactional
    public MedicalExamination addMedicalExamination(@RequestBody MedicalExamination medicalExamination, @PathVariable int patientId){
        var patient = patientService.getPatientById(patientId);
        medicalExamination.setPatient(patient);
        var addedMedicalExamination = medicalExaminationService.addMedicalExamination(medicalExamination);
        patient.addMedicalExamination(addedMedicalExamination);
        patientService.updatePatient(patient);

        return addedMedicalExamination;
    }
}
