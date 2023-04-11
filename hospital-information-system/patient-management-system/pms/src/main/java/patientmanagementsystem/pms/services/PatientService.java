package patientmanagementsystem.pms.services;

import com.his.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import patientmanagementsystem.pms.repos.PatientRepo;

import java.util.List;
import java.util.Map;

@Service
public class PatientService implements IPatientService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public Patient getPatientById(int patientId) {
        logger.info("Retrieving patient with id: " + patientId);
        var patientOpt =  patientRepo.findById(patientId);
        return patientOpt.get();
    }

    @Override
    public Patient addIllnessToPatient(int patientId, String diagnosis){
        logger.info("Adding illness '"+ diagnosis +"' to patient with id: " + patientId);
        var patient = getPatientById(patientId);
        patient.addIllness(diagnosis);
        return patientRepo.save(patient);
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
        logger.info("Updating record for patient: " + patient);
        return patientRepo.save(patient);
    }

    // Interesting approach, but it only seems to work with primitives and strings. If you have an object (say Date or
    // Address), it falls over as it tries to set a String where a Date is expected. This could work if you use a
    // mapper or something - or if you do the conversion manually (not elegant).
    @Override
    public Patient updatePatientPatch(int patientId, Map<String, Object> fields) {
        var patient = getPatientById(patientId);
        fields.forEach((key, value) -> {
            var field = ReflectionUtils.findField(Patient.class, key);
            field.setAccessible(true);
//            if (key.equals("birthDate")){
//                var val = LocalDate.parse((CharSequence) value,  DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//                ReflectionUtils.setField(field, patient, val);
//            } else {
                ReflectionUtils.setField(field, patient, value);
//            }
        });
        return patientRepo.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

}
