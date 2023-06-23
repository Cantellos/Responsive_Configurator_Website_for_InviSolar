package com.cantelli.invisolar.repository;

import com.cantelli.invisolar.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cantelli.invisolar.domain.Visit;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {

    @Query(value="SELECT v FROM Visit v WHERE v.city= :city")
    List<Visit> findByCity(String city);

    @Query(value="SELECT v FROM Visit v WHERE v.date= :date")
    List<Visit> findByDate(Date date);

    Visit findById(long id);

    Visit findByUser(User user);
}