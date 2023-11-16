package com.bicyclebooking.service;

import com.bicyclebooking.model.Station;
import com.bicyclebooking.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StationService {
    @Autowired private StationRepository repo;

    public List<Station> listAll(String keyword){
        if (keyword != null){
            return repo.findAll(keyword);
        }

        return repo.findAll();
    }

    public List<Integer> getAllStationId(){
        return repo.findAllStationID();
    }

    public Optional<Station> findByID(Integer stationID){
        return repo.findByStationId(stationID);
    }
}
