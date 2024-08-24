package com.openclassrooms.projet9microservicefront.proxies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.projet9microservicefront.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-patient", url = "host.docker.internal:8080")
public interface PatientProxy {


    @GetMapping("/patient")
    List<Patient> getPatients();

    @GetMapping("/patient/get/{id}")
    Patient getPatient(@PathVariable Integer id);

    @PostMapping("/patient/add")
    Patient addPatient(@RequestBody Patient patient);

    @PutMapping("/patient/update/{id}")
    String updatePatient(@PathVariable("id") int id, @RequestBody Patient patient);

    @DeleteMapping("/patient/delete/{id}")
    String deletePatient(@PathVariable("id") int id);
}
