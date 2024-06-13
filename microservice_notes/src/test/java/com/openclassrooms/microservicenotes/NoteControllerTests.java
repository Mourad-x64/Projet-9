package com.openclassrooms.microservicenotes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.microservicenotes.model.Note;
import com.openclassrooms.microservicenotes.repository.NotesRepository;
import com.openclassrooms.microservicenotes.service.NotesService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class NoteControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    NotesService notesService;
    @Autowired
    NotesRepository notesRepository;

    @Before
    public void initPatient() {
        cleanDB();

        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setNote("TEST");
        note.setPatientId(102);
        note.setDate("2024-06-09");


        notesService.addNote(note);
    }

    @After
    public void cleanDB(){
        notesRepository.deleteAll();
    }

    @Test
    public void testAddNote() throws Exception {
        //clean db first
        cleanDB();

        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setNote("TEST ADD");
        note.setPatientId(102);
        note.setDate("2024-06-09");

        ObjectMapper mapper = new ObjectMapper();
        String patientJson = mapper.writeValueAsString(note);

        mvc.perform(MockMvcRequestBuilders
                        .post("/notes/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.note").value("TEST ADD"));

    }

    @Test
    public void testGetNote() throws Exception {

        Note note = null;
        Optional<List<Note>> optPatient = Optional.ofNullable(notesService.getNotes(102));
        if(optPatient.isPresent()){
            note = optPatient.get().get(0);
        }

        mvc.perform(MockMvcRequestBuilders
                        .get("/notes/{id}", note.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.note").value("TEST"));

    }

    @Test
    public void testGetAllNotes() throws Exception {
        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setNote("TEST2");
        note.setPatientId(102);
        note.setDate("2024-06-09");

        notesService.addNote(note);

        mvc.perform(MockMvcRequestBuilders
                        .get("/notes/patient/102"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));

    }

    @Test
    public void testUpdateNote() throws Exception {
        Note note = null;
        Optional<List<Note>> optPatient = Optional.ofNullable(notesService.getNotes(102));
        if(optPatient.isPresent()){
            note = optPatient.get().get(0);
        }

        note.setNote("TEST EDITED");

        ObjectMapper mapper = new ObjectMapper();
        String patientJson = mapper.writeValueAsString(note);

        mvc.perform(MockMvcRequestBuilders
                        .put("/notes/update/{id}", note.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJson))
                .andExpect(status().isOk());


        Optional<List<Note>> optPatient2 = Optional.ofNullable(notesService.getNotes(102));
        if(optPatient2.isPresent()){
            note = optPatient.get().get(0);
        }

        Assert.assertEquals("TEST EDITED", note.getNote());

    }

    @Test
    public void testDeleteNote() throws Exception {
        Note note = null;
        Optional<List<Note>> optPatient = Optional.ofNullable(notesService.getNotes(102));
        if(optPatient.isPresent()){
            note = optPatient.get().get(0);
        }

        mvc.perform(MockMvcRequestBuilders
                        .delete("/notes/delete/{id}", note.getId()))
                .andExpect(status().isOk());

        Assert.assertEquals(0, notesService.getNotes(102).size());
    }
}

