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
    private Boolean funding;
    private long installments;

    /*@ManyToOne
    @JoinColumn(name="user_id")
    private User user;*/

}
