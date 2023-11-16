package com.bicyclebooking.controller;

import com.bicyclebooking.model.Bicycle;
import com.bicyclebooking.model.Station;
import com.bicyclebooking.model.User;
import com.bicyclebooking.service.BicycleService;
import com.bicyclebooking.service.StationService;
import com.bicyclebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BicycleController {

    @Autowired private BicycleService bicycleService;

    @Autowired private UserService userService;

    @Autowired private StationService stationService;

    public void userLogged(int userID, Model model){
        User user = userService.get(userID);
        model.addAttribute("user", user);
        model.addAttribute("userID", userID);
    }

    @GetMapping("/{userID}/code")
    public String getBikeId(@PathVariable("userID") int userID, Model model){
        userLogged(userID, model);
        model.addAttribute("checkRequest", new Bicycle());
        return "code";
    }

    @PostMapping("/{userID}/code")
    public String checkBikeId(@PathVariable("userID") int userID, @ModelAttribute Bicycle bicycle, Model model){
        userLogged(userID, model);
        Bicycle checkedBikeId = bicycleService.check(bicycle.getBicycleId());
        if (checkedBikeId != null){
            return "redirect:/{userID}/bookingBike/" + bicycle.getBicycleId();
        }
        return "redirect:/{userID}/code?fail";
    }

    @GetMapping("/{userID}/bookingBike/{bicycleID}")
    public String getBookingForm(@PathVariable("userID") int userID,@PathVariable("bicycleID") int bicycleID, Model model){
        userLogged(userID, model);
        Bicycle bicycle = bicycleService.check(bicycleID);
        Optional<Station> station = stationService.findByID(bicycle.getStationId().getStationId());
        model.addAttribute("station", station);
        model.addAttribute("bicycle", bicycle);
        return "booking";
    }

}
