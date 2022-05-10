package com.example.springbackend.exception;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(Long id){
        super("Nie ma notatki o id: "+id);
    }
}
