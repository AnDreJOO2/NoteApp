package com.example.springbackend.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(length = 300)
    @Size(min = 1, max = 300, message = "Maksymalna długość tytułu to 300 znaków")
    private String title;

    @Column(length = 3000)
    @Size(min = 1, max = 3000, message = "Maksymalna długość opisu to 3000 znaków")
    private String textField;


    @Temporal(TemporalType.DATE)
    private Date createdDate;


    @Temporal(TemporalType.TIME)
    private Date createdTime;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", textField='" + textField + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                '}';
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public Note() {
    }

}
