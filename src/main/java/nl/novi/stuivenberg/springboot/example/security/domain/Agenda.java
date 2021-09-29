package nl.novi.stuivenberg.springboot.example.security.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Agenda {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    //RELATIONSHIP ANNOTATIONS
    @ManyToOne
    private User user;

    @ManyToOne
    private Lesson lesson;

    //@OneToOne
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    //private User user;

    //GETTERS & SETTERS
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
