package com.openclassrooms.projet9microserviceassessment.service;

import com.openclassrooms.projet9microserviceassessment.model.Note;
import com.openclassrooms.projet9microserviceassessment.model.Patient;
import com.openclassrooms.projet9microserviceassessment.proxies.NotesProxy;
import com.openclassrooms.projet9microserviceassessment.proxies.PatientProxy;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Service
public class AssessmentService {


    @Value("${PATIENT_URI}")
    String patientUri;
    @Value("${NOTE_URI}")
    String noteUri;

    @Autowired
    PatientProxy patientProxy;
    @Autowired
    NotesProxy notesProxy;



    public String getAssessment(int id){




        Patient patient = patientProxy.getPatient(id);



        List<Note> notes = notesProxy.getNotes(id);

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
