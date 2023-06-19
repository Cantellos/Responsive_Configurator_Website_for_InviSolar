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
    @Transactional
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

}
