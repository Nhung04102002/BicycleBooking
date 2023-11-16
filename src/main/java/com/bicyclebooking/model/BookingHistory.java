package com.bicyclebooking.model;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BookingHistory")
public class BookingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
    @OneToOne
    @JoinColumn(name = "bicycleId", columnDefinition = "int")
    private Bicycle bicycle;
    @Column
    private int stationBegin;
    @Column
    private int stationEnd;
    @Column(columnDefinition = "date")
    private Date bookingDate;
    @Column(columnDefinition = "date")
    private Date payDate;
    @Column(columnDefinition = "int null check(minute_total>0)")
    private int minuteTotal;
    @Column(columnDefinition = "int null check(fee_total>0)")
    private int feeTotal;
}
