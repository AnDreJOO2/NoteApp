package com.example.springbackend.repository;

import com.example.springbackend.model.Note;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NoteRepositoryTest {

    @Autowired
    private NoteRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindNoteById() {
        //given
        Long givenId = 1L;
        Note givenNote = new Note();
        givenNote.setId(givenId);
        givenNote.setTitle("Title");
        givenNote.setTextField("Description");

        underTest.save(givenNote);
        //when
        Note noteById = underTest.findNoteById(givenId).get();
        //then

        assertThat(noteById)
                .isEqualTo(givenNote);
    }
}
