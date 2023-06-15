package com.cantelli.invisolar.domain;

import javax.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numPeople;
    private long startPower;
    private Boolean boiler;
    private Boolean car;
    private long km;
    private Boolean badOrientation;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Profile(long id, int numPeople, long startPower, Boolean boiler, Boolean car, long km, Boolean badOrientation, User user) {
        this.id = id;
        this.numPeople = numPeople;
        this.startPower = startPower;
        this.boiler = boiler;
        this.car = car;
        this.km = km;
        this.badOrientation = badOrientation;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public long getStartPower() {
        return startPower;
    }

    public void setStartPower(long startPower) {
        this.startPower = startPower;
    }

    public Boolean getBoiler() {
        return boiler;
    }

    public void setBoiler(Boolean boiler) {
        this.boiler = boiler;
    }

    public Boolean getCar() {
        return car;
    }

    public void setCar(Boolean car) {
        this.car = car;
    }

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public Boolean getBadOrientation() {
        return badOrientation;
    }

    public void setBadOrientation(Boolean badOrientation) {
        this.badOrientation = badOrientation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
