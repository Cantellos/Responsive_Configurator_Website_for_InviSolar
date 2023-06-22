package com.cantelli.invisolar.domain;

import javax.persistence.*;

@Entity
@Table(name="quote", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private Boolean roofSystem;
    private Boolean clayStyle;
    private Boolean bestBattery;
    private Boolean funding;
    private double installments;

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

    public Boolean getRoofSystem() {
        return roofSystem;
    }

    public void setRoofSystem(Boolean roofSystem) {
        this.roofSystem = roofSystem;
    }

    public Boolean getBestBattery() {
        return bestBattery;
    }

    public void setBestBattery(Boolean bestBattery) {
        this.bestBattery = bestBattery;
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

    public Boolean getClayStyle() {
        return clayStyle;
    }

    public void setClayStyle(Boolean clayStyle) {
        this.clayStyle = clayStyle;
    }
}
