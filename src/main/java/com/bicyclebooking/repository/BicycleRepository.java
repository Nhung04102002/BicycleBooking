package com.bicyclebooking.repository;

import com.bicyclebooking.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BicycleRepository extends JpaRepository<Bicycle, Integer> {

    @Query("select count(b.bicycleId) from Bicycle b where b.stationId.stationId = ?1")
    int countByBicycleId(int stationID);

    Optional<Bicycle> findByBicycleId(int id);
}
