package com.cantelli.invisolar.domain;

import javax.persistence.*;

@Entity
@Table(name="quote", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long price;
    private String type;
    private Boolean cheapBattery;
    private Boolean funding;
    private long installments;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Quote(long id, long price, String type, Boolean cheapBattery, Boolean funding, long installments, User user) {
        this.id = id;
        this.price = price;
        this.type = type;
        this.cheapBattery = cheapBattery;
        this.funding = funding;
        this.installments = installments;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCheapBattery() {
        return cheapBattery;
    }

    public void setCheapBattery(Boolean cheapBattery) {
        this.cheapBattery = cheapBattery;
    }

    public Boolean getFunding() {
        return funding;
    }

    public void setFunding(Boolean funding) {
        this.funding = funding;
    }

    public long getInstallments() {
        return installments;
    }

    public void setInstallments(long installments) {
        this.installments = installments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
