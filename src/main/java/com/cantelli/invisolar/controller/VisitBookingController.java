package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.House;
import com.cantelli.invisolar.domain.Quote;
import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.Visit;
import com.cantelli.invisolar.service.HouseService;
import com.cantelli.invisolar.service.UserService;
import com.cantelli.invisolar.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/visitBooking")
public class VisitBookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private HouseService houseService;

    @RequestMapping("")
    public String visitBooking(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        Visit visit = visitService.findByUser(user);

        if(visit!=null){
            model.addAttribute("hasVisit", true);
            model.addAttribute("visit", visit);
        } else model.addAttribute("hasVisit", false);

        model.addAttribute("editVisit", false);
        model.addAttribute("user", user);

        return"visitBooking";
    }

    @RequestMapping("/myVisit")
    public String myVisit(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        Visit visit = visitService.findByUser(user);
        if(visit!=null){
            model.addAttribute("visit", visit);
            model.addAttribute("hasVisit", true);
        } else model.addAttribute("hasVisit", false);

        model.addAttribute("user", user);

        return "myVisit";
    }

    @RequestMapping(value="/newVisit", method = RequestMethod.POST)
    public String newVisit(
            Model model, Principal principal,
            @ModelAttribute("action") String action,
            @ModelAttribute("city") String city,
            @ModelAttribute("address") String address,
            @ModelAttribute("zip") String zip,
            @ModelAttribute("state") String state,
            @ModelAttribute("date") String date,
            @ModelAttribute("hour") String hour
            ) throws NumberFormatException, ParseException {

        User user = userService.findByUsername(principal.getName());

        Visit visit = new Visit();
        visit.setUser(user);
        visit.setCity(city);
        visit.setAddress(address);
        visit.setZip(zip);
        visit.setState(state);
        visit.setDate(date);
        visit.setHour(Integer.parseInt(hour));
        visit = visitService.create(visit);

        model.addAttribute("user", user);
        model.addAttribute("visit", visit);
        model.addAttribute("hasVisit", true);

        return "myVisit";
    }

    @RequestMapping(value="/editVisit", method = RequestMethod.POST)
    public String editVisit(
            Model model, Principal principal,
            @ModelAttribute("city") String city,
            @ModelAttribute("address") String address,
            @ModelAttribute("zip") String zip,
            @ModelAttribute("state") String state,
            @ModelAttribute("date") String date,
            @ModelAttribute("hour") String hour
    ) throws NumberFormatException, ParseException {

        User user = userService.findByUsername(principal.getName());

        Visit visit = visitService.findByUser(user);
        visit.setUser(user);
        visit.setCity(city);
        visit.setAddress(address);
        visit.setZip(zip);
        visit.setState(state);
        visit.setDate(date);
        visit.setHour(Integer.parseInt(hour));
        visit = visitService.save(visit);

        model.addAttribute("user", user);
        model.addAttribute("visit", visit);
        model.addAttribute("hasVisit", true);

        return "myVisit";
    }

    @RequestMapping("/goEdit")
    public String editVisit(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        Visit visit = visitService.findByUser(user);

        model.addAttribute("editVisit", true);
        model.addAttribute("hasVisit", false);
        model.addAttribute("user", user);
        model.addAttribute("visit", visit);

        return"visitBooking";
    }

    @RequestMapping("/deleteVisit")
    public String deleteVisit(Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        Visit visit = visitService.findByUser(user);

        visitService.delete(visit);

        model.addAttribute("user", user);
        model.addAttribute("hasVisit", false);
        model.addAttribute("editVisit", false);

        return"visitBooking";
    }

}
