package com.cantelli.invisolar.repository;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {
    Quote findById(long id);

    List<Quote> findByUser(User user);

    //findNewest @Query
}