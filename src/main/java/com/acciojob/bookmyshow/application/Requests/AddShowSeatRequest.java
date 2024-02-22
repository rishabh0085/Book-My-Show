package com.acciojob.bookmyshow.application.Requests;

import lombok.Data;

@Data
public class AddShowSeatRequest {

   private int priceForClassicSeats;
   private int priceForPremiumSeats;
   private boolean isFoodAttached;
   private int showId;

}
