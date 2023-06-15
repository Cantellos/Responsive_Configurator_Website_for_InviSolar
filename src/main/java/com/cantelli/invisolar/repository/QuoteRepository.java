package com.cantelli.invisolar.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantelli.invisolar.domain.Quote;

public interface QuoteRepository extends CrudRepository<Quote, Long> {
    Quote findById(long id);
}