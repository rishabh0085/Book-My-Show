package com.acciojob.bookmyshow.application.Services;

import com.acciojob.bookmyshow.application.Entities.Movie;
import com.acciojob.bookmyshow.application.Entities.Show;
import com.acciojob.bookmyshow.application.Entities.Theater;
import com.acciojob.bookmyshow.application.Repository.MovieRepository;
import com.acciojob.bookmyshow.application.Requests.AddMovieRequest;
import com.acciojob.bookmyshow.application.Transformers.MovieTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest movieRequest) {
        Movie movie = MovieTransformers.convertRequestToEntity(movieRequest);

        movie = movieRepository.save(movie);

        return "New Movie has been added to DB Successfully!!! with movieID " + movie.getMovieId();
    }

    public Movie updateMovieDetails(Integer movieId, Movie updatedMovie) throws Exception
    {

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (optionalMovie.isEmpty())
        {
            throw new Exception("Movie Not present");
        }

        Movie existingMovie = optionalMovie.get();

        // Update the existing movie details with the new details
        existingMovie.setMovieName(updatedMovie.getMovieName());
        existingMovie.setMovieDuration(updatedMovie.getMovieDuration());
        existingMovie.setMovieGenre(updatedMovie.getMovieGenre());
        existingMovie.setMovieLanguage(updatedMovie.getMovieLanguage());
        existingMovie.setMovieReleaseDate(updatedMovie.getMovieReleaseDate());
        existingMovie.setRating(updatedMovie.getRating());

        // Save the updated movie details
        return movieRepository.save(existingMovie);
    }

    public String mostLikedMovie() {
        //Most liked movie will be that one whose rating is highest...
        //i need to iterate over movieList and check for which movie we have highest Rating...i have already written
        //sql query for that in MovieRepository

        String mostLikedMovie = movieRepository.findMostLikedMovieTitle();

        return "Most Liked movie is " + mostLikedMovie;

    }

    public String deleteMovie(String movieName) throws Exception
    {
        Optional<Movie> optionalMovie = movieRepository.findByMovieName(movieName);

        if (optionalMovie.isEmpty())
        {
            throw new Exception("Invalid Movie Name....Not Present!!!");
        }

        Movie movie = optionalMovie.get();

        movieRepository.delete(movie);

        return "Movie has been deleted successfully!!!";
    }

}
