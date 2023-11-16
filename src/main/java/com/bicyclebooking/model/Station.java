package com.bicyclebooking.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stationId;
    @Column(columnDefinition = "nvarchar(100) not null ")
    private String stationName;
    @Column(columnDefinition = "nvarchar(200) not null ")
    private String address;
}
