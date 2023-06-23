package com.cantelli.invisolar.domain;

import javax.persistence.*;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double power;
    private int startPower;
    private Boolean boiler;
    private Boolean car;
    private int km;
    private Boolean badOrientation;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false, updatable = false)
    private User user;

    public House() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double Power) {
        this.power = Power;
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

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
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
