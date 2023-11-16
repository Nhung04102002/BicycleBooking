package com.bicyclebooking.service;

import com.bicyclebooking.model.Bicycle;
import com.bicyclebooking.repository.BicycleRepository;
import org.springframework.stereotype.Service;

@Service
public class BicycleService {
    private final BicycleRepository bicycleRepository;

    public BicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    public Bicycle check(int bicycleId){
        return bicycleRepository.findByBicycleId(bicycleId).orElse(null);
    }

    public int countBike(int stationID){return bicycleRepository.countByBicycleId(stationID);}
}
