package com.cantelli.invisolar.service.impl;

import com.cantelli.invisolar.domain.House;
import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.repository.HouseRepository;
import com.cantelli.invisolar.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public House findById(long id){
        return houseRepository.findById(id);
    }

    @Override
    public House findByUser(User user){
        return houseRepository.findByUser(user);
    }

    @Override
    public House create(House house){

        double power = house.getStartPower();

        if(house.getBoiler()){
            power += power*0.2;
        }

        if(house.getCar()){
            power += house.getKm();
        }

        if(house.getBadOrientation()){
            power = power*1.3;
        }

        house.setPower(power);

        house = houseRepository.save(house);

        return house;
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public double getDayPower(double power){

        double dailyPower = power/365;

        double dayPower = dailyPower*4/3;

        double hourPower = dayPower/5;

        return (Math.ceil(hourPower));

    }

    @Override
    public double getNightPower(double power){

        double dailyPower = power/365;

        double nightPower = dailyPower*2/3;

        nightPower=(Math.ceil(nightPower));

        return nightPower;
    }

}
