package com.cantelli.invisolar.service;

import com.cantelli.invisolar.domain.Quote;
import com.cantelli.invisolar.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuoteService {

    Quote findById(long id);

    List<Quote> findByUser(User user);

    Quote create (Quote quote);

    Quote save(Quote quote);

}