package com.openclassrooms.microservicenotes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.microservicenotes.model.Note;
import com.openclassrooms.microservicenotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    public List<Note> getNotes(int patientId){
        return notesRepository.findByPatientId(patientId);
    }

    public Note getNote(String noteId){
        Note note = null;
        Optional<Note> opt = notesRepository.findById(noteId);
        if(opt.isPresent()){
            note = opt.get();
        }

        return note;
    }

    public Note addNote(Note note){
        String id = UUID.randomUUID().toString();
        note.setId(id);

        return notesRepository.save(note);
    }

    public String updateNote(String id, Note note) throws JsonProcessingException {
        Optional<Note> opt = notesRepository.findById(id);

        if(opt.isPresent()){
            note.setId(opt.get().getId());
            notesRepository.save(note);
            ObjectMapper mapper = new ObjectMapper();
            String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(opt.get());
            return response;
        }else {
            return "note not found";
        }
    }

    public String deleteNote(String id) {
        Optional<Note> opt = notesRepository.findById(id);

        if(opt.isPresent()){
            notesRepository.delete(opt.get());
        }else {
            return "invalid note id";
        }

        return "note deleted";
    }
}
