package nl.novi.stuivenberg.springboot.example.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Attributen van deze Entity Lesson.
    @Column
    private String title;

    @Lob
    private byte[] classVideo;

    @Lob
    private byte[] lessonGuide;

    //Relatie: 1 Lesson kan zich bevinden in meerdere instanties van Agenda.
    @OneToMany (mappedBy = "lesson")
    @JsonIgnore
    private List<Agenda> agenda;

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getClassVideo() {
        return classVideo;
    }

    public void setClassVideo(byte[] classVideo) {
        this.classVideo = classVideo;
    }

    public byte[] getLessonGuide() {
        return lessonGuide;
    }

    public void setLessonGuide(byte[] lessonGuide) {
        this.lessonGuide = lessonGuide;
    }

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }
}
