import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Note } from './note';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiUrl}/notes`)
  }

  public getNote(noteId: number): Observable<Note> {
    return this.http.get<Note>(`${this.apiUrl}/notes/${noteId}`)
  }

  public addNote(note: Note): Observable<Note> {
    return this.http.post<Note>(`${this.apiUrl}/notes/add`, note)
  }

  public updateNote(note: Note): Observable<Note> {
    return this.http.put<Note>(`${this.apiUrl}/notes/update`, note)
  }

  public deleteNote(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/notes/delete/${noteId}`)
  }

  
}
