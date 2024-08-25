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





    public List<Patient> getAllPatients(){


        return patientProxy.getPatients();
    }


    public Object getPatient(int id){


        return patientProxy.getPatient(id);
    }

    public Object addPatient(Patient patient){


        return patientProxy.addPatient(patient);
    }

    public void updatePatient(int id, Patient patient){


        patientProxy.updatePatient(id, patient);
    }

    public void deletePatient(int id){


        patientProxy.deletePatient(id);
    }
}
