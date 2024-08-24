package com.openclassrooms.projet9microserviceassessment.proxies;

import com.openclassrooms.projet9microserviceassessment.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-notes", url = "host.docker.internal:8080")
public interface NotesProxy {
    /**
     * Recover notes
     *
     * @param id patient's id
     * @return notes list
     */

    @GetMapping("/notes/patient/{id}")
    List<Note> getNotes(@PathVariable Integer id);
}
