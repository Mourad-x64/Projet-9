package com.openclassrooms.microservicenotes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.microservicenotes.model.Note;
import com.openclassrooms.microservicenotes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    NotesService notesService;

    @GetMapping("patient/{patientId}")
    public List<Note> getNotes(@PathVariable("patientId") int patientId){
        return notesService.getNotes(patientId);
    }

    @GetMapping("/{noteId}")
    public Note getNote(@PathVariable("noteId") String noteId){
        return notesService.getNote(noteId);
    }

    @PostMapping("/add")
    public Note addNote(@RequestBody Note note){
        return notesService.addNote(note);
    }

    @PutMapping("/update/{noteId}")
    public String updateNote(@PathVariable("noteId") String noteId, @RequestBody Note note) throws JsonProcessingException {
        return notesService.updateNote(noteId, note);
    }

    @DeleteMapping("/delete/{noteId}")
    public String delete(@PathVariable("noteId") String noteId){
        return notesService.deleteNote(noteId);
    }
}
