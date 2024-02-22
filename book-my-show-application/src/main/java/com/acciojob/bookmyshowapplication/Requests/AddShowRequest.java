package com.acciojob.bookmyshowapplication.Requests;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class AddShowRequest {

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;//From this name we will figure out movieEntity

    private int theaterId;

    //ShowEntity is child to Movie Entity
    /*@JoinColumn
    @ManyToOne
    private Movie movie;*/

    /*@JoinColumn
    @ManyToOne
    private Theater theater;*/
}
