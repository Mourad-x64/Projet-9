package com.openclassrooms.projet9microservicefront.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.projet9microservicefront.model.Patient;
import com.openclassrooms.projet9microservicefront.model.Uri;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.DataInput;
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

    public Object updatePatient(int id, Patient patient) throws JsonProcessingException {

        //ObjectMapper mapper = new ObjectMapper();
        //Patient patientObject = mapper.readValue(patient, Patient.class);

        RestTemplate restTemplate = new RestTemplate();
        Object response = restTemplate.postForObject(Uri.PATIENT+id, patient, Object.class);

        return response;
    }
}
