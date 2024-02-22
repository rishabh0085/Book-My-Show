package com.acciojob.bookmyshow.application.Requests;

import com.acciojob.bookmyshow.application.Enums.MovieFormat;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest {

    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;//from movie name we will figure out movie Entity
    private int theaterId;
    private MovieFormat movieFormat;

}
