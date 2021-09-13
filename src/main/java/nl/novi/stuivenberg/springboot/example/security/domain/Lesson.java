package com.customuserdetailsservice.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String lessonDescription;

    @Lob
    private byte[] classVideo;

    //RELATIONSHIP ANNOTATIONS
    @OneToMany (mappedBy = "lesson")
    private List<Agenda> agenda;

    @ManyToOne
    private Teacher teacher;

    //GETTERS & SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public byte[] getClassVideo() {
        return classVideo;
    }

    public void setClassVideo(byte[] classVideo) {
        this.classVideo = classVideo;
    }

}
