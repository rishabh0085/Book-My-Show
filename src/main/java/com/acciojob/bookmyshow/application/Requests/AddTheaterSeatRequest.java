package com.acciojob.bookmyshow.application.Requests;

import lombok.Data;

@Data
public class AddTheaterSeatRequest {

    private int noOfClassicSeats;
    private int noOfPremiumSeats;
    private int theaterId;
}
