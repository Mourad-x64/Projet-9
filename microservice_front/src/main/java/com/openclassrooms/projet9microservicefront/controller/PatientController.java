package com.openclassrooms.projet9microservicefront.controller;


import com.openclassrooms.projet9microservicefront.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


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

}
