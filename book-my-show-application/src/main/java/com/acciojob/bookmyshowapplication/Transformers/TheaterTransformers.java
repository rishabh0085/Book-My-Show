package com.acciojob.bookmyshowapplication.Transformers;

import com.acciojob.bookmyshowapplication.Entities.Theater;
import com.acciojob.bookmyshowapplication.Requests.AddTheaterRequest;

public class TheaterTransformers {

    public static Theater convertRequestToEntity(AddTheaterRequest theaterRequest)
    {

        //why static method?
        //so that u can call the method directly call them using class name

        Theater theater = Theater.builder()
                .theaterAddress(theaterRequest.getTheaterAddress())
                .theaterName(theaterRequest.getTheaterName())
                .numOfScreens(theaterRequest.getNumOfScreens())
                .build();

        return theater;
    }
}
