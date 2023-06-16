package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.Profile;
import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.service.ProfileService;
import com.cantelli.invisolar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/profiling")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;

    @RequestMapping("")
    public String profiling(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("startingPower", false);
        return"profiling";
    }

    @RequestMapping(value = "/startingPowerForm", method = RequestMethod.POST)
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
        return"profiling";
    }

    @RequestMapping(value = "/profilingForm", method = RequestMethod.POST)
    public String profilingForm(
            Model model,
            Principal principal,
            @ModelAttribute("startPower") int startPower,
            @ModelAttribute("boiler") Boolean boiler,
            @ModelAttribute("car") Boolean car,
            @ModelAttribute("km") int km,
            @ModelAttribute("badOrientation") Boolean badOrientation
    ){

        User user = userService.findByUsername(principal.getName());
        Profile profile = new Profile();

        profile.setProfileOwner(user);
        profile.setStartPower(startPower);
        //...

        profile = profileService.create(profile);

        model.addAttribute("profile", profile);
        model.addAttribute("user", user);

        return"profiling";
    }

}
