import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Note } from './note';
import { NoteService } from './note.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public notes: Note[] = [];
  public editNote!: Note;
  public deleteNote!: Note;

  constructor(private noteService: NoteService){

  }

  ngOnInit(){
      this.getNotes();
  }

  public getNotes(): void{
    this.noteService.getNotes().subscribe(
      (response: Note[])=>{
        this.notes = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  //w zależności czy add, edit, delete ustawia modal
  public openModalAdd(mode: string): void{
      const container = document.getElementById('main-container');

      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');
      if(mode === 'add'){
        button.setAttribute('data-target', '#addnotesmodal');
      }

      container?.appendChild(button);
      button.click();
  }

  public openModalUpdate(note: Note, mode: string): void{
    const container = document.getElementById('main-container');

    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if(mode === 'edit'){
      this.editNote = note;
      button.setAttribute('data-target', '#updatenotesmodal');
    }
    container?.appendChild(button);
    button.click();
}

public openModalDelete(note: Note, mode: string): void{
  const container = document.getElementById('main-container');

  const button = document.createElement('button');
  button.type = 'button';
  button.style.display = 'none';
  button.setAttribute('data-toggle', 'modal');
  if(mode === 'delete'){
    this.deleteNote = note;
    button.setAttribute('data-target', '#deletenotesmodal');
  }


  container?.appendChild(button);
  button.click();
}

public onAddNote(addForm: NgForm): void{
  document.getElementById('btn-add-note')?.click();
  this.noteService.addNote(addForm.value).subscribe(
  (response: Note) =>{
    console.log(response);
    this.getNotes();
    addForm.reset();
  },
  (error: HttpErrorResponse) =>{
    alert(error.message);
    addForm.reset();
  }
)
}

public onUpdateNote(note: Note): void{
  this.noteService.updateNote(note).subscribe(
  (response: Note) =>{
    console.log(response);
    this.getNotes();
  },
  (error: HttpErrorResponse) =>{
    alert(error.message);
  }
)
}

public onDeleteNote(noteId: number): void{
  this.noteService.deleteNote(noteId).subscribe(
  (response: void) =>{
    console.log(response);
    this.getNotes();
  },
  (error: HttpErrorResponse) =>{
    alert(error.message);
  }
)
}



}
