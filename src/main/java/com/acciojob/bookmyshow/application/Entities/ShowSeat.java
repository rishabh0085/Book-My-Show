package com.acciojob.bookmyshow.application.Entities;

import com.acciojob.bookmyshow.application.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "showSeat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {

    //ShowSeat are virtual seats on top of TheaterSeat(Physical Seats)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;

    private int seatPrice;

    private boolean isAvailable;

    private boolean isFoodAttached;

    private String seatNo;//These values come from theaterSeat

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;//based on Mapping or Seat Structure

    //Between ShowSeat and Show Entity ShowSeat is Parent and Show is Child
    @JoinColumn
    @ManyToOne
    private Show show;

}
