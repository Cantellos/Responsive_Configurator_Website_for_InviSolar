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

    @Autowired
    private HouseRepository houseRepository;


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
    public Quote create(Quote quote, double dayPower, double nightPower){

        double systemPrice, batteryPrice, price;

        if(quote.getRoofSystem()){
            systemPrice = dayPower*2.5;
            if(quote.getClayStyle()){
                systemPrice = systemPrice*1.25;
            }
        } else systemPrice = dayPower*1.5;

        if(quote.getBestBattery()){
            batteryPrice = nightPower*1.3;
        } else batteryPrice = nightPower;

        price = systemPrice + batteryPrice;

        price = price*1000;

        quote.setPrice(Math.ceil(price*100)/100);

        quote = quoteRepository.save(quote);

        return quote;
    }

    @Override
    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public double calculateFunding(Quote quote, boolean funding, int numYears){

        double price = quote.getPrice();

        double rate = Math.pow((1+0.07), numYears);

        price = price*rate;

        double installments = price/(12*numYears);

        return Math.ceil(installments*100)/100;
    }

}
