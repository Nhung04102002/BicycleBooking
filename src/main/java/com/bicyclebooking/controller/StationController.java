package com.bicyclebooking.controller;

import com.bicyclebooking.model.Station;
import com.bicyclebooking.service.BicycleService;
import com.bicyclebooking.service.StationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StationController {
    @Autowired private StationService service;
    @Autowired private BicycleService bicycleService;

    @GetMapping("/{userID}/station")
    public String showStationList(@PathVariable("userID") int userID, @Param("keyword") String keyword, Model model, HttpServletRequest request){
        List<Station> listStation = service.listAll(keyword);
        List<Integer> listStationID = service.getAllStationId();
        for (int i : listStationID) {
            model.addAttribute("quantity"+i, bicycleService.countBike(i));
        }
        model.addAttribute("userID", userID);
        model.addAttribute("listStation", listStation);
        model.addAttribute("keyword", keyword);
        return "station";
    }
}
