package com.openclassrooms.projet9microservicefront.proxies;

import com.openclassrooms.projet9microservicefront.model.Note;
import com.openclassrooms.projet9microservicefront.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-notes", url = "host.docker.internal:8080")
public interface NotesProxy {

    @GetMapping("/notes/patient/{id}")
    List<Note> getNotes(@PathVariable("id") int id);

    @GetMapping("/notes/{id}")
    Note getNote(@PathVariable String id);

    @PostMapping("/notes/add")
    Note addNote(@RequestBody Note note);

    @PutMapping("/notes/update/{id}")
    String updateNote(@PathVariable("id") String id, @RequestBody Note note);

    @DeleteMapping("/notes/delete/{id}")
    String deleteNote(@PathVariable("id") String id);

}
