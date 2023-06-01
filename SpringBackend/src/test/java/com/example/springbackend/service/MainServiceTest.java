package com.example.springbackend.service;

import com.example.springbackend.exception.NoteNotFoundException;
import com.example.springbackend.model.Note;
import com.example.springbackend.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private MainService underTest;

    @BeforeEach
    void setUp() {
        underTest = new MainService(noteRepository);
    }

    @Test
    void shouldGetAllNotes() {
        //given
        List<Note> expectedNotes = List.of(
                new Note(1L, "Title1", "First note", Date.from(Instant.now()), Date.from(Instant.now())),
                new Note(2L, "Title2", "Second note", Date.from(Instant.now()), Date.from(Instant.now())),
                new Note(3L, "Title3", "Third note", Date.from(Instant.now()), Date.from(Instant.now())),
                new Note(4L, "Title4", "Fourth note", Date.from(Instant.now()), Date.from(Instant.now())),
                new Note(5L, "Title5", "Fifth note", Date.from(Instant.now()), Date.from(Instant.now()))
        );
        //when
        when(noteRepository.findAll()).thenReturn(expectedNotes);
        //then
        assertThat(underTest.getAllNotes())
                .isNotNull()
                .isNotEmpty()
                .containsExactlyElementsOf(expectedNotes)
                .hasSameSizeAs(expectedNotes);
    }

    @Test
    void shouldFindNoteById() {
        //given
        Long givenId = 7L;
        Optional<Note> expectedNote = Optional.of(new Note(givenId, "Title", "Note",
                Date.from(Instant.now()), Date.from(Instant.now())));
        //when
        when(noteRepository.findNoteById(givenId)).thenReturn(expectedNote);

        //then
        assertThat(underTest.findNoteById(givenId))
                .isInstanceOf(Note.class)
                .isEqualTo(expectedNote.get())
                .extracting("id").isEqualTo(givenId);
        verify(noteRepository, times(1)).findNoteById(givenId);
    }

    @Test
    void shouldFindNoteByIdThrowException() {
        //given
        Long givenId = 16L;
        Optional<Note> expectedNote = Optional.of(new Note(givenId, "Title", "Note",
                Date.from(Instant.now()), Date.from(Instant.now())));
        //when
        when(noteRepository.findNoteById(givenId)).thenThrow(new NoteNotFoundException(givenId));

        //then
        assertThatThrownBy(() -> underTest.findNoteById(givenId))
                .isInstanceOf(NoteNotFoundException.class)
                .hasMessage("Nie ma notatki o id: " + givenId);

        verify(noteRepository, times(1)).findNoteById(givenId);
    }

    @Test
    void shouldAddNote() {
        //given
        Note toSave = new Note();
        toSave.setTitle("Title");
        toSave.setTextField("Description");

        //when
        underTest.addNote(toSave);

        //then
        ArgumentCaptor<Note> captureSavingNote = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(captureSavingNote.capture());
        Note capturedNote = captureSavingNote.getValue();

        verify(noteRepository, times(1)).save(toSave);
        assertThat(toSave)
                .isEqualTo(capturedNote);
    }

    @Test
    void shouldUpdateNote() {
        //given
        Note toUpdate = new Note();
        toUpdate.setTitle("Title");
        toUpdate.setTextField("Description");

        //when
        underTest.updateNote(toUpdate);

        //then
        ArgumentCaptor<Note> captureUpdatingNote = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(captureUpdatingNote.capture());
        Note capturedNote = captureUpdatingNote.getValue();

        verify(noteRepository, times(1)).save(toUpdate);
        assertThat(toUpdate)
                .isEqualTo(capturedNote);
    }

    @Test
    void shouldDeleteNote() {
        //given
        Long givenId = 4L;

        //when
        underTest.deleteNote(givenId);

        //then
        ArgumentCaptor<Long> captureGivenId = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).deleteById(captureGivenId.capture());
        Long capturedId = captureGivenId.getValue();

        verify(noteRepository, times(1)).deleteById(givenId);
        assertThat(givenId)
                .isEqualTo(capturedId);
    }
}
