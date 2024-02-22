package com.acciojob.bookmyshowapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    private String seatNosBooked;

    private Integer totalAmountPaid;

    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private Show show;

    @JoinColumn
    @ManyToOne
    private User user;
}
