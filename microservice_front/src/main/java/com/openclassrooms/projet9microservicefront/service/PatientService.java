package com.openclassrooms.projet9microservicefront.service;

import com.openclassrooms.projet9microservicefront.model.Uri;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    public List<Object> getAllPatients(){
        RestTemplate restTemplate = new RestTemplate();
        Object[] patients = restTemplate.getForObject(Uri.ALL_PATIENTS, Object[].class);

        return Arrays.asList(patients);
    }

    public Object getPatient(int id){
        RestTemplate restTemplate = new RestTemplate();
        Object patient = restTemplate.getForObject(Uri.PATIENT+id, Object.class);

        return patient;
    }
}
