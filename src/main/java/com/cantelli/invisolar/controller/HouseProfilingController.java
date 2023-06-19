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
@RequestMapping("/houseProfiling")
public class HouseProfilingController {

    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("")
    public String houseProfiling(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        House house = houseService.findByUser(user);

        model.addAttribute("startingPower", false);

        if(house!=null){
            model.addAttribute("house", house);
            if(house.getStartPower()>0){
                model.addAttribute("startingPower", true);
                model.addAttribute("startPower", Integer.toString(house.getStartPower()));
            }
        }

        model.addAttribute("user", user);

        return"houseProfile";
    }

    @RequestMapping("myHouse")
    public String myHouse(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        House house = houseService.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("house", house);

        return"myHouse";
    }

    @RequestMapping(value = "/modifyStartPower")
    public String modifyStartPower(
            Model model,
            Principal principal
    ){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("startingPower", false);

        model.addAttribute("user", user);
        return "houseProfile";
    }

    @RequestMapping(value = "/startProfileForm", method = RequestMethod.POST)
    public String startingPowerForm(
            Model model,
            Principal principal,
            @ModelAttribute("numPeople") String numPeople,
            @ModelAttribute(value="actualPower") String actualPower
    ){

        User user = userService.findByUsername(principal.getName());

        if(Integer.parseInt(numPeople)<=0&&Integer.parseInt(actualPower)<=0){
            model.addAttribute("nullStartPower", true);
            model.addAttribute("startingPower", false);
        }else {

            int startPower=0;
            if(Integer.parseInt(actualPower)>0){
                startPower=Integer.parseInt(actualPower);
            } else if(Integer.parseInt(numPeople)>0){
                startPower=Integer.parseInt(numPeople)*700;
            }

            model.addAttribute("startingPower", true);
            model.addAttribute("startPower", startPower);
        }

        model.addAttribute("user", user);
        return "houseProfile";
    }

    @RequestMapping(value = "/createProfileForm", method = RequestMethod.POST)
    public String houseProfilingForm(
            Model model,
            Principal principal,
            @ModelAttribute("startPower") String startPower,
            @ModelAttribute("boilerCheckbox") String boiler,
            @ModelAttribute("orientationCheckbox") String badOrientation,
            @ModelAttribute("carCheckbox") String car,
            @ModelAttribute("kmCheckbox") String km
    ){

        User user = userService.findByUsername(principal.getName());
        House house = houseService.findByUser(user);

        if(house==null){
            house = new House();
        }

        if(!startPower.isEmpty()&&!startPower.equals("0")){
            house.setStartPower(Integer.parseInt(startPower));
        } else{
            model.addAttribute("missingStartPower", house);
            model.addAttribute("startingPower", true);
            model.addAttribute("startPower", "0");
            model.addAttribute("user", user);
            return "houseProfile";
        }


        house.setUser(user);
        house.setBoiler(Boolean.parseBoolean(boiler));
        house.setBadOrientation(Boolean.parseBoolean(badOrientation));
        house.setCar(Boolean.parseBoolean(car));
        if(Boolean.parseBoolean(car)){
            house.setKm(Integer.parseInt(km));
        } else house.setKm(0);

        house = houseService.create(house);

        model.addAttribute("house", house);
        model.addAttribute("user", user);

        return"myHouse";
    }
}
