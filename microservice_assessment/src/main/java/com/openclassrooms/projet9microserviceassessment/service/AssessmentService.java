package com.openclassrooms.projet9microserviceassessment.service;

import com.openclassrooms.projet9microserviceassessment.model.Note;
import com.openclassrooms.projet9microserviceassessment.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    PropertyLoader propertyLoader;

    public String getAssessment(int id){

        RestTemplate restTemplate = new RestTemplate();
        Patient patient = restTemplate.getForObject(propertyLoader.getProperty("PATIENT_URI")+"get/"+id, Patient.class);

        Note[] response = restTemplate.getForObject(propertyLoader.getProperty("NOTE_URI")+"patient/"+id, Note[].class);
        List<Note> notes;
        notes = Arrays.asList(response);

        LocalDate dateOfBirth = LocalDate.parse(patient.getDateNaissance());
        LocalDate now = LocalDate.now();

        int age = calculateAge(dateOfBirth, now);

        String genre = patient.getGenre();

        String notesContent = "";

        if(notes.size() > 0){
            for(Note note:notes){
                notesContent = notesContent+" "+note.getNote();
                notesContent = notesContent.toLowerCase();
            }
        }else{
            return "NONE";
        }


        String[] items = {"hémoglobine a1c", "microalbumine", "taille",  "poids", "fumeur", "fumeuse", "anormal", "cholestérol", "vertiges", "rechute", "réaction", "anticorps"};


        long contains = stringContainsItemFromList(notesContent, items);

        if (stringContainsItemFromList(notesContent, items) == 0){
            return "NONE";
        }

        if (age > 30 && stringContainsItemFromList(notesContent, items) >= 2 && stringContainsItemFromList(notesContent, items) <= 5){
            return "BORDERLINE";
        }


        if(genre.equals("F")){
            if(age < 30 && stringContainsItemFromList(notesContent, items) >= 7){
                return "EARLY ONSET";
            }else if (age > 30 && stringContainsItemFromList(notesContent, items) >= 8) {
                return "EARLY ONSET";
            }

            if(age < 30 && stringContainsItemFromList(notesContent, items) >= 4){
                return "IN DANGER";
            }else if (age > 30 && stringContainsItemFromList(notesContent, items) >= 6) {
                return "IN DANGER";
            }

        }else if(genre.equals("M")){
            if(age < 30 && stringContainsItemFromList(notesContent, items) >= 5){
                return "EARLY ONSET";
            }else if (age > 30 && stringContainsItemFromList(notesContent, items) >= 8) {
                return "EARLY ONSET";
            }

            if(age < 30 && stringContainsItemFromList(notesContent, items) >= 3){
                return "IN DANGER";
            }else if (age > 30 && stringContainsItemFromList(notesContent, items) >= 6) {
                return "IN DANGER";
            }

        }

        return "NONE";


    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    private long stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).filter(inputStr::contains).count();
    }

    private long listStringContainsItemFromList(String[] inputStr, String[] items) {
        return Arrays.stream(items).filter(e -> Arrays.stream(inputStr).map(String::toLowerCase).anyMatch(n -> n.contains(e))).count();
    }


}
