package com.customuserdetailsservice.demo.model;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String review;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
