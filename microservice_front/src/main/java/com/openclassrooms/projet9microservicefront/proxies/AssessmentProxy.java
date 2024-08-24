package com.openclassrooms.projet9microservicefront.proxies;

import com.openclassrooms.projet9microservicefront.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-assessment", url = "host.docker.internal:8080")
public interface AssessmentProxy {

    @GetMapping("/assessment/{id}")
    String getAssessment(@PathVariable int id);
}
