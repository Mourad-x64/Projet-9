package openclassrooms.projet9MicroservicePatient;

import com.fasterxml.jackson.databind.ObjectMapper;

import openclassrooms.projet9MicroservicePatient.model.Patient;
import openclassrooms.projet9MicroservicePatient.repository.PatientRepository;
import openclassrooms.projet9MicroservicePatient.service.PatientService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class PatientControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    PatientService patientService;
    @Autowired
    PatientRepository patientRepository;

    @Before
    public void initPatient() {
        cleanDB();

        Patient patient = new Patient();
        patient.setNom("test");
        patient.setPrenom("test");
        patient.setGenre("F");
        patient.setAdresse("test");
        patient.setDateNaissance("test");
        patient.setTelephone("test");

        patientService.add(patient);
    }

    @After
    public void cleanDB(){
        patientRepository.deleteAll();
    }

    @Test
    public void testAddPatient() throws Exception {
        //clean db first
        cleanDB();

        Patient patient = new Patient();
        patient.setNom("test");
        patient.setPrenom("test");
        patient.setGenre("F");
        patient.setAdresse("test");
        patient.setDateNaissance("test");
        patient.setTelephone("test");

        ObjectMapper mapper = new ObjectMapper();
        String patientJson = mapper.writeValueAsString(patient);

        mvc.perform(MockMvcRequestBuilders
                .post("/patient/add")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("test"));

    }

    @Test
    public void testGetPatient() throws Exception {

        Patient patient = null;
        Optional<List<Patient>> optPatient = Optional.ofNullable(patientService.findAll());
        if(optPatient.isPresent()){
            patient = optPatient.get().get(0);
        }

        mvc.perform(MockMvcRequestBuilders
                .get("/patient/get/{id}", patient.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("test"));

    }

    @Test
    public void testGetAllPatients() throws Exception {
        Patient patient = new Patient();
        patient.setNom("test2");
        patient.setPrenom("test2");
        patient.setGenre("F");
        patient.setAdresse("test2");
        patient.setDateNaissance("test2");
        patient.setTelephone("test2");

        patientService.add(patient);

        mvc.perform(MockMvcRequestBuilders
                        .get("/patient/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));

    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient patient = null;
        Optional<List<Patient>> optPatient = Optional.ofNullable(patientService.findAll());
        if(optPatient.isPresent()){
            patient = optPatient.get().get(0);
        }

        patient.setGenre("M");

        ObjectMapper mapper = new ObjectMapper();
        String patientJson = mapper.writeValueAsString(patient);

        mvc.perform(MockMvcRequestBuilders
                .put("/patient/update/{id}", patient.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientJson))
                .andExpect(status().isOk());


        Optional<List<Patient>> optPatient2 = Optional.ofNullable(patientService.findAll());
        if(optPatient2.isPresent()){
            patient = optPatient.get().get(0);
        }

        Assert.assertEquals("M", patient.getGenre());

    }

    @Test
    public void testDeletePatient() throws Exception {
        Patient patient = null;
        Optional<List<Patient>> optPatient = Optional.ofNullable(patientService.findAll());
        if(optPatient.isPresent()){
            patient = optPatient.get().get(0);
        }

        mvc.perform(MockMvcRequestBuilders
                .delete("/patient/delete/{id}", patient.getId()))
                .andExpect(status().isOk());

        Assert.assertEquals(0, patientService.findAll().size());
    }
}
