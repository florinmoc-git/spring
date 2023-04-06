package patientmanagementsystem.pms.services;

import com.his.entities.Patient;

import java.util.Map;

public interface IPatientService {

    /**
     *
     * @param patientId
     * @return
     */
    Patient getPatientById(int patientId);

    Patient addIllnessToPatient(int patientId, String diagnosis);

    /**
     * Admits a patient to hospital care. Creates patient if not exits.
     * @param patient
     * @return Patient
     */
    Patient admitPatient(Patient patient);

    /**
     * Discharges the patient from hospital care.
     * @param patient
     * @return Patient
     */
    Patient dischargePatient(Patient patient);

    /**
     * Updates patient medical state.
     * @param patient
     * @return Patient
     */
    Patient updatePatient(Patient patient);

    Patient updatePatientPatch(int patientId, Map<String, Object> fields);

}
