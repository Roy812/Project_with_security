package nl.novi.stuivenberg.springboot.example.security.domain;

import javax.persistence.*;

@Entity
public class Agenda {

    @Id
    @GeneratedValue
    private long id;

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
