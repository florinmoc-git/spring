package healthinformationsystem.his.services;

import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.repos.PatientRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements IPatientService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public Patient getPatientById(int patientId) {
        var patientOpt =  patientRepo.findById(patientId);
        return patientOpt.get();
    }

    @Override
    public Patient admitPatient(Patient patient) {
        logger.info("Admitting patient: " + patient);
        return patientRepo.save(patient);
    }

    @Override
    public Patient dischargePatient(Patient patient) {
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }
}
