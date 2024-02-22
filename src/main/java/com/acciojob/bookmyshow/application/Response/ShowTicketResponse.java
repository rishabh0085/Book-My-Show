package com.acciojob.bookmyshow.application.Response;

import com.acciojob.bookmyshow.application.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
public class ShowTicketResponse {

    private String movieName;
    private String theaterInfo;
    private LocalDate showDate;
    private LocalTime showTime;
    private int totalAmountPaid;
    private String seatNo;
    private SeatType seatType;

}
