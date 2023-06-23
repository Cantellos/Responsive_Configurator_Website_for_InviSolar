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
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        if(house!=null){

            List<Quote> quotes = quoteService.findByUser(user);
            if(quotes.isEmpty()){
                model.addAttribute("hasQuotes", false);
            } else model.addAttribute("hasQuotes", true);

            double power = house.getPower();
            double dayPower = houseService.getDayPower(power);
            double nightPower = houseService.getNightPower(power);

            model.addAttribute("hasHouse", true);
            model.addAttribute("power", power);
            model.addAttribute("dayPower", dayPower);
            model.addAttribute("nightPower", nightPower);

        } else{
            model.addAttribute("hasHouse", false);
        }

        model.addAttribute("askFunding", false);
        model.addAttribute("user", user);

        return"configurator";
    }

    @RequestMapping("/myQuotes")
    public String myQuotes(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        List<Quote> quotes = quoteService.findByUser(user);

        if(!quotes.isEmpty()){
            model.addAttribute("quotes", quotes);
            model.addAttribute("hasQuote", true);
        } else model.addAttribute("hasQuote", false);

        model.addAttribute("user", user);

        return"myQuotes";
    }

    @RequestMapping("/newQuote")
    public String newQuote(
            Model model,
            Principal principal,
            @ModelAttribute("systemForm") String roofSystem,
            @ModelAttribute("styleForm") String style,
            @ModelAttribute("batteryForm") String battery
            ){

        User user = userService.findByUsername(principal.getName());
        House house = houseService.findByUser(user);
        double power = house.getPower();
        double dayPower = houseService.getDayPower(power);
        double nightPower = houseService.getNightPower(power);
        Quote quote = new Quote();

        quote.setUser(user);
        quote.setPowerDemand(power);
        quote.setRoofSystem(roofSystem);

        if(roofSystem.equals("Roof")){
            if(style!=null)quote.setTilesStyle(style);
            else quote.setTilesStyle("Black");
        } else quote.setTilesStyle("/");
        quote.setBattery(battery);

        quote = quoteService.create(quote, dayPower, nightPower);

        model.addAttribute("user", user);
        model.addAttribute("hasQuotes", true);
        model.addAttribute("hasHouse", true);
        model.addAttribute("power", power);
        model.addAttribute("dayPower", dayPower);
        model.addAttribute("nightPower", nightPower);
        model.addAttribute("quote", quote);
        model.addAttribute("askFunding", true);
        model.addAttribute("hasFunding", false);
        model.addAttribute("yesFunding", false);

        return "configurator";
    }

    @RequestMapping("/funding")
    public String funding(
            Model model,
            Principal principal,
            @ModelAttribute("quoteId") String quoteId,
            @ModelAttribute("fundingForm") String fundingForm,
            @ModelAttribute("numYears") String numYears
    ){

        User user = userService.findByUsername(principal.getName());
        House house = houseService.findByUser(user);
        double power = house.getPower();
        double dayPower = houseService.getDayPower(power);
        double nightPower = houseService.getNightPower(power);

        model.addAttribute("user", user);

        Quote quote = quoteService.findById(Long.parseLong(quoteId));

        boolean funding = Boolean.parseBoolean(fundingForm);

        quote.setFunding(funding);

        if(funding){
            double installments = quoteService.calculateFunding(quote, true, Integer.parseInt(numYears));
            quote.setInstallments(installments);
            model.addAttribute("yesFunding", true);
        } else model.addAttribute("yesFunding", false);

        quote = quoteService.save(quote);

        model.addAttribute("power", power);
        model.addAttribute("dayPower", dayPower);
        model.addAttribute("nightPower", nightPower);
        model.addAttribute("quote", quote);
        model.addAttribute("numYears", numYears);
        model.addAttribute("hasQuotes", true);
        model.addAttribute("hasHouse", true);
        model.addAttribute("askFunding", true);
        model.addAttribute("hasFunding", true);

        return "configurator";
    }

    @RequestMapping("/deleteQuote")
    public String deleteQuote(
            Model model,
            Principal principal,
            @RequestParam("id") String id
    ){
        User user = userService.findByUsername(principal.getName());

        Quote quote = quoteService.findById(Long.parseLong(id));

        quoteService.delete(quote);

        List<Quote> quotes = quoteService.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("quotes", quotes);
        return "/myQuotes";
    }

}
