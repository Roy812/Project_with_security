package nl.novi.stuivenberg.springboot.example.security.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Attributen van deze Entity Review.
    @Column
    private String title;

    @Column
    private String review;

    @Column
    private Long rating;

    @Column
    private String teacherReply;

    //Relatie: 1 of meerdere gebruikers kunnen zich bevinden in 1 instantie van een Review.
    @ManyToOne
    private User user;

    //Getters & Setters
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
