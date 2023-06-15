package com.cantelli.invisolar.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="visit", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private int hour;
    private String city;
    private String address;
    private int civic_number;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Visit(long id, Date date, int hour, String city, String address, int civic_number, User user) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.city = city;
        this.address = address;
        this.civic_number = civic_number;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCivic_number() {
        return civic_number;
    }

    public void setCivic_number(int civic_number) {
        this.civic_number = civic_number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
