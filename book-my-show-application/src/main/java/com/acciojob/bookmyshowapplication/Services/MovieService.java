package com.acciojob.bookmyshowapplication.Services;

import com.acciojob.bookmyshowapplication.Entities.Movie;
import com.acciojob.bookmyshowapplication.Repository.MovieRepository;
import com.acciojob.bookmyshowapplication.Requests.AddMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(AddMovieRequest movieRequest)
    {
        //new method : of creating an Object by using @Builder Annotation
        Movie movie = Movie.builder()        //order of attributes does not matter
                .movieLanguage(movieRequest.getMovieLanguage())
                .movieName(movieRequest.getMovieName())
                .duration(movieRequest.getDuration())
                .genre(movieRequest.getGenre())
                .releaseDate(movieRequest.getReleaseDate())
                .rating(movieRequest.getRating())
                .build();

        movie = movieRepository.save(movie);

        return "The movie has been saved with the movieId "+movie.getMovieId();
    }

    public Movie getMovie(Integer movieId){

        Movie movie = movieRepository.findById(movieId).get();
        return movie;
    }
}
