package com.openclassrooms.projet9microservicefront.service;


import com.openclassrooms.projet9microservicefront.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.util.Arrays;
import java.util.List;


@Service
public class PatientService {

    @Autowired
    PropertyLoader propertyLoader;

    public List<Patient> getAllPatients(){
        RestTemplate restTemplate = new RestTemplate();
        Patient[] response = restTemplate.getForObject(propertyLoader.getProperty("URI"), Patient[].class);

        return Arrays.asList(response);
    }

    public Object getPatient(int id){
        RestTemplate restTemplate = new RestTemplate();
        Object response = restTemplate.getForObject(propertyLoader.getProperty("URI")+"get/"+id, Patient.class);

        return response;
    }

    public Object addPatient(Patient patient){
        RestTemplate restTemplate = new RestTemplate();
        Patient response = restTemplate.postForObject(propertyLoader.getProperty("URI")+"add", patient, Patient.class);

        return response;
    }

    public void updatePatient(int id, Patient patient){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(propertyLoader.getProperty("URI")+"update/"+id, patient);
    }

    public void deletePatient(int id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(propertyLoader.getProperty("URI")+"delete/"+id);
    }
}
