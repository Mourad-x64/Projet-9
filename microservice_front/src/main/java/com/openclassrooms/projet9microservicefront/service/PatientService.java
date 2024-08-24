package com.openclassrooms.projet9microservicefront.service;


import com.openclassrooms.projet9microservicefront.model.Patient;
import com.openclassrooms.projet9microservicefront.proxies.AssessmentProxy;
import com.openclassrooms.projet9microservicefront.proxies.NotesProxy;
import com.openclassrooms.projet9microservicefront.proxies.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;



import java.util.Arrays;
import java.util.List;


@Service
public class PatientService {


    @Value("${PATIENT_URI}")
    String patientUri;

    @Autowired
    PatientProxy patientProxy;


    @Bean
    RestOperations restTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication("user", "user").build();
    }


    public List<Patient> getAllPatients(){
        //RestTemplate restTemplate = new RestTemplate();
        //Patient[] response = restTemplate.getForObject(patientUri, Patient[].class);

        //return Arrays.asList(response);

        return patientProxy.getPatients();
    }


    public Object getPatient(int id){
        //RestTemplate restTemplate = new RestTemplate();
        //Object response = restTemplate.getForObject(patientUri+"/get/"+id, Patient.class);

        //return response;

        return patientProxy.getPatient(id);
    }

    public Object addPatient(Patient patient){
        //RestTemplate restTemplate = new RestTemplate();
        //Patient response = restTemplate.postForObject(patientUri+"/add", patient, Patient.class);

        //return response;

        return patientProxy.addPatient(patient);
    }

    public void updatePatient(int id, Patient patient){
        //RestTemplate restTemplate = new RestTemplate();
        //restTemplate.put(patientUri+"/update/"+id, patient);

        patientProxy.updatePatient(id, patient);
    }

    public void deletePatient(int id){
        //RestTemplate restTemplate = new RestTemplate();
        //restTemplate.delete(patientUri+"/delete/"+id);

        patientProxy.deletePatient(id);
    }
}
