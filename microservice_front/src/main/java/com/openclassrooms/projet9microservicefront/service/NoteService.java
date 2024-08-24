package com.openclassrooms.projet9microservicefront.service;

import com.openclassrooms.projet9microservicefront.model.Note;
import com.openclassrooms.projet9microservicefront.model.Patient;
import com.openclassrooms.projet9microservicefront.proxies.NotesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class NoteService {


    @Value("${NOTE_URI}")
    String noteUri;

    @Autowired
    NotesProxy notesProxy;

    public List<Note> getAllNotes(int id){
        //RestTemplate restTemplate = new RestTemplate();
        //Note[] response = restTemplate.getForObject(noteUri+"/patient/"+id, Note[].class);

        //return Arrays.asList(response);

        return notesProxy.getNotes(id);
    }


    public Note getNote(String id) {
        //RestTemplate restTemplate = new RestTemplate();
        //Note response = restTemplate.getForObject(noteUri+"/"+id, Note.class);

        //return response;

        return notesProxy.getNote(id);
    }

    public Note addNote(Note note) {
        //RestTemplate restTemplate = new RestTemplate();
        //Note response = restTemplate.postForObject(noteUri+"/add", note, Note.class);

        //return response;

        return notesProxy.addNote(note);
    }

    public void updateNote(String id, Note note){
        //RestTemplate restTemplate = new RestTemplate();
        //restTemplate.put(noteUri+"/update/"+id, note);

        notesProxy.updateNote(id, note);
    }

    public void deleteNote(String id){
        //RestTemplate restTemplate = new RestTemplate();
        //restTemplate.delete(noteUri+"/delete/"+id);

        notesProxy.deleteNote(id);
    }
}
