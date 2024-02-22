package com.acciojob.bookmyshow.application.Transformers;

import com.acciojob.bookmyshow.application.Entities.Movie;
import com.acciojob.bookmyshow.application.Requests.AddMovieRequest;

public class MovieTransformers {

    public static Movie convertRequestToEntity(AddMovieRequest movieRequest)
    {
        //In order to Object of MovieEntity there are 2 ways to make:
        //1. Old Method by using Constructor...
        //Movie movie = new Movie();

        //2. Professional way using @Builder Annotation...if i want to make an object of any class/entity there at the
        //top of class i need to write the @Builder Annotation....Also while using @Builder Annotation u need to use the
        //AllArgsConstructor as well this is compulsory for @Builder Annotation

        Movie movie = Movie.builder()
                .movieLanguage(movieRequest.getMovieLanguage())
                .movieDuration(movieRequest.getMovieDuration())
                .movieName(movieRequest.getMovieName())
                .movieReleaseDate(movieRequest.getMovieReleaseDate())
                .movieGenre(movieRequest.getMovieGenre())
                .rating(movieRequest.getRating()).build();

        return movie;
    }
}
