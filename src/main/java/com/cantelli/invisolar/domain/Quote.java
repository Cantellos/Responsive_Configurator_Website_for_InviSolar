package com.cantelli.invisolar.domain;

import javax.persistence.*;

@Entity
@Table(name="quote", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private String roofSystem;
    private String tilesStyle;
    private String battery;
    private Boolean funding;
    private double installments;
    private double powerDemand;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Quote() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getFunding() {
        return funding;
    }

    public void setFunding(Boolean funding) {
        this.funding = funding;
    }

    public double getInstallments() {
        return installments;
    }

    public void setInstallments(double installments) {
        this.installments = installments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoofSystem() {
        return roofSystem;
    }

    public void setRoofSystem(String roofSystem) {
        this.roofSystem = roofSystem;
    }

    public String getTilesStyle() {
        return tilesStyle;
    }

    public void setTilesStyle(String tilesStyle) {
        this.tilesStyle = tilesStyle;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public double getPowerDemand() {
        return powerDemand;
    }

    public void setPowerDemand(double powerDemand) {
        this.powerDemand = powerDemand;
    }
}
