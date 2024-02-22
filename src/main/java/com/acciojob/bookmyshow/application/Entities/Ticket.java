package com.acciojob.bookmyshow.application.Entities;

import com.acciojob.bookmyshow.application.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "ticket")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private String NoOfBookedSeats;

    private Integer totalAmountPaid;

    private String movieName;

    private String theaterNameAndAddress;

    private LocalTime movieTiming;

    private LocalDate movieDate;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @JoinColumn
    @ManyToOne
    private Show show;

    @JoinColumn
    @ManyToOne
    private User user;//connecting it to user as user is parent and ticket is child entity

}
