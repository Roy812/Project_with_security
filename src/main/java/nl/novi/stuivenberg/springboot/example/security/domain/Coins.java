package com.customuserdetailsservice.demo.model;

import javax.persistence.*;

@Entity
public class Coins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long userId;

    @Column
    private int amount;

    @Column
    private String coinsKey;

    //@ManyToOne
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    //private User user;

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userId;
    }

    public void setUserid(long userid) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCoinsKey() {
        return coinsKey;
    }

    public void setCoinKey(String coinKey) {
        this.coinsKey = coinsKey;
    }

}
