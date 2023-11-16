package com.bicyclebooking.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bicycle")
public class Bicycle {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int IDENTITY(135238,4)")
    private int bicycleId;

    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "stationId")
    private Station stationId;
}
