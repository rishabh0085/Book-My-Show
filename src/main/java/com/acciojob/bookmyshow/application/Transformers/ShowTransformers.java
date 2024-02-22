package com.acciojob.bookmyshow.application.Transformers;

import com.acciojob.bookmyshow.application.Entities.Show;
import com.acciojob.bookmyshow.application.Requests.AddShowRequest;

public class ShowTransformers {

    public static Show convertRequestToEntity(AddShowRequest showRequest)
    {
        Show show = Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .movieFormat(showRequest.getMovieFormat()).build();

        return show;
    }
}
