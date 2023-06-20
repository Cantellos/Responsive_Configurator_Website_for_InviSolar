package com.cantelli.invisolar.service.impl;

import com.cantelli.invisolar.domain.House;
import com.cantelli.invisolar.domain.Quote;
import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.repository.HouseRepository;
import com.cantelli.invisolar.repository.QuoteRepository;
import com.cantelli.invisolar.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Quote findById(long id){
        return quoteRepository.findById(id);
    }

    @Override
    public List<Quote> findByUser(User user){
        return quoteRepository.findByUser(user);
    }

    @Override
    @Transactional
    public Quote create(Quote quote){



        return quote;
    }

    @Override
    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

}
