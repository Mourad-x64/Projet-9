package com.openclassrooms.projet9microservicefront.controller;

import com.openclassrooms.projet9microservicefront.model.Note;
import com.openclassrooms.projet9microservicefront.model.Patient;
import com.openclassrooms.projet9microservicefront.service.NoteService;
import com.openclassrooms.projet9microservicefront.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;
    @Autowired
    PatientService patientService;

    @GetMapping("/{id}")
    public String getNote(@PathVariable String id, Model model){
        model.addAttribute("note", noteService.getNote(id));

        return "note";
    }

    @GetMapping("/add")
    public String addNoteForm(Model model){
        model.addAttribute("note", new Note());
        model.addAttribute("patients", patientService.getAllPatients());
        return "note_form";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute("note") Note note) {
        noteService.addNote(note);
        return "redirect:/patient/"+note.getPatientId();
    }

    @GetMapping("edit/{id}")
    public String updateNoteForm(@PathVariable String id, Model model){
        model.addAttribute("note", noteService.getNote(id));
        model.addAttribute("patients", patientService.getAllPatients());

        return "note_form";
    }

    @PostMapping("edit/{id}")
    public String updatePatient(@PathVariable("id") String id, Model model, @ModelAttribute("note") Note note){
        noteService.updateNote(id, note);

        return "redirect:/patient/"+note.getPatientId();
    }

    @GetMapping("delete/{id}")
    public String deletePatient(@PathVariable("id") String id){
        int patientId = noteService.getNote(id).getPatientId();
        noteService.deleteNote(id);

        return "redirect:/patient/"+patientId;
    }

}
