package com.cantelli.invisolar.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cantelli.invisolar.domain.Visit;

import java.util.Date;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VisitRepository extends CrudRepository<Visit, Integer> {

    List<Visit> findByCity(String city);

    //@Query(value="SELECT '*' FROM visit WHERE  ")
    //List<Visit> findByDate(Date date);

    Visit findById(long id);

}