package springboot.with.security.domain;

import javax.persistence.*;

@Entity
public class Agenda {

    @Id
    @GeneratedValue
    private long id;

    //Attributen van deze Entity Agenda.
    @Column
    private String title;

    //Relatie: 1 of meerdere instanties van Agenda kunnen zich bevinden in één instantie van User.
    @ManyToOne
    private User user;

    //Relatie: 1 of meerdere instanties van Agenda kunnen zich bevinden in één instantie van Lesson.
    @ManyToOne
    private Lesson lesson;

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
