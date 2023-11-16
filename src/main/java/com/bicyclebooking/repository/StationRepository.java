package com.bicyclebooking.repository;

import com.bicyclebooking.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Integer> {
    @Query("select p from Station p where " + "concat(p.stationId, p.stationName, p.address)" +
            " like %?1%")
    List<Station> findAll(String keyword);

    @Query("select p.stationId from Station p")
    List<Integer> findAllStationID();
    Optional<Station> findByStationId(int stationID);
}
