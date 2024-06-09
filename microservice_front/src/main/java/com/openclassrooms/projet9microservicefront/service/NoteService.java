package com.openclassrooms.projet9microservicefront.service;

import com.openclassrooms.projet9microservicefront.model.Note;
import com.openclassrooms.projet9microservicefront.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    PropertyLoader propertyLoader;

    public List<Note> getAllNotes(int id){
        RestTemplate restTemplate = new RestTemplate();
        Note[] response = restTemplate.getForObject(propertyLoader.getProperty("NOTE_URI")+"patient/"+id, Note[].class);

        return Arrays.asList(response);
    }


    public Note getNote(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Note response = restTemplate.getForObject(propertyLoader.getProperty("NOTE_URI")+id, Note.class);

        return response;
    }

    public Note addNote(Note note) {
        RestTemplate restTemplate = new RestTemplate();
        Note response = restTemplate.postForObject(propertyLoader.getProperty("NOTE_URI")+"add", note, Note.class);

        return response;
    }

    public void updateNote(String id, Note note){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(propertyLoader.getProperty("NOTE_URI")+"update/"+id, note);
    }

    public void deleteNote(String id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(propertyLoader.getProperty("NOTE_URI")+"delete/"+id);
    }
}
