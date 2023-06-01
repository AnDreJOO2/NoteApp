package com.example.springbackend.service;

import com.example.springbackend.exception.NoteNotFoundException;
import com.example.springbackend.model.Note;
import com.example.springbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MainService {

    private final NoteRepository noteRepository;

    public MainService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note findNoteById(Long id) {
        return noteRepository.findNoteById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    public Note addNote(Note note) {
        setTime(note);
        return noteRepository.save(note);
    }

    public Note updateNote(Note note) {
        setTime(note);
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    private void setTime(Note note) {
        Date today = Calendar.getInstance().getTime();
        note.setCreatedDate(today);
        note.setCreatedTime(today);
    }

}
