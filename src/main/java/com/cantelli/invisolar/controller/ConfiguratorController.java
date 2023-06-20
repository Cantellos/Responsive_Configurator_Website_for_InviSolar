package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.House;
import com.cantelli.invisolar.domain.Quote;
import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.service.HouseService;
import com.cantelli.invisolar.service.QuoteService;
import com.cantelli.invisolar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/configurator")
public class ConfiguratorController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private HouseService houseService;

    @RequestMapping("")
    public String configurator(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        House house = houseService.findByUser(user);
        List<Quote> quotes = quoteService.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("house", house);
        model.addAttribute("quotes", quotes);

        return"configurator";
    }

    @RequestMapping("/myQuotes")
    public String myHouse(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        House house = houseService.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("house", house);

        return"myQuotes";
    }
}
