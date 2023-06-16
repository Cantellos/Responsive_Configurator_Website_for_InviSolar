package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.House;
import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.service.HouseService;
import com.cantelli.invisolar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profiling")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("")
    public String profiling(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("startingPower", false);
        return"houseProfile";
    }

    @RequestMapping("myHouse")
    public String myHouse(
            Model model,
            Principal principal
        ){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        House house = houseService.findByProfileOwner(user);
        if(house!=null){
            model.addAttribute("startingPower", house);
        }
        model.addAttribute("startingPower", false);
        return"houseProfile";
    }

    @RequestMapping(value = "/startProfileForm", method = RequestMethod.POST)
    public String startingPowerForm(
            Model model,
            Principal principal,
            @ModelAttribute("numPeople") String numPeople,
            @ModelAttribute(value="actualPower") String actualPower
       ){

        User user = userService.findByUsername(principal.getName());

        int startPower=0;
        if(!actualPower.isEmpty()){
            startPower=Integer.parseInt(actualPower);
        } else if(!numPeople.isEmpty()){
            startPower=Integer.parseInt(numPeople)*700;
        }

        model.addAttribute("startingPower", true);
        model.addAttribute("startPower", startPower);
        model.addAttribute("user", user);
        return"houseProfile";
    }

    @RequestMapping(value = "/createProfileForm", method = RequestMethod.POST)
    public String profilingForm(
            Model model,
            Principal principal,
            @ModelAttribute("startPower") int startPower,
            @ModelAttribute("boiler") Boolean boiler,
            @ModelAttribute("badOrientation") Boolean badOrientation,
            @ModelAttribute("car") Boolean car,
            @ModelAttribute("km") int km
        ){

        User user = userService.findByUsername(principal.getName());
        House house = new House();

        house.setUser(user);
        house.setStartPower(startPower);
        house.setBoiler(boiler);
        house.setBadOrientation(badOrientation);
        house.setCar(car);
        if(car){
            house.setKm(km);
        } else house.setKm(0);

        house = houseService.create(house);

        model.addAttribute("profile", house);
        model.addAttribute("user", user);

        return"myHouse";
    }
}
