package com.cantelli.invisolar.domain;

import com.cantelli.invisolar.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numPeople;////////
    private int startPower;
    private Boolean boiler;
    private Boolean car;
    private int km;
    private Boolean badOrientation;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false, updatable = false)
    private User profileOwner;

    public Profile() {
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

    public int getStartPower() {
        return startPower;
    }

    public void setStartPower(int startPower) {
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

    public User getProfileOwner() {
        return profileOwner;
    }

    public void setProfileOwner(User profileOwner) {
        this.profileOwner = profileOwner;
    }
}
