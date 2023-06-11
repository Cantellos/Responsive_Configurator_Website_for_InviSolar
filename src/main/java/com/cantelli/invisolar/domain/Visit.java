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
    private String city;
    private String address;
    private int civic_number;

}
