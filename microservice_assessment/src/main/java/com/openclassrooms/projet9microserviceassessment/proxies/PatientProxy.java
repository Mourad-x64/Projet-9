package com.openclassrooms.projet9microserviceassessment.proxies;

import com.openclassrooms.projet9microserviceassessment.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-patient", url = "host.docker.internal:8080")
public interface PatientProxy {
    /**
     * Recover patient with his id
     *
     * @param id patient's id
     * @return patient if exists else throw NotFoundException
     */

    @GetMapping("/patient/get/{id}")
    Patient getPatient(@PathVariable Integer id);

}



