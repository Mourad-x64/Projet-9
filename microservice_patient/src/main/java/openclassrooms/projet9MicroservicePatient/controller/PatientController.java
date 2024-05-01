package openclassrooms.projet9MicroservicePatient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import openclassrooms.projet9MicroservicePatient.model.Patient;
import openclassrooms.projet9MicroservicePatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/")
    public List<Patient> home(){
        return patientService.findAll();
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") int id) throws JsonProcessingException {
        return patientService.get(id);
    }

    @PostMapping("/add")
    public Patient add(@RequestBody Patient patient){
        return patientService.add(patient);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @RequestBody Patient patient){
        return patientService.update(id, patient);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        return patientService.delete(id);
    }

}
