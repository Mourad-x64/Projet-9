package com.openclassrooms.projet9microservicefront.service;

import com.openclassrooms.projet9microservicefront.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AssessmentService {

    @Autowired
    PropertyLoader propertyLoader;

    public String getAssessment(int id){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(propertyLoader.getProperty("ASSESSMENT_URI")+id, String.class);

        return response;
    }
}
