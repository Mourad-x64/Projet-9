package com.openclassrooms.projet9microservicefront.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.projet9microservicefront.model.Patient;
import com.openclassrooms.projet9microservicefront.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;


    @GetMapping("/")
    public String getAll(Model model){

        model.addAttribute("patients", patientService.getAllPatients());

        return "patients_list";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model){

        model.addAttribute("patient", patientService.getPatient(id));

        return "patient";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("patient") Patient patient) throws JsonProcessingException {

        patientService.updatePatient(id, patient);
        //System.out.println("test");

        return "redirect:/patient/";
    }

}
