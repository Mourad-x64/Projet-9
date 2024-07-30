package com.openclassrooms.projet9microserviceassessment.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.projet9microserviceassessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id) {
        return assessmentService.getAssessment(id);
    }

}
