package com.acciojob.bookmyshowapplication.Requests;

import lombok.Data;

@Data
public class AddTheaterRequest {

    private String theaterName;
    private String theaterAddress;
    private int numOfScreens;

}
