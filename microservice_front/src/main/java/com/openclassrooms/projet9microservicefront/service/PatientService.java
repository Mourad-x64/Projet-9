package com.openclassrooms.projet9microservicefront.service;


import com.openclassrooms.projet9microservicefront.model.Patient;
import com.openclassrooms.projet9microservicefront.model.Uri;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    public List<Patient> getAllPatients(){
        RestTemplate restTemplate = new RestTemplate();
        Patient[] response = restTemplate.getForObject(Uri.ALL_PATIENTS, Patient[].class);

        return Arrays.asList(response);
    }

    public Object getPatient(int id){
        RestTemplate restTemplate = new RestTemplate();
        Object response = restTemplate.getForObject(Uri.GET_PATIENT+id, Patient.class);

        return response;
    }

    public Object addPatient(Patient patient){
        RestTemplate restTemplate = new RestTemplate();
        Patient response = restTemplate.postForObject(Uri.ADD_PATIENT, patient, Patient.class);

        return response;
    }

    public void updatePatient(int id, Patient patient){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(Uri.UPDATE_PATIENT+id, patient);
    }

    public void deletePatient(int id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(Uri.DELETE_PATIENT+id);
    }
}
