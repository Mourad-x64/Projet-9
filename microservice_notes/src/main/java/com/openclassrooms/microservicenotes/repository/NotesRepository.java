package com.openclassrooms.microservicenotes.repository;

import com.openclassrooms.microservicenotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepository extends MongoRepository<Note, String> {
    public List<Note> findByPatientId(int patientId);
}
