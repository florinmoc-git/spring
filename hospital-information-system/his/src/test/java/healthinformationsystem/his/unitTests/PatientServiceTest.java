package healthinformationsystem.his.unitTests;

import healthinformationsystem.his.entities.Illness;
import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.repos.PatientRepo;
import healthinformationsystem.his.services.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepo patientRepo;
    @InjectMocks
    private PatientService patientService;

    @Captor
    private ArgumentCaptor<Patient> patientArgumentCaptor;

    @Test
    public void getPatientByIdTest(){
        var patient = new Patient();
        when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
        assertEquals(patient, patientService.getPatientById(1));
        verify(patientRepo).findById(1);
    }

    @Test
    public void addIllnessToPatientTest(){
        var patient = new Patient();
        when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
        patientService.addIllnessToPatient(1, "Headache");
        verify(patientRepo).save(patientArgumentCaptor.capture());
        Patient p = patientArgumentCaptor.getValue();
        assertTrue(p.getIllnesses().size() == 1);
        var illness = p.getIllnesses().iterator().next();
        assertEquals("Headache", illness.getName());
        assertEquals(LocalDate.now(), illness.getDateDiagnosed());
    }

}
