package healthinformationsystem.his.unitTests;

import healthinformationsystem.his.controllers.PatientController;
import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.services.IPatientService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @MockBean
    private IPatientService patientService;
    @Autowired
    private MockMvc mockMvc;
    @Captor
    private ArgumentCaptor<Integer> patientArgumentCaptor;

    @Test
    public void getPatientTest() throws Exception {
        var patient = new Patient();
        when(patientService.getPatientById(eq(1))).thenReturn(patient);

        mockMvc.perform(get("/patients/patient/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
        verify(patientService).getPatientById(patientArgumentCaptor.capture());
        assertTrue(patientArgumentCaptor.getValue() == 1);
    }

    @Test
    public void getPatientJsonTest() throws Exception {
        var patient = new Patient();
        patient.setId(1);
        patient.setWeight(20);
        patient.setAge(10);
        when(patientService.getPatientById(1)).thenReturn(patient);

        mockMvc.perform(get("/patients/patient/{id}", 1))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.weightInKg").value(20))
                .andExpect(jsonPath("$.age").value(10));

    }
}
