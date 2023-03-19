package healthinformationsystem.his.unitTests;

import healthinformationsystem.his.controllers.PatientController;
import healthinformationsystem.his.entities.Patient;
import healthinformationsystem.his.services.IPatientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @DisplayName("The correct patient id is used in the search")
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
    @DisplayName("Patient is properly serialized to json")
    public void getPatientJsonTest() throws Exception {
        var patient = new Patient();
        patient.setId(1);
        patient.setWeight(20);
        when(patientService.getPatientById(1)).thenReturn(patient);

        mockMvc.perform(get("/patients/patient/{id}", 1))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.weight").value(20));

    }

    @Test
    @DisplayName("Searching for non existing patient returns NOT_FOUND")
    public void getPatientNonExistentTest() throws Exception {
        when(patientService.getPatientById(eq(99))).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/patients/patient/{id}", 99))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("No resource found at path: /patients/patient/99"));
    }

    @Test
    @DisplayName("Empty json object will throw validation errors")
    public void validationErrorsOnInvalidData() throws Exception {
        var patientJson = """
                {}
                """;

        mockMvc.perform(post("/patients/admit")
                        .content(patientJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Invalid arguments in JSON request"));
    }

    @Test
    @DisplayName("No validation errors when json contains valid data")
    public void noValidationErrorsOnValidData() throws Exception {
        var patient = new Patient();
        when(patientService.admitPatient(patient)).thenReturn(patient);

        var patientJson = """
                {
                    "title": "Ms",
                    "firstName": "Jamila",
                    "lastName": "Cuisine",
                    "birthDate": "12.03.1975",
                    "phone": "01908564897",
                    "email": "JamCui@sneakmail.co.uk",
                    "address": {
                        "number": 45,
                        "street": "St. Michaels",
                        "city": "Rainsfield",
                        "county": "Notts",
                        "postcode": "NG1 5PG",
                        "country": "UK"
                    },
                    "weight": 80,
                    "height": 1.68,
                        "wardId": 102,
                    "allergies": [
                        "Peanuts",
                        "Tetracycline",
                        "Strawberries"
                    ],
                    "illnesses": [
                        {
                            "name": "Cardiomyopathy",
                            "dateDiagnosed": "11.05.2022"
                        },
                        {
                            "name": "Tendinitis",
                            "dateDiagnosed": "11.12.2018"
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/patients/admit")
                        .content(patientJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.title").value("Ms"))
//                .andExpect(jsonPath("$.firstName").value( "Jamila"))
//                .andExpect(jsonPath("$.lastName").value("Cuisine"))
//                .andExpect(jsonPath("$.birthDate").value("12.03.1975"))
//                .andExpect(jsonPath("$.phone").value("01908564897"))
//                .andExpect(jsonPath("$.email").value("JamCui@sneakmail.co.uk"))
//                .andExpect(jsonPath("$.address.number").value(85))
//                .andExpect(jsonPath("$.address.street").value("St. Michaels"))
//                .andExpect(jsonPath("$.address.city").value("Rainsfield"))
//                .andExpect(jsonPath("$.address.county").value("Notts"))
//                .andExpect(jsonPath("$.address.postcode").value("NG1 5PG"))
//                .andExpect(jsonPath("$.address.country").value("UK"))
//                .andExpect(jsonPath("$.weight").value(80))
//                .andExpect(jsonPath("$.height").value(1.65))
//                .andExpect(jsonPath("$.wardId").value(102))
//                .andExpect(jsonPath("$.allergies[0]").value("Peanuts"))
//                .andExpect(jsonPath("$.allergies[1]").value("Tetracycline"))
//                .andExpect(jsonPath("$.allergies[2]").value("Strawberries"))
//                .andExpect(jsonPath("$.illnesses[0].name").value("Cardiomyopathy"))
//                .andExpect(jsonPath("$.illnesses[0].dateDiagnosed").value("11.05.2022"))
//                .andExpect(jsonPath("$.illnesses[1].name").value("Tendinitis"))
//                .andExpect(jsonPath("$.illnesses[1].dateDiagnosed").value("11.12.2018"))

    }
    @Test
    @DisplayName("Badly formatted birth date returns BAD_REQUEST")
    public void badlyFormattedBirthData() throws Exception{
        var patient = new Patient();
        when(patientService.admitPatient(patient)).thenReturn(patient);

        var patientJson = """
                {
                    "title": "Ms",
                    "firstName": "Jamila",
                    "lastName": "Cuisine",
                    "birthDate": "12.03.19756",
                    "phone": "01908564897",
                    "email": "JamCui@sneakmail.co.uk",
                    "address": {
                        "number": 45,
                        "street": "St. Michaels",
                        "city": "Rainsfield",
                        "county": "Notts",
                        "postcode": "NG1 5PG",
                        "country": "UK"
                    },
                    "weight": 80,
                    "height": 1.68,
                        "wardId": 102,
                    "allergies": [
                        "Peanuts",
                        "Tetracycline",
                        "Strawberries"
                    ],
                    "illnesses": [
                        {
                            "name": "Cardiomyopathy",
                            "dateDiagnosed": "11.05.2022"
                        },
                        {
                            "name": "Tendinitis",
                            "dateDiagnosed": "11.12.2018"
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/patients/admit")
                        .content(patientJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Malformed JSON request"));

    }
}
