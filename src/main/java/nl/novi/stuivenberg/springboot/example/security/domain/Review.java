package nl.novi.stuivenberg.springboot.example.security.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String review;

    @Column
    private Long rating;

    @Column
    private String teacherReply;


    //RELATIONSHIP ANNOTATIONS
    @ManyToOne
    private User user;


    //@ManyToOne
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    //private User user;

    //GETTERS & SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTeacherReply() {
        return teacherReply;
    }

    public void setTeacherReply(String teacherReply) {
        this.teacherReply = teacherReply;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
