package com.acciojob.bookmyshow.application.Entities;

import com.acciojob.bookmyshow.application.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theaterSeats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeat {

    //Theater Seats are Physical Seats of Theater that are fixed
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    //establish the connection b/w Theater and TheaterSeat
    //TheaterSeat is Child and Theater is Parent

    @JoinColumn
    @ManyToOne()
    private Theater theater;


}
