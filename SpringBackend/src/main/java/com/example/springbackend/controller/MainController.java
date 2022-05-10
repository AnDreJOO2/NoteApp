package com.example.springbackend.controller;

import com.example.springbackend.model.Note;
import com.example.springbackend.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = mainService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
        Note note = mainService.findNoteById(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("/notes/add")
    public ResponseEntity<Note> addNote(@Valid @RequestBody Note note) {
        Note newNote = mainService.addNote(note);
        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    @PutMapping("/notes/update")
    public ResponseEntity<Note> updateNote(@Valid @RequestBody Note note) {
        Note updatedNote = mainService.updateNote(note);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    @DeleteMapping("/notes/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
        mainService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
