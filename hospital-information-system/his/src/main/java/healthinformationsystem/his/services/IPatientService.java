package healthinformationsystem.his.services;

import healthinformationsystem.his.entities.Patient;

public interface IPatientService {

    /**
     *
     * @param patientId
     * @return
     */
    Patient getPatientById(int patientId);

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
}
