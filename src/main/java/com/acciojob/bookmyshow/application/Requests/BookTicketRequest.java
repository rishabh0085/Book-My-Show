package com.acciojob.bookmyshow.application.Requests;

import com.acciojob.bookmyshow.application.Enums.SeatType;
import lombok.Data;
import java.util.List;

@Data
public class BookTicketRequest {

    private int showId;
    List<String> seatList; //No. of seats you want to book
    private SeatType seatType;
    private String emailId;
    private boolean foodAttached;
}
