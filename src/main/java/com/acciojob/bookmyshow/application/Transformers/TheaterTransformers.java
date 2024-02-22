package com.acciojob.bookmyshow.application.Transformers;

import com.acciojob.bookmyshow.application.Entities.Theater;
import com.acciojob.bookmyshow.application.Requests.AddTheaterRequest;

public class TheaterTransformers {

    public static Theater convertRequestToEntity(AddTheaterRequest theaterRequest)
    {
        //In order to Object of MovieEntity there are 2 ways to make:
        //1. Old Method by using Constructor...
        //Movie movie = new Movie();

        //2. Professional way using @Builder Annotation...if i want to make an object of any class/entity there at the
        //top of class i need to write the @Builder Annotation....Also while using @Builder Annotation u need to use the
        //AllArgsConstructor as well this is compulsory for @Builder Annotation

        Theater theater = Theater.builder()
                .theaterName(theaterRequest.getTheaterName())
                .theaterAddress(theaterRequest.getTheaterAddress())
                .build();

        return theater;
    }
}
