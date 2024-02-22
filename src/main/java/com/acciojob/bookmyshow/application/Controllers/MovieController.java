package com.acciojob.bookmyshow.application.Controllers;

import com.acciojob.bookmyshow.application.Entities.Movie;
import com.acciojob.bookmyshow.application.Requests.AddMovieRequest;
import com.acciojob.bookmyshow.application.Services.MovieService;
import com.acciojob.bookmyshow.application.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowService showService;
    @PostMapping("addMovie")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest addMovieRequest)
    {
        String result = movieService.addMovie(addMovieRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("updateMovie")
    public ResponseEntity updateMovieDetails(@RequestParam Integer movieId, @RequestBody Movie updatedMovie) {
        try {
            Movie updateMovie = movieService.updateMovieDetails(movieId,updatedMovie);
            return new ResponseEntity(updatedMovie,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("mostLikedMovie")
    public String mostLikedMovie()
    {
        String result = movieService.mostLikedMovie();
        return result;
    }

    @DeleteMapping("deleteMovie")
    public ResponseEntity deleteMovie(@RequestParam("movieName") String movieName)
    {
        try {
            String result = movieService.deleteMovie(movieName);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
