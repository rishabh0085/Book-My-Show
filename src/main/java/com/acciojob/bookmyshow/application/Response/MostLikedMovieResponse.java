package com.acciojob.bookmyshow.application.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MostLikedMovieResponse {

    private String movieName;
    private double movieRating;
}
